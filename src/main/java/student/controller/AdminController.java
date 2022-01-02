package student.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.business.StatusService;
import student.business.StreetService;
import student.business.StudentOrderChildService;
import student.business.StudentOrderService;
import student.business.StudentService;
import student.domain.Address;
import student.domain.Adult;
import student.domain.Street;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.domain.StudentOrderStatus;

@Controller
public class AdminController {

	@Autowired
	private StudentService studentServ;
//	@Autowired
//	private StudentOrderService stOrServ;
	
	@Autowired
	private StudentOrderChildService childService;
	
	@Autowired
	private StreetService streetService;
	@Autowired
	private StatusService statusService;
	

	
	
	
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
		md.addAttribute("pOfficies", studentServ.getListPassportOffice());
		md.addAttribute("registers", studentServ.getListRegisterOffice());
		md.addAttribute("univers", studentServ.getListUnivers());
		md.addAttribute("streets", studentServ.findListStreet());
		md.addAttribute("statuses", studentServ.findListStatus());
		List<StudentOrderChild> childs = studentServ.getStudentOrderById(id).get().getStudentOrderChild();
		md.addAttribute("childs", childs);
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
	//STREET
	@GetMapping("/admin/streets")
	public String getStreets(Model md) {
		md.addAttribute("streets", streetService.findListStreet());
		return "streets";
	}
	@GetMapping("/admin/street/update/{id}")
	public String updateStreet(@PathVariable("id") Long id, Model md) {
		md.addAttribute("street", streetService.getStreetById(id));
		return "streetAdd";
	}
	@PostMapping("/admin/street/add")
	public String updateStreet(@ModelAttribute("street") Street street) {
		streetService.saveStreet(street);
		return "redirect:/admin/streets";
	}
	
	@GetMapping("/admin/street/remove/{id}")
	public String removeStreet(@PathVariable("id") Long id, Model md) {
		streetService.removeStreetById(id);
		return "redirect:/admin/streets";
	}
	@GetMapping("/admin/street/add")
	public String addStreet(Model md) {
		md.addAttribute("street", new Street());
		return "streetAdd";
	}
	
	//STATUS
	@GetMapping("/admin/status")
	public String getStatus(Model md) {
		md.addAttribute("status", statusService.getListStatus());
		return "status";
	}
	@GetMapping("/admin/status/update/{id}")
	public String updateStatus(@PathVariable("id") Long id, Model md) {
		md.addAttribute("status", statusService.getStatusById(id));
		return "statusAdd";
	}
	@PostMapping("/admin/status/add")
	public String updateStatus(@ModelAttribute("studentOrderStatus") StudentOrderStatus status) {
		statusService.saveStatus(status);
		return "redirect:/admin/status";
	}
	
	@GetMapping("/admin/status/remove/{id}")
	public String removeStatus(@PathVariable("id") Long id, Model md) {
		statusService.removeStatusById(id);
		return "redirect:/admin/status";
	}
	@GetMapping("/admin/status/add")
	public String addStatus(Model md) {
		md.addAttribute("status", new StudentOrderStatus());
		return "statusAdd";
	}
	
}
