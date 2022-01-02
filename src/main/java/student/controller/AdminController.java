package student.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.business.StudentOrderChildService;
import student.business.StudentOrderService;
import student.business.StudentService;
import student.domain.Address;
import student.domain.Adult;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;

@Controller
public class AdminController {

	@Autowired
	private StudentService studentServ;
	@Autowired
	private StudentOrderService stOrServ;
	
	@Autowired
	private StudentOrderChildService childService;
	

	
	
	
	@GetMapping("/admin")
	public String getAdminPage() {
	
		return "adminHome";
	}
	@GetMapping("/admin/orders")
	public String getOrders(Model md) {
		md.addAttribute("orders", studentServ.getAllStudentOrder());
		stOrServ.testSave();
		stOrServ.testGet();
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
	//CHILD
	
	@GetMapping("/admin/child/{id}")
	public String infoChild(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrderChild> soc = childService.getChildOrderById(id);
		if(soc.isPresent()) {
		md.addAttribute("orderChild",soc.get());
		}
		
		return "child-info";
	}
	@GetMapping("/admin/orders/title")
	public String searchOrders(@RequestParam(name="title", required = false) Long id, Model md) {
		if(id==null||id==0L) {
			return "redirect:/admin/orders";
		}
		md.addAttribute("orders", studentServ.getListStudentOrderById(id));
		
		return "orders";
	}
	
	@GetMapping("/admin/order/update/{id}")
	public String getAddOrders(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrder> so = studentServ.getStudentOrderById(id);
		if(so.isPresent()) {
			md.addAttribute("studentOrder", so.get());
		} else {
			return "404";
		}
		return "orderAdd";
	}
	@PostMapping("/admin/orders/add")
	public String updateOrder(@ModelAttribute("studentOrder") StudentOrder so, Model md) {
		
		
		
		studentServ.updateStudentOrder(so);
		return "redirect:/admin/orders";
	}
}
