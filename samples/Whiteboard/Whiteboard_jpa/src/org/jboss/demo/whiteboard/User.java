package org.jboss.demo.whiteboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.Length;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="Users")
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String username;
	private String passwd;
	private List<UserRole>userRoles = new ArrayList<UserRole>();

	public User() {
		super();
	}

	@Id
	@Length(max=64)	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Length(max=64)
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@OneToMany(mappedBy="username", cascade=CascadeType.ALL)
	@OrderBy("id")
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public void addUserRole(UserRole userRole){
		userRole.setUsername(this);
		this.getUserRoles().add(userRole);
	}
   
	
	
}
