package com.poly.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT o FROM User o") })

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "favoriteByUser", procedureName = "FILTER_VIDEO_FAV_USER ", parameters = {
				@StoredProcedureParameter(name = "titleVideo", type = String.class) }),
		@NamedStoredProcedureQuery(name = "userShare", procedureName = "FILTER_USER_SHARE ", parameters = {
				@StoredProcedureParameter(name = "titleVideo", type = String.class) })

})

@Entity
@Table(name = "Users")
public class User {
	@Id
	String id;
	String password;
	String email;
	String fullname;
	boolean admin = false;
	@OneToMany(mappedBy = "user")
	List<Favorite> favorite;
	@OneToMany(mappedBy = "user")
	List<Share> share;

	public User() {
		super();
	}

	public User(String id, String password, String email, String fullname, boolean admin, List<Favorite> favorite,
			List<Share> share) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.admin = admin;
		this.favorite = favorite;
		this.share = share;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fulllname) {
		this.fullname = fulllname;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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
