package com.bugtracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class BugController {

//    public String __construct(ModelMap map, HttpServletRequest request){
//        View::share("root", URL::to("/"));
//        View::share("name", request.getSession().getAttribute("name"));
//        View::share("userType", request.getSession().getAttribute("userType"));
//    }

    public String createBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        Object projectId = request.getSession().getAttribute("currentProject");

        $users = User::all();

        if(null!=projectId)
            return View::make("bugs.create")->with("projectId", $projectId)->with("users", $users);
        else
            return "redirect:/";
    }

    public String saveBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "not logged";        

        $bug = new Bug();

        $bug->title = Input::get("title");
        $bug->description = Input::get("description");
        $bug->severity = Input::get("severity");
        $bug->created_by = request.getSession().getAttribute("userId")
        $bug->project_id = request.getSession().getAttribute("currentProject");
        $bug->status = "active";

        $bug->save();

        $files = Input::file("file");
        if(isset($files))
            $fileCount = count($files);
        else
            $fileCount = 0;

        $users = Input::get("users");

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

                $bugFile->bug_id = $bug->id;
                $bugFile->file_name = $filename;
                $bugFile->saved_file_name = $savedFileName;
                $bugFile->status = "active";

                $bugFile->save();
            }
        }

        if($userCount>0){

            foreach($users as int userId){
                $bugUser = new BugUser();

                $bugUser->bug_id = $bug->id;
                $bugUser->user_id = int userId;
                $bugUser->status = "active";

                $bugUser->save();

                $user = User::finduserId;
                $project = Project::find($bug->project_id);

                if(isset($user))
                    $this->sendNewBugEmail($user->name, $user->email, $project->name, $bug->title, $bug->description, null);
            }
        }

        echo "done";
    }

    public String editBug($id){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "redirect:/";

        $bug = Bug::find($id);

        return View::make("bugs.edit")->with("bug", $bug);
    }

    public String updateBug(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "not logged";

        $id = Input::get("id");

        $bug = Bug::find($id);

        if($bug){

            $title = Input::get("title");
            $description = Input::get("description");
            $status = Input::get("status");

            if(isset($title))
                $bug->title = Input::get("title");

            if(isset($description))
                $bug->description = Input::get("description");

            if(isset($status))
                $bug->status = Input::get("status");

            $bug->save();

            echo "done";
        }
        else
            echo "invalid";
    }

    public String changeBugStatus(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "not logged";

        $id = Input::get("id");

        $bug = Bug::find($id);

        if($bug){

            $status = Input::get("status");

            if(isset($status)) {
                $bug->status = Input::get("status");
                $bug->save();

                BugUser::where("bug_id", "=", $bug->id) ->update(["status" => $bug->status]);

                echo "done";
            }
            else
                echo "invalid";
        }
        else
            echo "invalid";
    }

    public String listBugs($projectId){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "redirect:/";

        if(isset($projectId)){

            Session::put("currentProject", $projectId);

            return View::make("bugs.list");
        }
        else
            return "redirect:/";
    }

    public String saveBugComment(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "not logged";

        $bugComment = new BugComment();

        $bugComment->comment= Input::get("comment");
        $bugComment->created_by = request.getSession().getAttribute("userId")
        $bugComment->bug_id = request.getSession().getAttribute("currentBugId");
        $bugComment->status = "active";

        $bugComment->save();

        $files = Input::file("file");
        $fileCount = count($files);

        if($fileCount>0){
            foreach($files as $file) {
                $destinationPath = "public/uploads";

                $savedFileName = date("Ymdhis");

                $filename = $file->getClientOriginalName();
                $file->move($destinationPath, $savedFileName);

                $bugCommentFile = new BugCommentFile();

                $bugCommentFile->bug_comment_id = $bugComment->id;
                $bugCommentFile->file_name = $filename;
                $bugCommentFile->saved_file_name = $savedFileName;
                $bugCommentFile->status = "active";

                $bugCommentFile->save();
            }
        }

        echo "done";
    }

    public String bugDetail($bugId){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return "redirect:/";

        if(isset($bugId)){

            $bug = Bug::find($bugId);
            $project = Project::find($bug->project_id);

            if(isset($bug) && isset($project)){
                Session::put("currentBugId", $bugId);

                $bugFiles = BugFile::where("bug_id", "=", $bugId)->get();

                return View::make("bugs.detail")
                    ->with("project", $project)
                    ->with("bug", $bug)
                    ->with("bugFiles", $bugFiles);
            }
            else
                return "redirect:/";
        }
        else
            return "redirect:/";
    }

    public String downloadBug($bugId){

        if(isset($bugId)){

            $bug = Bug::find($bugId);

            if(isset($bug)){

                $bugFiles = BugFile::where("bug_id", "=", $bugId)->get();

                if(isset($bugFiles)){

                    foreach($bugFiles as $bugFile){

                    }
                }
            }
        }
    }

    /****************** json methods ***********************/

    public String dataListBugs(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return json_encode(array("message" => "not logged"));

        $projectId = request.getSession().getAttribute("currentProject");

        $bugType = Input::get("bug_type");

        if(isset($projectId)){
            $bugs = Bug::where("project_id", "=", $projectId)->where("status","=",$bugType)->get();

            if($bugs && count($bugs)>0)
                return json_encode(array("found" => true, "bugs" => $bugs->toArray(), "message" => "logged"));
            else
                return json_encode(array("found" => false, "message" => "logged"));
        }
        else
            return json_encode(array("found" => false, "message" => "logged"));
    }

    public String dataListBugComments(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId")
        if(null==userId)
            return json_encode(array("message" => "not logged"));

        $bugId = request.getSession().getAttribute("currentBugId");

        if(isset($bugId)){
            $comments = BugComment::with(array("User", "BugCommentFiles"))->where("bug_id", "=", $bugId)->get();

            if($comments && count($comments)>0)
                return json_encode(array("found" => true, "comments" => $comments->toArray(), "message" => "logged"));
            else
                return json_encode(array("found" => false, "message" => "logged"));
        }
        else
            return json_encode(array("found" => false, "message" => "logged"));
    }

    public String sendNewBugEmail($username, $email, $project, $bugTitle, $description, $attachments){
        $portal = "BUGS@YOGASMOGA";

        $data["project"] = $project;
        $data["description"] = $description;
        $data["username"] = $username;
        $data["portal"] = $portal;
        $data["bugTitle"] = $bugTitle;

        $result = Mail::send("emails.new-bug", $data, function($message) use ($email, $attachments) {
            $message->to($email);
            $message->subject("New bug added at yogasmoga");
            $message->from("info@yogasmoga.com");
        });

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
