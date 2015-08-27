package com.bugtracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugtracker.entity.BugUser;
import com.bugtracker.entity.User;
import com.bugtracker.pojo.UserData;
import com.bugtracker.service.BugService;
import com.bugtracker.service.ProjectService;
import com.bugtracker.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;	

	@Autowired
	private ProjectService projectService;	

	@Autowired
	private BugService bugService;	

	@RequestMapping("/user-section")
    public String userSection(ModelMap map, HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "redirect:/";

        int userId = Integer.parseInt(objUserId.toString());
        
        int runningProjects = projectService.getProjectCount("active");
        int closedProjects = projectService.getProjectCount("closed");
        int currentBugs = bugService.getBugCount("active");
        int fixedBugs = bugService.getBugCount("fixed");
        int unresolvedBugs = bugService.getBugCount("unresolved");

        List<BugUser> userBugs = bugService.getUserBugs(userId);

        map.addAttribute("runningProjects", runningProjects);
        map.addAttribute("closedProjects", closedProjects);
        map.addAttribute("currentBugs", currentBugs);
        map.addAttribute("fixedBugs", fixedBugs);
        map.addAttribute("unresolvedBugs", unresolvedBugs);
        map.addAttribute("userBugs", userBugs);
        
        return "users/user-section";
    }

	@RequestMapping("/create-user")
    public String createUser(HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "redirect:/";

        return "users/create";
    }

	@RequestMapping("/save-user")
	@ResponseBody
    public String saveUser(User user, HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "not logged";

        User exisingUser = service.findUserByEmail(user.getEmail());

        if(null != exisingUser){
            return "Duplicate email";
        }
        else{
            user.setStatus("active");

            service.saveUser(user);

            return "User created successfully";
        }
    }

	@RequestMapping("/profile")
    public String profile(ModelMap map, HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "redirect:/";

        int userId = Integer.parseInt(objUserId.toString());

        User user = service.findUser(userId);

        if(null != user){

        	map.addAttribute("user", user);
        	
            return "users/profile";
        }
        else
            return "redirect:/";
    }

	@RequestMapping("/update-profile")
	@ResponseBody
    public String updateProfile(User user, HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "not logged";

        int userId = Integer.parseInt(objUserId.toString());
        
        if(null != user){

            User exisingUser = service.findUserByEmail(user.getEmail());

            if(null != exisingUser && exisingUser.getId() != userId)
                return "Email already taken";
            else{
            	user.setId(userId);
            	
                service.updateUser(user);

                return "Profile updated successfully";
            }
        }
        else
            return "Invalid user";
    }

	@RequestMapping("/edit-user")
    public String editUser(@RequestParam Integer userId, HttpServletRequest request, ModelMap map){

        if(null==userId)
            return "redirect:/";

        User user = service.findUser(userId.intValue());

        request.setAttribute("current_edit_user", userId);

        if(null != user){

        	map.addAttribute("user", user);
        	
            return "users/edit";
        }
        else
            return "redirect:/";
    }

	@RequestMapping("/update-user")
	@ResponseBody
    public String updateUser(User user, HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("current_edit_user");
        if(null==objUserId)
            return "invalid";

        if(null != user){

            service.updateUser(user);

            return "Profile updated successfully";
        }
        else
            return "Invalid user";
    }

	@RequestMapping("/list-users")
    public String listUsers(HttpServletRequest request){

        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId)
            return "redirect:/";

        return "users/list";
    }

	@RequestMapping("/remove-user")
	@ResponseBody
    public String removeUser(Integer userId){

        if(null!=userId) {

            User user = service.findUser(userId);

            if(null!=user){
                user.setStatus("removed");

                service.saveUser(user);

                return "done";
            }
            else
                return "invalid";
        }
        else
            return "invalid";
    }

    /************** json methods ***************/
	@RequestMapping("/data-list-users")
	@ResponseBody
    public UserData dataListUsers(HttpServletRequest request){

    	UserData userData = new UserData();
    	
        Object objUserId = request.getSession().getAttribute("userId");
        if(null==objUserId){
        	userData.setMessage("not logged");

        	return userData;
        }

        List<User> users = service.getAllUsers();

        if(null!=users && !users.isEmpty()){
        	
        	userData.setUsers(users);
        	userData.setFound(true);
        	userData.setMessage("logged");
        }
        else{
        	userData.setFound(false);
        	userData.setMessage("logged");
        }
        
        return userData;
    }
	
}
