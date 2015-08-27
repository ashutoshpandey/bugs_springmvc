package com.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.dao.BugDAO;
import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;
import com.bugtracker.entity.BugUser;

@Service
public class BugServiceImpl implements BugService {

	@Autowired
	private BugDAO dao;
	
	@Override
	public boolean saveBug(Bug bug) {
		
		return dao.saveBug(bug);
	}

	@Override
	public boolean updateBug(Bug bug) {
		
		return dao.updateBug(bug);
	}

	@Override
	public boolean changeBugStatus(Bug bug) {
		
		return dao.updateBug(bug);
	}

	@Override
	public List<Bug> getBugs(int projectId, String bugType) {
		
		return dao.getBugs(projectId, bugType);
	}

	@Override
	public boolean saveBugComment(BugComment bugComment) {
		
		return dao.saveBugComment(bugComment);
	}

	@Override
	public List<BugComment> getBugComments(int bugId) {
		
		return dao.getBugComments(bugId);
	}

	@Override
	public boolean sendNewBugEmail(String username, String email,
			String project, String bugTitle, String description,
			List<String> attachments) {
		
		return false;
	}

	@Override
	public Bug findBug(int bugId) {

		return dao.findBug(bugId);
	}

	@Override
	public List<BugFile> getBugFiles(Integer bugId) {

		return dao.getBugFiles(bugId);
	}

	@Override
	public int getBugCount(String type) {
		
		return dao.getBugCount(type);
	}

	@Override
	public List<BugUser> getUserBugs(Integer userId) {
		
		return dao.getUserBugs(userId);
	}
}
