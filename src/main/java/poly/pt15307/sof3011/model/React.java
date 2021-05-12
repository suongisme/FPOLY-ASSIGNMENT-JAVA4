package poly.pt15307.sof3011.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "React")
public class React implements Serializable {
 
	private static final long serialVersionUID = 2001L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id", foreignKey = @ForeignKey(name = "FK_LIKE_VIDEO"))
	private Video video;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_LIKE_USER"))
	private User user;

	@Column(name = "isLike")
	private Boolean like;
	
	public Boolean getLike() {
		return like;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean isLike() {
		return like;
	}

	public void setLike(Boolean like) {
		this.like = like;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;

		if (!(obj instanceof React)) return false;
		
		React react = (React) obj;
		
		return this.user.equals(react.getUser()) &&
			   this.video.equals(react.getVideo()) &&
			   this.like == react.isLike();
 	}
	
	@Override
	public int hashCode() {
		return Objects.hash(user, video);
	}
	
	@Override
	public String toString() {
		return "VIDEO: "+this.video.getId() + " LIKE: "+this.like+" USER: "+this.user.getFullName();
	}
}