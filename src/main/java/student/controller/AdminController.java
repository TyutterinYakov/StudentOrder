package student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import student.business.StudentOrderService;
import student.business.StudentService;

@Controller
public class AdminController {

	@Autowired
	private StudentService studentServ;
//	@Autowired
//	private StudentOrderService stOrServ;
	
	
	@GetMapping("/admin")
	public String getAdminPage() {
	
		return "adminHome";
	}
	@GetMapping("/admin/orders")
	public String getOrders(Model md) {
		md.addAttribute("orders", studentServ.getAllStudentOrder());
		//stOrServ.testSave();
		//stOrServ.testGet();
		md.addAttribute("status", studentServ.getStatus());
		return "orders";
	}
}
