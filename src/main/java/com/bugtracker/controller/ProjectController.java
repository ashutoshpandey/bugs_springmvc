package com.bugtracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugtracker.entity.Project;
import com.bugtracker.pojo.ProjectData;
import com.bugtracker.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService service;
	
//    public String __construct(ModelMap map, HttpServletRequest request){
//        View::share("root", URL::to("/"));
//        View::share("name", request.getSession().getAttribute("name"));
//        View::share("userType", request.getSession().getAttribute("userType"));
//    }

    @RequestMapping("/create-project")
    public String createProject(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

    	return "projects/create";
    }

    @RequestMapping("/save-project")
    @ResponseBody
    public String saveProject(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";        

        Project project = new Project();

        project.setName(request.getParameter("name"));
        project.setDescription(request.getParameter("description"));
        project.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        project.setStatus("active");

        service.saveProject(project);

        return "done";
    }

    @RequestMapping("/edit-project")
    public String editProject(@RequestParam Integer id, ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        Project project = service.findProject(id);

        if(null != project){
	        map.addAttribute("project", project);
	        
	        return "projects/edit";
        }
        else
        	return "redirect:/";
    }

    @RequestMapping("/update-project")
    @ResponseBody
    public String updateProject(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";

        int id = Integer.parseInt(request.getParameter("id"));

        Project project = service.findProject(id);

        if(null!=project){

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            if(null!=name)
                project.setName(name);

            if(null!=description)
                project.setDescription(request.getParameter("description"));

            if(null!=status)
                project.setStatus(request.getParameter("status"));

            service.updateProject(project);

            return "done";
        }
        else
            return "invalid";
    }

    @RequestMapping("/remove-project")
    @ResponseBody
    public String removeProject(ModelMap map, HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "not logged";

        int id = Integer.parseInt(request.getParameter("id"));

        Project project = service.findProject(id);

        if(null!=project){
            
            project.setStatus("removed");
            
            service.updateProject(project);

            return "done";
        }
        else
            return "invalid";
    }

    @RequestMapping("/list-projects")
    public String listProjects(HttpServletRequest request){

        Object userId = request.getSession().getAttribute("userId");
        if(null==userId)
            return "redirect:/";

        return "projects/list";
    }

    @RequestMapping("/data-list-projects")
    @ResponseBody
    public ProjectData dataListProjects(ModelMap map, HttpServletRequest request){

    	ProjectData projectData = new ProjectData();
    	
        Object userId = request.getSession().getAttribute("userId");
        if(null==userId){
        
        	projectData.setMessage("not logged");
            
        	return projectData;
        }

        String projectType = request.getParameter("project_type");

        if(null == projectType)
        	projectType = "active";
        
        List<Project> projects = service.getProjects(projectType);

        if(null!=projects && !projects.isEmpty()){
        	
        	projectData.setProjects(projects);
        	projectData.setFound(true);
        	projectData.setMessage("logged");
        }
        else{
        	projectData.setFound(false);
        	projectData.setMessage("logged");
        }
        
        return projectData;
    }
}