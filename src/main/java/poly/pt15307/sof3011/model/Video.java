package poly.pt15307.sof3011.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Video")
@DynamicUpdate
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Integer id;
	
	@Column(name = "title", length = 100)
	private String title;
	
	@Column(name = "youtubeUrl")
	private String youtubeUrl;
	
	@Column(name = "thumbnail")
	private String thumbnailUrl;
	
	@Column(name = "date", updatable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "view")
	private Integer view;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( 
			name = "user_id",
			foreignKey = @ForeignKey(name = "FK_VIDEO_USER"), 
			updatable = false)
	private User author;
	
	@OneToMany(
			mappedBy = "video",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<React> reacts;
	
	public Video() {
		view = 0;
		date = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		if (view == null) view = 0;
		this.view = view;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User uploadBy) {
		this.author = uploadBy;
	}

	public List<React> getReact() {
		return this.reacts;
	}
	
	
	public void setReact(List<React> reacts) {
		this.reacts = reacts;
	}
	
	// state: like or dislike
	public long getNumber( boolean state ) {
		return reacts.stream()
				.filter(r -> r.isLike() == state)
				.count();
	}
	
	// check User is one of the Users liked or disliked this video
	public boolean contains( boolean state, User user ) {
		
		long number = reacts.stream().filter( r -> {
			System.out.println(r.getUser().getId());
			System.out.println(user.getId());
			return r.isLike() == state && 
				   r.getUser().equals(user);
		}).count();
		
		System.out.println(number);
		
		return number == 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		
		if(!(obj instanceof Video)) return false;
		
		Video video = (Video) obj;
		
		return video.getId() == this.id;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
