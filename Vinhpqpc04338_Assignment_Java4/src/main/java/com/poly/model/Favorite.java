package com.poly.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@NamedQueries({
		@NamedQuery(name = "Favorite.getLikes", query = "SELECT COUNT(o.video.id) FROM Favorite o WHERE o.video.id=:id"),
		@NamedQuery(name = "Favorite.unLike", query = "SELECT o.id FROM Favorite o WHERE o.user.id=:usid AND o.video.id=:id") })

@Entity
@Table(name = "Favorite", uniqueConstraints = { @UniqueConstraint(columnNames = { "VideoID", "UserID" }) })
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(name = "UserID")
	User user;
	@ManyToOne
	@JoinColumn(name = "VideoID")
	Video video;
	@Temporal(TemporalType.DATE)
	Date likeDate = new Date();

	public Favorite() {
		super();
	}

	public Favorite(Long id, User user, Video video, Date likeDate) {
		super();
		this.id = id;
		this.user = user;
		this.video = video;
		this.likeDate = likeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}
