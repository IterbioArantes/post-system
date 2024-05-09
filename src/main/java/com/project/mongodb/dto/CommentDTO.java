package com.project.mongodb.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CommentDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String comment;
	private LocalDate date;
	private AuthorDTO authorDTO;
	
	public CommentDTO() {
	}

	public CommentDTO(String comment, LocalDate date, AuthorDTO authorDTO) {
		super();
		this.comment = comment;
		this.date = date;
		this.authorDTO = authorDTO;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public AuthorDTO getAuthorDTO() {
		return authorDTO;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}

}
