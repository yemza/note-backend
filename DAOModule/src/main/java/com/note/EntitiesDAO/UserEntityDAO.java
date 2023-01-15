package com.note.entitiesDAO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy="noteUser")
	@JsonIgnore
	private Set<NoteEntityDAO> userNotes;
	
	public UserEntityDAO() {
        super();
	}
	

	
	public UserEntityDAO(Long userId, String firstName, String lastName, String email, Set<NoteEntityDAO> userNotes) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userNotes = userNotes;
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

	public Set<NoteEntityDAO> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Set<NoteEntityDAO> userNotes) {
		this.userNotes = userNotes;
	}
	
	
		
}
