package poly.pt15307.sof3011.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "User")
@DynamicUpdate
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Integer id;

	@Column(name = "first_name", length = 100)
	private String firstName;
	
	@Column(name = "last_name", length = 100)
	private String lastName;
	
	@Column(name = "email", length = 100, updatable = false)
	private String email;
	
	@Column(name = "password", length = 299)
	private String password;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "gender")
	private boolean gender;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "isAdmin", updatable = false)
	private boolean admin;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			mappedBy = "author", 
			cascade = CascadeType.ALL)
	@MapKey
	private Map<Integer, Video> videosMap;
	
	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<React> reacts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return this.firstName + ' ' + this.lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Map<Integer, Video> getVideosMap() {
		return videosMap;
	}

	public void setVideosMap(Map<Integer, Video> videosMap) {
		this.videosMap = videosMap;
	}
	
	public List<React> getReacts() {
		return reacts;
	}
	
	public void setReacts(List<React> reacts) {
		this.reacts = reacts;
	}
	
	public List<Video> getAllVideoExcep(Video video) {
		return videosMap.values().stream()
					.filter( v -> v.getId() != video.getId())
					.collect(Collectors.toList());
	}
	
	public List<Video> lovedVideos() {
		return reacts.stream()
				.filter( react -> react.isLike())
				.map( react -> react.getVideo() )
				.collect(Collectors.toList());
	}
	
	public long getNumberVideo() {
		return this.videosMap.values().size();
	}
	
	public long getNumberLiked() {
		return this.lovedVideos().size();
	}
	
	// add video to user's videos in session
	public void addVideoToSession(Video video) {
		React react = new React();
		react.setVideo(video);
		react.setLike(true);
		react.setUser(this);
		reacts.add(react);
	}
	
	// remove video from user's videos in session
	public void removeVideoInSession(Video video) {
		React react = new React();
		react.setVideo(video);
		react.setUser(this);
		react.setLike(true);
		
		this.reacts.remove(react);
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		
		if (!(obj instanceof User)) return false;
		
		User user = (User) obj;
		
		return user.getId() == this.id;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
