package com.bugtracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugtracker.entity.User;
import com.bugtracker.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/is-valid-user")
	@ResponseBody
    public String isValidUser(String email, String password, HttpServletRequest request){

        User user = service.isValidUser(email, password);

        if(null==user)
            return "invalid";
        else{
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("userType", user.getUserType());
            request.getSession().setAttribute("name", user.getName());

            return "correct";
        }
    }

	@RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().invalidate();

        return "redirect:/";
    }
}