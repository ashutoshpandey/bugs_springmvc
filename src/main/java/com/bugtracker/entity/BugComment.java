package com.bugtracker.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bug_comments")
public class BugComment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	private String status;
	
	@OneToMany(mappedBy="bugComment")
	private List<BugCommentFile> bugCommentFiles;

	@ManyToOne
	@JoinColumn(name="bug_id")
	private Bug bug;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public Bug getBug() {
		return bug;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public List<BugCommentFile> getBugCommentFiles() {
		return bugCommentFiles;
	}

	public void setBugCommentFiles(List<BugCommentFile> bugCommentFiles) {
		this.bugCommentFiles = bugCommentFiles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
