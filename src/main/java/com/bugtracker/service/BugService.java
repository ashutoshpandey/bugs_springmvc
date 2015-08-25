package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;

public interface BugService {

	boolean saveBug(Bug bug);

	boolean updateBug(Bug bug);

	boolean changeBugStatus(Bug bug);

	boolean saveBugComment(BugComment bugComment);
	
	Bug findBug(int bugId);

	List<BugFile> getBugFiles(Integer bugId);

	List<Bug> getBugs(int projectId, String bugType);

    List<BugComment> getBugComments(int bugId);

    boolean sendNewBugEmail(String username, String email, String project, String bugTitle, String description, List<String> attachments);
}
