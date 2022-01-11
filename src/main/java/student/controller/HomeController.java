package student.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import student.business.StudentOrderChildService;
import student.business.StudentService;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.domain.User;

@Controller
public class HomeController {
	@Autowired
	private StudentService studentServ;
	@Autowired
	private StudentOrderChildService childService;
	
	
	@GetMapping("/")
	public String getHome() {
		
		return "index.html";
	}
	@GetMapping("/formPay")
	public String getFormPay(Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		if(studentServ.getStudentOrderByEmailAdd(user).isPresent()) {
			return "redirect:/orders";
		}
		md.addAttribute("studentOrder", new StudentOrder());
		md.addAttribute("pOfficies", studentServ.getListPassportOffice());
		md.addAttribute("registers", studentServ.getListRegisterOffice());
		md.addAttribute("univers", studentServ.getListUnivers());
		md.addAttribute("streets", studentServ.findListStreet());
		md.addAttribute("statuses", studentServ.findListStatus());
		return "formPay";
	}
	@PostMapping("/formPay/add")
	public String postFormPay(@ModelAttribute("studentOrder")@Valid StudentOrder so, BindingResult result, @AuthenticationPrincipal User user, Model md) {
		if(result.hasErrors()) {
			md.addAttribute("pOfficies", studentServ.getListPassportOffice());
			md.addAttribute("registers", studentServ.getListRegisterOffice());
			md.addAttribute("univers", studentServ.getListUnivers());
			md.addAttribute("streets", studentServ.findListStreet());
			md.addAttribute("statuses", studentServ.findListStatus());
			return "/formPay";
		}
		studentServ.saveStudentOrder(so, user);
		return "redirect:/formChilds";
	}
	@GetMapping("/orders")
	public String viewOrders(Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		Optional<StudentOrder> stud = studentServ.getStudentOrderByEmailAdd(user);
		List<StudentOrderChild> childs = childService.getStudentOrderChildByEmailAdd(user);

		if(stud.isPresent()) {
		md.addAttribute("studentOrder", stud.get());
		md.addAttribute("studentOrderChild", childs);
		return "myOrders";
		}
		return "redirect:/";
		
	}
	
	@GetMapping("/formChilds")
	public String getFormChild(Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		if(studentServ.getStudentOrderByEmailAdd(user).isPresent()) {
		md.addAttribute("child", new StudentOrderChild());
		md.addAttribute("registers", studentServ.getListRegisterOffice());
		md.addAttribute("streets", studentServ.findListStreet());
		return "formChilds";
		}
		return "redirect:/formPay";
	}
	@PostMapping("/formChilds/add")
	public String getFormChild(@ModelAttribute("child") @Valid StudentOrderChild soc, BindingResult result,@AuthenticationPrincipal User user, Model md) {
		if(result.hasErrors()) {
			md.addAttribute("registers", studentServ.getListRegisterOffice());
			md.addAttribute("streets", studentServ.findListStreet());
			return "/formChilds";
		}
		childService.saveChildNew(soc, user);
		
		return "redirect:/orders";
	}
	
}
