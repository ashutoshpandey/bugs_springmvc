package com.bugtracker.pojo;

import java.util.List;

import com.bugtracker.entity.BugComment;

public class BugCommentData {

	private String message;

	private List<BugComment> bugComments;
	
	private boolean found;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BugComment> getBugComments() {
		return bugComments;
	}

	public void setBugComments(List<BugComment> bugComments) {
		this.bugComments = bugComments;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
}
