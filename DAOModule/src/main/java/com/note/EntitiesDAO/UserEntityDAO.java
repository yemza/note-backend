package com.note.entitiesDAO;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author hamza.elbouazzaoui
 *
 */
@Entity
@Table(name="users")
public class UserEntityDAO implements UserDetails{

	
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
	private String userName;
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy="noteUser")
	@JsonIgnore
	private Set<NoteEntityDAO> userNotes;
	
	@Column(name="role")
	private String role;

	
	public UserEntityDAO() {
        super();
	}
	

	
	public UserEntityDAO(Long userId, String firstName, String lastName, String email, Set<NoteEntityDAO> userNotes,String userName,String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userNotes = userNotes;
		this.userName = userName;
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

	public Set<NoteEntityDAO> getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(Set<NoteEntityDAO> userNotes) {
		this.userNotes = userNotes;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
		
}
