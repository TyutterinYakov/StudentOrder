package student.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import student.business.StudentOrderService;
import student.business.StudentService;
import student.domain.Adult;
import student.domain.StudentOrder;

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
//		stOrServ.testSave();
//		stOrServ.testGet();
		md.addAttribute("status", studentServ.getStatus());
		return "orders";
	}
	//WIFE
	@GetMapping("/admin/wife/{id}")
	public String getInfoWife(@PathVariable("id") Long number, Model md ) {
		
			md.addAttribute("adult", studentServ.getWifeByStudentOrderId(number));
		
		return "parent-info";
	}
	//HUSBAND
	@GetMapping("/admin/husband/{id}")
	public String getInfoHusband(@PathVariable("id") Long number, Model md ) {
		
			md.addAttribute("adult", studentServ.getHusbandByStudentOrderId(number));
		
		return "parent-info";
	}
	@GetMapping("/admin/order/remove/{id}")
	public String removeStudentOrder(@PathVariable Long id) {
		studentServ.removeStudentOrderById(id);
		return "redirect:/admin/orders";
	}
	
	@GetMapping("/admin/order/add")
	public String orderAdd() {
		
		return "orderAdd";
	}
}
