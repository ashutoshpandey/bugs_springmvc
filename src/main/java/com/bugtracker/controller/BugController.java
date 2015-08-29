package com.bugtracker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugCommentFile;
import com.bugtracker.entity.BugFile;
import com.bugtracker.entity.BugUser;
import com.bugtracker.entity.Project;
import com.bugtracker.entity.User;
import com.bugtracker.pojo.BugCommentData;
import com.bugtracker.pojo.BugData;
import com.bugtracker.service.BugService;
import com.bugtracker.service.ProjectService;
import com.bugtracker.service.UserService;

@Controller
public class BugController {

	@Autowired
	private BugService service;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/create-bug")
	public String createBug(ModelMap map, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "redirect:/";

		Object projectId = request.getSession().getAttribute("currentProjectId");

		List<User> users = userService.getAllUsers();

		if (null != projectId) {
			map.addAttribute("projectId", projectId);
			map.addAttribute("users", users);
			return "bugs/create";
		} else
			return "redirect:/";
	}

	@RequestMapping("/save-bug")
	@ResponseBody
	public String saveBug(Bug bug, ModelMap map, HttpServletRequest request,
			@RequestParam("file") MultipartFile[] files, @RequestParam String users) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "not logged";

		bug.setTitle(request.getParameter("title"));
		bug.setDescription(request.getParameter("description"));
		bug.setSeverity(request.getParameter("severity"));
		bug.setBugType(request.getParameter("bugType"));
		bug.setUser(new User(Integer.parseInt(request.getSession().getAttribute("userId").toString())));
		bug.setProject(new Project(Integer.parseInt(request.getSession().getAttribute("currentProjectId").toString())));
		bug.setStatus("active");
		bug.setCreatedAt(new Date());

		service.saveBug(bug);

		if (files != null && files.length > 0) {

			String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");

			for (MultipartFile file : files) {

				String fileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString();

				String filePath = uploadPath + "/" + savedFileName;

				File dest = new File(filePath);

				try {
					file.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				BugFile bugFile = new BugFile();

				bugFile.setBug(bug);
				bugFile.setFileName(fileName);
				bugFile.setSavedFileName(savedFileName);
				bugFile.setStatus("active");

				service.saveBugFile(bugFile);

				System.out.println("Saved file : " + filePath);
			}
		}

		String[] userIds = null;
		if (null != users) {
			userIds = users.split(",");
		}

		if (null != userIds && userIds.length > 0) {

			for (String strUserId : userIds) {

				BugUser bugUser = new BugUser();

				bugUser.setBug(bug);
				bugUser.setUser(new User(Integer.parseInt(strUserId)));
				bugUser.setStatus("active");

				service.saveBugUser(bugUser);

				User user = userService.findUser(Integer.parseInt(strUserId));
				Project project = projectService.findProject(bug.getProject().getId());

				if (null != user)
					sendNewBugEmail(user.getName(), user.getEmail(), project.getName(), bug.getTitle(),
							bug.getDescription(), null);
			}
		}

		return "done";
	}

	@RequestMapping("/edit-bug/{id}")
	public String editBug(@PathVariable("id") Integer id, ModelMap map, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "redirect:/";

		Bug bug = service.findBug(id);

		map.addAttribute("bug", bug);

		return "bugs/edit";
	}

	@RequestMapping("/update-bug")
	@ResponseBody
	public String updateBug(ModelMap map, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "not logged";

		int id = Integer.parseInt(request.getParameter("id"));

		Bug bug = service.findBug(id);

		if (null != bug) {

			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String status = request.getParameter("status");

			if (null != title)
				bug.setTitle(title);

			if (null != description)
				bug.setDescription(request.getParameter("description"));

			if (null != status)
				bug.setStatus(request.getParameter("status"));

			service.updateBug(bug);

			return "done";
		} else
			return "invalid";
	}

	@RequestMapping("/change-bug-status/{id}")
	@ResponseBody
	public String changeBugStatus(@PathVariable("id") Integer id, ModelMap map, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "not logged";

		Bug bug = service.findBug(id);

		if (null != bug) {

			String status = request.getParameter("status");

			if (null != status) {
				bug.setStatus(request.getParameter("status"));

				service.changeBugStatus(bug);

				service.changeBugUserStatus(bug.getId(), bug.getStatus());

				return "done";
			} else
				return "invalid";
		} else
			return "invalid";
	}

	@RequestMapping("/list-bugs/{projectId}")
	public String listBugs(@PathVariable("projectId") Integer projectId, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "redirect:/";

		if (null != projectId) {

			request.getSession().setAttribute("currentProjectId", projectId);

			return "bugs/list";
		} else
			return "redirect:/";
	}

	@RequestMapping("/save-bug-comment")
	@ResponseBody
	public String saveBugComment(ModelMap map, HttpServletRequest request,
			@RequestParam("file") MultipartFile[] files) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "not logged";

		BugComment bugComment = new BugComment();

		bugComment.setComment(request.getParameter("comment"));
		bugComment.setUser(new User(Integer.parseInt(request.getSession().getAttribute("userId").toString())));
		bugComment.setBug(new Bug(Integer.parseInt(request.getSession().getAttribute("currentBugId").toString())));
		bugComment.setStatus("active");

		service.saveBugComment(bugComment);

		if (files != null && files.length > 0) {

			String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");

			for (MultipartFile file : files) {

				String fileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString();

				String filePath = uploadPath + "/" + savedFileName;

				File dest = new File(filePath);

				try {
					file.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				BugCommentFile bugCommentFile = new BugCommentFile();

				bugCommentFile.setBugComment(bugComment);
				bugCommentFile.setFileName(fileName);
				bugCommentFile.setSavedFileName(savedFileName);
				bugCommentFile.setStatus("active");

				service.saveBugCommentFile(bugCommentFile);
			}
		}

		return "done";
	}

	@RequestMapping("/bug-detail/{id}")
	public String bugDetail(@PathVariable("id") Integer id, ModelMap map, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId)
			return "redirect:/";

		if (null != id) {

			Bug bug = service.findBug(id);

			Project project = projectService.findProject(bug.getProject().getId());

			if (null != bug && null != project) {
				request.getSession().setAttribute("currentBugId", id);

				List<BugFile> bugFiles = service.getBugFiles(id);

				map.addAttribute("project", project);
				map.addAttribute("bug", bug);
				map.addAttribute("bugFiles", bugFiles);
				map.addAttribute("imageTypes", new String[] { "jpeg", "jpg", "gif", "png", "pdf", "doc", "docx" });

				return "bugs/detail";
			} else
				return "redirect:/";
		} else
			return "redirect:/";
	}

	@RequestMapping("/download-bug/{id}")
	public String downloadBug(@PathVariable("id") Integer bugId, HttpServletRequest request,
			HttpServletResponse response) {

		if (null != bugId) {

			Bug bug = service.findBug(bugId);

			if (null != bug) {

				String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");

				List<BugFile> bugFiles = service.getBugFiles(bugId);

				if (null != bugFiles && !bugFiles.isEmpty()) {

					for (BugFile bugFile : bugFiles) {
						try {
							File fileToDownload = new File(uploadPath + "/" + bugFile.getSavedFileName());
							InputStream inputStream = new FileInputStream(fileToDownload);
							response.setContentType("application/force-download");
							response.setHeader("Content-Disposition", "attachment; filename=" + bugFile.getFileName());
							IOUtils.copy(inputStream, response.getOutputStream());
							response.flushBuffer();
							inputStream.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

		return null;
	}

	/****************** json methods ***********************/

	@RequestMapping("/data-list-bugs")
	@ResponseBody
	public BugData dataListBugs(@RequestParam String status, ModelMap map, HttpServletRequest request) {
		System.out.println("Status = " + status);
		BugData bugData = new BugData();

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId) {

			bugData.setMessage("not logged");

			return bugData;
		}

		if (null != request.getSession().getAttribute("currentProjectId")) {

			int projectId = Integer.parseInt(request.getSession().getAttribute("currentProjectId").toString());

			List<Bug> bugs = service.getBugs(projectId, status);

			if (null != bugs && !bugs.isEmpty()) {

				bugData.setBugs(bugs);
				bugData.setFound(true);
				bugData.setMessage("logged");
			} else {
				bugData.setFound(false);
				bugData.setMessage("logged");
			}
		} else {
			bugData.setFound(false);
			bugData.setMessage("logged");
		}

		return bugData;
	}

	@RequestMapping("/data-list-bug-comments")
	@ResponseBody
	public BugCommentData dataListBugComments(ModelMap map, HttpServletRequest request) {

		BugCommentData bugCommentData = new BugCommentData();

		Object userId = request.getSession().getAttribute("userId");
		if (null == userId) {
			bugCommentData.setMessage("not logged");

			return bugCommentData;
		}

		if (null != request.getSession().getAttribute("currentBugId")) {
			int bugId = Integer.parseInt(request.getSession().getAttribute("currentBugId").toString());

			List<BugComment> bugComments = service.getBugComments(bugId);

			if (null != bugComments && !bugComments.isEmpty()) {

				bugCommentData.setBugComments(bugComments);
				bugCommentData.setFound(true);
				bugCommentData.setMessage("logged");
			} else {
				bugCommentData.setFound(false);
				bugCommentData.setMessage("logged");
			}
		} else {
			bugCommentData.setFound(false);
			bugCommentData.setMessage("logged");
		}

		return bugCommentData;
	}

	public void sendNewBugEmail(String username, String email, String project, String bugTitle, String description,
			List<String> attachments) {
		String portal = "BUGS@YOGASMOGA";

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("project", project);
		data.put("description", description);
		data.put("username", username);
		data.put("portal", portal);
		data.put("bugTitle", bugTitle);

		// $result = Mail::send("emails.new-bug", $data, function($message) use
		// ($email, $attachments) {
		// $message.to($email);
		// $message.subject("New bug added at yogasmoga");
		// $message.from("info@yogasmoga.com");
		// });

		// if(isset($attachments) && count($attachments)>0) {
		//
		// foreach($attachments as $attachment) {
		//
		// $mime = "application/pdf";
		// $as = "pdf-report.zip";
		//
		// $message.attach($attachment,
		// array(
		// "as" => $as,
		// "mime" => $mime
		// )
		// );
		// }
		// }
		// });
		//
	}

}
