package com.bugtracker.dao;

import java.util.List;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;

public class BugDAOImpl implements BugDAO {

	@Override
	public boolean saveBug(Bug bug) {
		
		return false;
	}

	@Override
	public boolean updateBug(Bug bug) {
		
		return false;
	}

	@Override
	public List<Bug> getBugs(int projectId) {
		
		return null;
	}

	@Override
	public boolean saveBugComment(BugComment comment) {
		
		return false;
	}

	@Override
	public List<BugComment> getBugComments(int bugId) {
		
		return null;
	}

	@Override
	public Bug findBug(int bugId) {

		return null;
	}

	@Override
	public List<BugFile> getBugFiles(Integer bugId) {

		return null;
	}

}
