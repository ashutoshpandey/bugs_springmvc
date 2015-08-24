package com.bugtracker.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;

@Service
public interface BugDAO {

	boolean saveBug(Bug bug);

	boolean updateBug(Bug bug);

	List<Bug> getBugs(int projectId);

	boolean saveBugComment(BugComment bugComment);

	Bug findBug(int bugId);

	List<BugComment> getBugComments(int bugId);

	List<BugFile> getBugFiles(Integer bugId);
}
