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
@Table(name="bugs")
public class Bug {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="description", length=255)
	private String description;
	
	@Column(name="severity", length=255)
	private String severity;
	
	@Column(name="title", length=255)
	private String title;
	
	private String status;
	
	@Column(name="bug_type", length=255)
	private String bugType;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy="bug")
	private List<BugComment> bugComments;
	
	@OneToMany(mappedBy="bug")
	private List<BugFile> bugFiles;
	
	@OneToMany(mappedBy="bug")
	private List<BugUser> bugUsers;

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Bug(int id) {
		this.id = id;
	}
	
	public Bug(){		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public List<BugComment> getBugComments() {
		return bugComments;
	}

	public List<BugFile> getBugFiles() {
		return bugFiles;
	}

	public List<BugUser> getBugUsers() {
		return bugUsers;
	}

	public void setBugComments(List<BugComment> bugComments) {
		this.bugComments = bugComments;
	}

	public void setBugFiles(List<BugFile> bugFiles) {
		this.bugFiles = bugFiles;
	}

	public void setBugUsers(List<BugUser> bugUsers) {
		this.bugUsers = bugUsers;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
