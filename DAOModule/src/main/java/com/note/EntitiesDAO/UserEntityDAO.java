package com.note.entitiesDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author hamza.elbouazzaoui
 *
 */
@Entity
@Table(name="users")
public class UserEntityDAO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "SEQ_GEN")
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(mappedBy="noteUser")
	@JsonIgnore
	private Collection<NoteEntityDAO> userNotes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<RoleEntityDAO> userRoles = new ArrayList<>();
	
	public UserEntityDAO() {
        super();
	}
	

	
	public UserEntityDAO(Long userId, String firstName, String lastName, String email, Collection<NoteEntityDAO> userNotes,
			Collection<RoleEntityDAO> userRoles,String username,String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userNotes = userNotes;
		this.userRoles = userRoles;
		this.username = username;
		this.password = password;
	}


	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<NoteEntityDAO> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Collection<NoteEntityDAO> userNotes) {
		this.userNotes = userNotes;
	}



	public Collection<RoleEntityDAO> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Collection<RoleEntityDAO> userRoles) {
		this.userRoles = userRoles;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
		
}
