package student.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import student.business.LoginService;
import student.domain.User;



@Controller
public class LoginController {
	@Autowired
	private LoginService loginServ;

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String registerGet() {
		
		return "register";
	}
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request, Model md) throws ServletException {
		boolean result = loginServ.register(user, request);
		if(result) {

		return "redirect:/";
		}
		md.addAttribute("msg_error", "Пользователь с таким email уже зарегистрирован");
		return "redirect:/register?error=true";
	}
}
