package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@RequestMapping("/")
	public String login() {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView dologin(
            ModelAndView mv,
            @RequestParam("name") String name
    		) {
		if(name == null || name.length() == 0) {
			mv.addObject("message","名前を入力してください");
			mv.setViewName("index");
		}
		session.setAttribute("name", name);
		session.setAttribute("categories", categoryRepository.findAll());
		
    	mv.setViewName("top");
        return mv;
    }
	
	@RequestMapping("/logout")
	public String logout() {
		return login();
	}
	
}
