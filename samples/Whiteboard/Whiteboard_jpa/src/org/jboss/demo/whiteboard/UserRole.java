package org.jboss.demo.whiteboard;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserRole
 *
 */
@Entity
@Table(name="userRoles")
public class UserRole implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private User username;
	private  String userRoles;

	public UserRole() {
		super();
	}

	public UserRole(String string) {
		this.setUserRoles(string);
	}

	@Id @GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="username")
	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	
	
   
}
