package com.bugtracker.dao;

import java.util.List;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugCommentFile;
import com.bugtracker.entity.BugFile;
import com.bugtracker.entity.BugUser;

public interface BugDAO {

	boolean saveBug(Bug bug);

	boolean updateBug(Bug bug);

	List<Bug> getBugs(int projectId, String status);

	boolean saveBugComment(BugComment bugComment);

	Bug findBug(int bugId);

	List<BugComment> getBugComments(int bugId);

	List<BugFile> getBugFiles(Integer bugId);

	int getBugCount(String type);

	List<BugUser> getUserBugs(Integer userId);

	boolean saveBugFile(BugFile bugFile);

	boolean saveBugUser(BugUser bugUser);

	boolean saveBugCommentFile(BugCommentFile bugCommentFile);

	boolean changeBugUserStatus(int id, String status);
}
