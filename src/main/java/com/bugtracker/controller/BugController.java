package com.bugtracker.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;
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
	
//    public String __construct(ModelMap map, HttpServletRequest request){
//        View::share("root", URL::to("/"));
//        View::share("name", request.getSession().getAttribute("name"));
//        View::share("userType", request.getSession().getAttribute("userType"));
//    }

    @RequestMapping("/")
    public String createBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        Object projectId = request.getSession().getAttribute("currentProject");

        List<User> users = userService.getAllUsers();

        if(null!=projectId){
        	map.addAttribute("projectId", projectId);
        	map.addAttribute("users", users);
            return "bugs/create";
        }
        else
            return "redirect:/";
    }

    @RequestMapping("/")
    @ResponseBody
    public String saveBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";        

        Bug bug = new Bug();

        bug.setTitle(request.getParameter("title"));
        bug.setDescription(request.getParameter("description"));
        bug.setSeverity(request.getParameter("severity"));
        bug.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        bug.setProjectId(Integer.parseInt(request.getSession().getAttribute("currentProject").toString()));
        bug.setStatus("active");

        service.saveBug(bug);
/*
        $files = Input::file("file");
        if(isset($files))
            $fileCount = count($files);
        else
            $fileCount = 0;

        $users(request.getParameter(("users");

        if(isset($users))
            $userCount = count($users);
        else
            $userCount = 0;

        if($fileCount>0){
            foreach($files as $file) {
                $destinationPath = "public/uploads";

                $savedFileName = date("Ymdhis");

                $filename = $file->getClientOriginalName();
                $file->move($destinationPath, $savedFileName);

                $bugFile = new BugFile();

                $bugFile->bug_id = bug.setid;
                $bugFile->file_name = $filename;
                $bugFile->saved_file_name = $savedFileName;
                $bugFile->status = "active";

                $bugFile->save();
            }
        }

        if($userCount>0){

            foreach($users as int userId){
                $bugUser = new BugUser();

                $bugUser->bug_id = bug.setid;
                $bugUser->user_id = int userId;
                $bugUser->status = "active";

                $bugUser->save();

                $user = User::finduserId;
                $project = Project::find(bug.setproject_id);

                if(isset($user))
                    $this->sendNewBugEmail($user->name, $user->email, $project->name, bug.settitle, bug.setdescription, null);
            }
        }
*/
        return "done";
    }

    @RequestMapping("/")
    public String editBug(Integer id, ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        Bug bug = service.findBug(id);

        map.addAttribute("bug", bug);
        
        return "bugs.edit";
    }

    @RequestMapping("/")
    @ResponseBody
    public String updateBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";

        int id = Integer.parseInt(request.getParameter("id"));

        Bug bug = service.findBug(id);

        if(null!=bug){

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            if(null!=title)
                bug.setTitle(title);

            if(null!=description)
                bug.setDescription(request.getParameter("description"));

            if(null!=status)
                bug.setStatus(request.getParameter("status"));

            service.updateBug(bug);

            return "done";
        }
        else
            return "invalid";
    }

    @RequestMapping("/")
    @ResponseBody
    public String changeBugStatus(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";

        int id = Integer.parseInt(request.getParameter("id"));

        Bug bug = service.findBug(id);

        if(null!=bug){

            String status = request.getParameter("status");

            if(null!=status) {
                bug.setStatus(request.getParameter("status"));
                
                service.changeBugStatus(bug);

                //BugUser::where("bug_id", "=", bug.setid) ->update(["status" => bug.setstatus]);

                return "done";
            }
            else
                return "invalid";
        }
        else
            return "invalid";
    }

    @RequestMapping("/")
    public String listBugs(Integer projectId, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        if(null!=projectId){

            request.getSession().setAttribute("currentProject", projectId);

            return "bugs/list";
        }
        else
            return "redirect:/";
    }

    @RequestMapping("/")
    @ResponseBody
    public String saveBugComment(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";

        BugComment bugComment = new BugComment();

        bugComment.setComment(request.getParameter("comment"));
        bugComment.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        bugComment.setBugId(Integer.parseInt(request.getSession().getAttribute("currentBugId").toString()));
        bugComment.setStatus("active");

        service.saveBugComment(bugComment);
/*
        $files = Input::file("file");
        $fileCount = count($files);

        if($fileCount>0){
            foreach($files as $file) {
                $destinationPath = "public/uploads";

                $savedFileName = date("Ymdhis");

                $filename = $file->getClientOriginalName();
                $file->move($destinationPath, $savedFileName);

                $bugCommentFile = new BugCommentFile();

                $bugCommentFile->bug_comment_id = bugComment.setid;
                $bugCommentFile->file_name = $filename;
                $bugCommentFile->saved_file_name = $savedFileName;
                $bugCommentFile->status = "active";

                $bugCommentFile->save();
            }
        }
*/
        return "done";
    }

    @RequestMapping("/")
    public String bugDetail(Integer bugId, ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        if(null!=bugId){

            Bug bug = service.findBug(bugId);
            Project project = projectService.findProject(bug.getProjectId());

            if(null!=bug && null!=project){
                request.getSession().setAttribute("currentBugId", bugId);

                List<BugFile> bugFiles = service.getBugFiles(bugId);

                map.addAttribute("project", project);
                map.addAttribute("bug", bug);
                map.addAttribute("bugFiles", bugFiles);
                
                return "bugs/detail";
            }
            else
                return "redirect:/";
        }
        else
            return "redirect:/";
    }

    @RequestMapping("/")
    public String downloadBug(Integer bugId){

        if(null!=bugId){

            Bug bug = service.findBug(bugId);

            if(null!=bug){

                List<BugFile> bugFiles = service.getBugFiles(bugId);

                if(null!=bugFiles && !bugFiles.isEmpty()){

//                    for(BugFile bugFile : bugFiles){
//
//                    }
                }
            }
        }
        
        return null;
    }

    /****************** json methods ***********************/

    @RequestMapping("/")
    @ResponseBody
    public BugData dataListBugs(ModelMap map, HttpServletRequest request){

    	BugData bugData = new BugData();
    	
        Object userId = request.getSession().getAttribute("userId");
        if(null==userId){
        
        	bugData.setMessage("not logged");
            
        	return bugData;
        }

        if(null != request.getSession().getAttribute("currentProject")){

        	int projectId = Integer.parseInt(request.getSession().getAttribute("currentProject").toString());

            String bugType = request.getParameter("bug_type");
        	
            List<Bug> bugs = service.getBugs(projectId, bugType);

            if(null!=bugs && !bugs.isEmpty()){
            	
            	bugData.setBugs(bugs);
            	bugData.setFound(true);
            	bugData.setMessage("logged");
            }
            else{
            	bugData.setFound(false);
            	bugData.setMessage("logged");
            }
        }
        else{
        	bugData.setFound(false);
        	bugData.setMessage("logged");
        }
        
        return bugData;
    }

    @RequestMapping("/")
    @ResponseBody
    public BugCommentData dataListBugComments(ModelMap map, HttpServletRequest request){

    	BugCommentData bugCommentData = new BugCommentData();
    	
        Object userId = request.getSession().getAttribute("userId");
        if(null==userId){
        	bugCommentData.setMessage("not logged");

        	return bugCommentData;
        }

        if(null != request.getSession().getAttribute("currentBugId")){
            int bugId = Integer.parseInt(request.getSession().getAttribute("currentBugId").toString());

            List<BugComment> bugComments = service.getBugComments(bugId);

            if(null!=bugComments && !bugComments.isEmpty()){
            	
            	bugCommentData.setBugComments(bugComments);
            	bugCommentData.setFound(true);
            	bugCommentData.setMessage("logged");
            }
            else{
            	bugCommentData.setFound(false);
            	bugCommentData.setMessage("logged");
            }
        }
        else{
        	bugCommentData.setFound(false);
        	bugCommentData.setMessage("logged");
        }
        
        return bugCommentData;
    }

    public void sendNewBugEmail(String username, String email, String project, String bugTitle, String description, List<String> attachments){
        String portal = "BUGS@YOGASMOGA";

        HashMap<String, String> data = new HashMap<String, String>();
        
        data.put("project", project);
        data.put("description", description);
        data.put("username", username);
        data.put("portal", portal);
        data.put("bugTitle", bugTitle);

//        $result = Mail::send("emails.new-bug", $data, function($message) use ($email, $attachments) {
//            $message->to($email);
//            $message->subject("New bug added at yogasmoga");
//            $message->from("info@yogasmoga.com");
//        });

//            if(isset($attachments) && count($attachments)>0) {
//
//                foreach($attachments as $attachment) {
//
//                    $mime = "application/pdf";
//                    $as = "pdf-report.zip";
//
//                    $message->attach($attachment,
//                                    array(
//                                        "as" => $as,
//                                        "mime" => $mime
//                                    )
//                            );
//                }
//            }
//        });
//
    }

}
