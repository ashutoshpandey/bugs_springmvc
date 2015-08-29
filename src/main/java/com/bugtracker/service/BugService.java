package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugCommentFile;
import com.bugtracker.entity.BugFile;
import com.bugtracker.entity.BugUser;

public interface BugService {

	boolean saveBug(Bug bug);

	boolean updateBug(Bug bug);

	boolean changeBugStatus(Bug bug);

	boolean saveBugComment(BugComment bugComment);
	
	Bug findBug(int bugId);

	List<BugFile> getBugFiles(Integer bugId);

	List<Bug> getBugs(int projectId, String status);

    List<BugComment> getBugComments(int bugId);

    boolean sendNewBugEmail(String username, String email, String project, String bugTitle, String description, List<String> attachments);

	int getBugCount(String type);

	List<BugUser> getUserBugs(Integer userId);

	boolean saveBugFile(BugFile bugFile);

	boolean saveBugUser(BugUser bugUser);

	boolean saveBugCommentFile(BugCommentFile bugCommentFile);

	boolean changeBugUserStatus(int id, String status);
}
