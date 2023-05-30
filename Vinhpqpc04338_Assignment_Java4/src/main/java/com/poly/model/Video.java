package com.poly.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT o FROM Video o"),
		@NamedQuery(name = "Video.findFavById", query = "SELECT o.video FROM Favorite o WHERE o.user.id=:id") })

@Entity
@Table(name = "Video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String title;
	String poster;
	String thumbnail;
	String link;
	int views;
	String description;
	boolean active;
	@OneToMany(mappedBy = "video")
	List<Favorite> favorite;
	@OneToMany(mappedBy = "video")
	List<Share> share;

	public Video() {
		super();
	}

	public Video(int id, String title, String poster, String thumbnail, String link, int views, String description,
			boolean active, List<Favorite> favorite, List<Share> share) {
		super();
		this.id = id;
		this.title = title;
		this.poster = poster;
		this.thumbnail = thumbnail;
		this.link = link;
		this.views = views;
		this.description = description;
		this.active = active;
		this.favorite = favorite;
		this.share = share;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(List<Favorite> favorite) {
		this.favorite = favorite;
	}

	public List<Share> getShare() {
		return share;
	}

	public void setShare(List<Share> share) {
		this.share = share;
	}

}
