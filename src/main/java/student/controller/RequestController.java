package student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import student.business.StudentService;
import student.business.RequestCityRegisterService;
import student.business.RequestRegisterService;
import student.business.RequestUniversityService;
import student.business.StudentOrderChildService;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;

@Controller
public class RequestController {
	
	@Autowired
	private RequestUniversityService requestUniversityService;
	@Autowired
	private StudentService studentServ;
	@Autowired
	private RequestRegisterService requestRegisterService;
	@Autowired
	private StudentOrderChildService childService;
	@Autowired
	private RequestCityRegisterService requestCityService;
	
	
	//UNIVERSITY
	@GetMapping("/admin/checkUniversity/{id}")
	public String getStudentInfo(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrder> so = studentServ.getStudentOrderById(id);
		if(so.isPresent()) {
			md.addAttribute("studentOrder", so.get());
			return "checkUniversity";
		}
		
		return "404";
	}
	
	@PostMapping("/admin/checkUniversity/wife/{id}")
	public String postStudentWife(@PathVariable("id") Long id) {
		try {
		requestUniversityService.buildWifeUniversityRequest(id);
		} catch(IOException ex) {
			return "redirect:/admin/checkUniversity/{id}?error=true";
		}
		return "redirect:/admin/checkUniversity/{id}"; 
	}
	@PostMapping("/admin/checkUniversity/husband/{id}")
	public String postStudentHusband(@PathVariable("id") Long id) {
		try {
		requestUniversityService.buildHusbandUniversityRequest(id);
		} catch(IOException ex) {
			return "redirect:/admin/checkUniversity/{id}?error=true";
		}
		return "redirect:/admin/checkUniversity/{id}"; 
	}
	
	
	//REGISTER OFFICE
	@GetMapping("/admin/checkRegister/{id}")
	public String getRegisterInfo(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrder> so = studentServ.getStudentOrderById(id);
		if(so.isPresent()) {
			md.addAttribute("studentOrder", so.get());
			Optional<List<StudentOrderChild>> childs = childService.getChildOrderByStudentOrderId(so.get());
			if(childs.isPresent()) {
				md.addAttribute("childs", childs.get());
			} 
			return "checkRegisterOffice";
		}
		
		return "404";
	}
	@PostMapping("/admin/checkRegister/{id}")
	public String postRegisterInfo(@PathVariable("id") Long id) {
		try {
		requestRegisterService.buildRegisterOfficeRequest(id);
		} catch(IOException ex) {
			return "redirect:/admin/checkRegister/{id}?error=true";
		}
		return "redirect:/admin/checkRegister/{id}";
	}
	
	//CITY REGISTER
	@GetMapping("/admin/checkCityRegister/{id}")
	public String getCityRegister(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrder> so = studentServ.getStudentOrderById(id);
		if(so.isPresent()) {
			md.addAttribute("studentOrder", so.get());
		Optional<List<StudentOrderChild>> childs = childService.getChildOrderByStudentOrderId(so.get());
			if(childs.isPresent()) {
				md.addAttribute("childs", childs.get());
			} 
			return "checkCityRegister";
		}
		return "404";
	}
	@PostMapping("/admin/checkCityRegister/{id}")
	public String postCityRegister(@PathVariable("id") Long id) {
		try {
		requestCityService.buildCityRegisterRequest(id);
		} catch (IOException ex) {
			return "redirect:/admin/checkCityRegister/{id}?error=true";
		}
		
		return "redirect:/admin/checkCityRegister/{id}";
	}
}
