package com.bugtracker.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String email;
	private String password;
	
	@Column(name="user_type")
	private String userType;
	
	@Column(name="profile_image_name")
	private String profileImageName;
	
	@Column(name="profile_image_saved_name")
	private String profileImageSavedName;
	
	private String status;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy="user")
	private List<Project> projects;
	
	@OneToMany(mappedBy="user")
	private List<Bug> bugs;
	
	@OneToMany(mappedBy="user")
	private List<BugUser> bugUsers;

	public User(){		
	}
	
	public User(int id) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public String getProfileImageSavedName() {
		return profileImageSavedName;
	}

	public void setProfileImageSavedName(String profileImageSavedName) {
		this.profileImageSavedName = profileImageSavedName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public List<BugUser> getBugUsers() {
		return bugUsers;
	}

	public void setBugUsers(List<BugUser> bugUsers) {
		this.bugUsers = bugUsers;
	}
}
