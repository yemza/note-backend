package com.note.entitiesDAO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author hamza.elbouazzaoui
 *
 */
@Entity
@Table(name="notes")
public class NoteEntityDAO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "SEQ_GEN")
	@Column(name = "note_id")
	private Long noteId;
	
	@Column(name = "note_title")
	private String noteTitle;
	
	@Column(name = "note_description")
	private String noteDescription;
	
	@Column(name = "note_written_date")
	private Date noteWrittenDate;
	
	@Column(name = "note_modified_date")
	private Date noteModifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id" , nullable = false)
	private UserEntityDAO noteUser;

	
	
	public NoteEntityDAO() {
		super();
	}
	

	public NoteEntityDAO(Long noteId, String noteTitle, String noteDescription, Date noteWrittenDate,
			Date noteModifiedDate) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteDescription = noteDescription;
		this.noteWrittenDate = noteWrittenDate;
		this.noteModifiedDate = noteModifiedDate;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public Date getNoteWrittenDate() {
		return noteWrittenDate;
	}

	public void setNoteWrittenDate(Date noteWrittenDate) {
		this.noteWrittenDate = noteWrittenDate;
	}

	public Date getNoteModifiedDate() {
		return noteModifiedDate;
	}

	public void setNoteModifiedDate(Date noteModifiedDate) {
		this.noteModifiedDate = noteModifiedDate;
	}

	public UserEntityDAO getNoteUser() {
		return noteUser;
	}

	public void setNoteUser(UserEntityDAO noteUser) {
		this.noteUser = noteUser;
	}
	
	

}

