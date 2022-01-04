package student.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.business.CountryStructService;
import student.business.PassportOfficeService;
import student.business.RegisterOfficeService;
import student.business.StatusService;
import student.business.StreetService;
import student.business.StudentOrderChildService;
import student.business.StudentOrderService;
import student.business.StudentService;
import student.business.UniversityService;
import student.domain.Address;
import student.domain.Adult;
import student.domain.CountryStruct;
import student.domain.PassportOffice;
import student.domain.RegisterOffice;
import student.domain.Street;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.domain.StudentOrderStatus;
import student.domain.University;
import student.domain.User;

@Controller
public class AdminController {

	@Autowired
	private StudentService studentServ;
	@Autowired
	private StudentOrderService stOrServ;
	
	@Autowired
	private StudentOrderChildService childService;
	
	@Autowired
	private StreetService streetService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UniversityService universityService;
	@Autowired
	private PassportOfficeService passportService;
	@Autowired
	private CountryStructService structService;
	@Autowired
	private RegisterOfficeService registerService;
	

	
	
	
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
		md.addAttribute("streets", studentServ.findListStreet());
		md.addAttribute("studentOrderChild", childService.getChildOrderById(id));
		md.addAttribute("registers", studentServ.getListRegisterOffice());
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
	@PostMapping("/admin/childs/add")
	public String updateChild(@ModelAttribute("studentOrderChild") StudentOrderChild so, Model md, @AuthenticationPrincipal User user) {
		childService.saveChild(so, user);
		//studentServ.updateStudentOrder(so);
		return "redirect:/admin/orders";
	}
	
	@GetMapping("/admin/order/update/{id}")
	public String getAddOrders(@PathVariable("id") Long id, Model md) {
		Optional<StudentOrder> so = studentServ.getStudentOrderById(id);
		md.addAttribute("pOfficies", studentServ.getListPassportOffice());
		md.addAttribute("registers", studentServ.getListRegisterOffice());
		md.addAttribute("univers", studentServ.getListUnivers());
		md.addAttribute("streets", studentServ.findListStreet());
		md.addAttribute("statuses", studentServ.findListStatus());
		if(so.isPresent()) {
			md.addAttribute("studentOrder", so.get());
		} else {
			return "404";
		}
		return "orderAdd";
	}
	@PostMapping("/admin/orders/add")
	public String updateOrder(@ModelAttribute("studentOrder") StudentOrder so, Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		
		studentServ.updateStudentOrder(so, user);

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
	
	//University
	@GetMapping("/admin/universities")
	public String getUniversities(Model md) {
		md.addAttribute("universities", universityService.findAllUniversity());
		return "university";
	}

	@GetMapping("/admin/university/update/{id}")
	public String updateUniversity(@PathVariable("id") Long id, Model md) {
		md.addAttribute("university", universityService.getUniversityById(id));
		return "universityAdd";
	}
	@PostMapping("/admin/university/add")
	public String updateUniversity(@ModelAttribute("university") University university) {
		universityService.saveUniversity(university);
		return "redirect:/admin/universities";
	}
	
	@GetMapping("/admin/university/remove/{id}")
	public String removeUniversity(@PathVariable("id") Long id, Model md) {
		universityService.removeUniversityById(id);
		return "redirect:/admin/universities";
	}
	@GetMapping("/admin/university/add")
	public String addUniversity(Model md) {
		md.addAttribute("university", new University());
		return "universityAdd";
	}
	
	//PASSPORT
	
	@GetMapping("/admin/pasports")
	public String getPassports(Model md) {
		md.addAttribute("passports", passportService.findListPassportOffice());
		return "passportOffice";
	}
	@GetMapping("/admin/passport/update/{id}")
	public String updatePassport(@PathVariable("id") Long id, Model md) {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("passport", passportService.getPassportById(id));
		return "passportAdd";
	}
	@PostMapping("/admin/passport/add")
	public String updatePassport(@ModelAttribute("passport") PassportOffice pass) {
		passportService.savePassport(pass);
		return "redirect:/admin/pasports";
	}
	
	@GetMapping("/admin/passport/remove/{id}")
	public String removePassport(@PathVariable("id") Long id, Model md) {
		passportService.removePassportById(id);
		return "redirect:/admin/pasports";
	}
	@GetMapping("/admin/passport/add")
	public String addPassport(Model md) {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("passport", new PassportOffice());
		return "passportAdd";
	}
	
	
	//COUNTRIES
	
	@GetMapping("/admin/countries")
	public String getCountries(Model md) {
		md.addAttribute("countries", structService.findAllStruct());
		
		return "countryStruct";
	}
	@GetMapping("/admin/country/update/{id}")
	public String updateCountry(@PathVariable("id") Long id, Model md) {
		md.addAttribute("struct", structService.findStructById(id));
		return "countryStructAdd";
	}
	@PostMapping("/admin/country/add")
	public String updateCountry(@ModelAttribute("struct") CountryStruct str) {
		structService.saveCountryStruct(str);
		
		return "redirect:/admin/countries";
	}
	
	@GetMapping("/admin/country/remove/{id}")
	public String removeCountry(@PathVariable("id") Long id, Model md) {
		structService.removeStructById(id);
		return "redirect:/admin/countries";
	}
	@GetMapping("/admin/country/add")
	public String addCountry(Model md) {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("struct", new CountryStruct());
		return "countryStructAdd";
	}
	
	//RegisterOffice
	
	@GetMapping("/admin/register")
	public String getRegister(Model md) {
		md.addAttribute("registers", registerService.findListRegister());
		return "registerOffice";
	}
	@GetMapping("/admin/register/update/{id}")
	public String updateRegister(@PathVariable("id") Long id, Model md) {
		md.addAttribute("registerOffice", registerService.findRegisterOfficeById(id));
		md.addAttribute("areas", studentServ.findListCountry());
		return "registerOfficeAdd";
	}
	@PostMapping("/admin/register/add")
	public String updateRegister(@ModelAttribute("registerOffice") RegisterOffice ro) {
		registerService.saveRegisterOffice(ro);
		
		return "redirect:/admin/register";
	}
	
	@GetMapping("/admin/register/remove/{id}")
	public String removeRegister(@PathVariable("id") Long id, Model md) {
		registerService.removeRegisterOfficeById(id);
		return "redirect:/admin/register";
	}
	@GetMapping("/admin/register/add")
	public String addRegister(Model md) {
		md.addAttribute("areas", studentServ.findListCountry());
		md.addAttribute("registerOffice", new RegisterOffice());
		return "registerOfficeAdd";
	}
	
	
}
