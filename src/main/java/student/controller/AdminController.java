package student.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.business.CountryStructService;
import student.business.PassportOfficeService;
import student.business.RegisterOfficeService;
import student.business.StatusService;
import student.business.StreetService;
import student.business.StudentOrderChildService;
import student.business.StudentService;
import student.business.UniversityService;
import student.domain.CountryStruct;
import student.domain.PassportOffice;
import student.domain.RegisterOffice;
import student.domain.Street;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.domain.StudentOrderStatus;
import student.domain.University;
import student.domain.User;
import student.exception.DataNotFoundException;

@Controller
@RequestMapping("/admin")
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
	@Autowired
	private UniversityService universityService;
	@Autowired
	private PassportOfficeService passportService;
	@Autowired
	private CountryStructService structService;
	@Autowired
	private RegisterOfficeService registerService;
	

	
	
	
	@GetMapping()
	public String getAdminPage() {
	
		return "adminHome";
	}
	@GetMapping("/orders")
	public String getOrders(Model md) throws DataNotFoundException {
		md.addAttribute("orders", studentServ.getAllStudentOrder());
	//	stOrServ.testSave();
		md.addAttribute("status", studentServ.getStatus(1L));
		
		
		return "orders";
	}
	
	@PostMapping("/checkAll")
	public String checkAll() {
		studentServ.checkAllOrders();
		return "redirect:/admin/orders";
	}
	//WIFE
	@GetMapping("/wife/{id}")
	public String getInfoWife(@PathVariable("id") Long number, Model md ) throws DataNotFoundException {
		
			md.addAttribute("adult", studentServ.getWifeByStudentOrderId(number));
		
		return "parent-info";
	}
	//HUSBAND
	@GetMapping("/husband/{id}")
	public String getInfoHusband(@PathVariable("id") Long number, Model md ) throws DataNotFoundException {
		
			md.addAttribute("adult", studentServ.getHusbandByStudentOrderId(number));
		
		return "parent-info";
	}
	@GetMapping("/order/remove/{id}")
	public String removeStudentOrder(@PathVariable Long id) {
		studentServ.removeStudentOrderById(id);
		return "redirect:/admin/orders";
	}
	//CHILD
	
	@GetMapping("/child/{id}")
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
	@GetMapping("/orders/title")
	public String searchOrders(@RequestParam(name="title", required = false) Long id, Model md) {
		if(id==null||id==0L) {
			return "redirect:/admin/orders";
		}
		md.addAttribute("orders", studentServ.getListStudentOrderById(id));
		
		return "orders";
	}
	@PostMapping("/childs/add")
	public String updateChild(@ModelAttribute("studentOrderChild") StudentOrderChild soc, Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		childService.saveChild(soc, user);
		//studentServ.updateStudentOrder(so);
		return "redirect:/admin/orders";
	}
	
	@GetMapping("/order/update/{id}")
	public String getAddOrders(@PathVariable("id") Long id, Model md, @AuthenticationPrincipal User user) {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
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
	@PostMapping("/orders/add")
	public String updateOrder(@ModelAttribute("studentOrder") StudentOrder so, Model md, @AuthenticationPrincipal User user) throws DataNotFoundException {
		if(user.getEmail()==null) {
			return "redirect:/logout";
		}
		
		studentServ.updateStudentOrder(so, user);

		return "redirect:/admin/orders";
	}	
	
	//STREET
	@GetMapping("/streets")
	public String getStreets(Model md) {
		md.addAttribute("streets", streetService.findListStreet());
		return "streets";
	}
	@GetMapping("/street/update/{id}")
	public String updateStreet(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("street", streetService.getStreetById(id));
		return "streetAdd";
	}
	@PostMapping("/street/add")
	public String updateStreet(@ModelAttribute("street") Street street) {
		streetService.saveStreet(street);
		return "redirect:/admin/streets";
	}
	
	@GetMapping("/street/remove/{id}")
	public String removeStreet(@PathVariable("id") Long id, Model md) {
		streetService.removeStreetById(id);
		return "redirect:/admin/streets";
	}
	@GetMapping("/street/add")
	public String addStreet(Model md) {
		md.addAttribute("street", new Street());
		return "streetAdd";
	}
	
	//STATUS
	@GetMapping("/status")
	public String getStatus(Model md) {
		md.addAttribute("status", statusService.getListStatus());
		return "status";
	}
	@GetMapping("/status/update/{id}")
	public String updateStatus(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("status", statusService.getStatusById(id));
		return "statusAdd";
	}
	@PostMapping("/status/add")
	public String updateStatus(@ModelAttribute("studentOrderStatus") StudentOrderStatus status) {
		statusService.saveStatus(status);
		return "redirect:/admin/status";
	}
	
	@GetMapping("/status/remove/{id}")
	public String removeStatus(@PathVariable("id") Long id, Model md) {
		statusService.removeStatusById(id);
		return "redirect:/admin/status";
	}
	@GetMapping("/status/add")
	public String addStatus(Model md) {
		md.addAttribute("status", new StudentOrderStatus());
		return "statusAdd";
	}
	
	//University
	@GetMapping("/universities")
	public String getUniversities(Model md) {
		md.addAttribute("universities", universityService.findAllUniversity());
		return "university";
	}

	@GetMapping("/university/update/{id}")
	public String updateUniversity(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("university", universityService.getUniversityById(id));
		return "universityAdd";
	}
	@PostMapping("/university/add")
	public String updateUniversity(@ModelAttribute("university") University university) {
		universityService.saveUniversity(university);
		return "redirect:/admin/universities";
	}
	
	@GetMapping("/university/remove/{id}")
	public String removeUniversity(@PathVariable("id") Long id, Model md) {
		universityService.removeUniversityById(id);
		return "redirect:/admin/universities";
	}
	@GetMapping("/university/add")
	public String addUniversity(Model md) {
		md.addAttribute("university", new University());
		return "universityAdd";
	}
	
	//PASSPORT
	
	@GetMapping("/pasports")
	public String getPassports(Model md) {
		md.addAttribute("passports", passportService.findListPassportOffice());
		return "passportOffice";
	}
	@GetMapping("/passport/update/{id}")
	public String updatePassport(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("passport", passportService.getPassportById(id));
		return "passportAdd";
	}
	@PostMapping("/passport/add")
	public String updatePassport(@ModelAttribute("passport") PassportOffice pass) {
		passportService.savePassport(pass);
		return "redirect:/admin/pasports";
	}
	
	@GetMapping("/passport/remove/{id}")
	public String removePassport(@PathVariable("id") Long id, Model md) {
		passportService.removePassportById(id);
		return "redirect:/admin/pasports";
	}
	@GetMapping("/passport/add")
	public String addPassport(Model md) {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("passport", new PassportOffice());
		return "passportAdd";
	}
	
	
	//COUNTRIES
	
	@GetMapping("/countries")
	public String getCountries(Model md) {
		md.addAttribute("countries", structService.findAllStruct());
		
		return "countryStruct";
	}
	@GetMapping("/country/update/{id}")
	public String updateCountry(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("struct", structService.findStructById(id));
		return "countryStructAdd";
	}
	@PostMapping("/country/add")
	public String updateCountry(@ModelAttribute("struct") CountryStruct str) {
		structService.saveCountryStruct(str);
		
		return "redirect:/admin/countries";
	}
	
	@GetMapping("/country/remove/{id}")
	public String removeCountry(@PathVariable("id") Long id, Model md) {
		structService.removeStructById(id);
		return "redirect:/admin/countries";
	}
	@GetMapping("/country/add")
	public String addCountry(Model md) {
		md.addAttribute("passportAreas", studentServ.findListCountry());
		md.addAttribute("struct", new CountryStruct());
		return "countryStructAdd";
	}
	
	//RegisterOffice
	
	@GetMapping("/register")
	public String getRegister(Model md) {
		md.addAttribute("registers", registerService.findListRegister());
		return "registerOffice";
	}
	@GetMapping("/register/update/{id}")
	public String updateRegister(@PathVariable("id") Long id, Model md) throws DataNotFoundException {
		md.addAttribute("registerOffice", registerService.findRegisterOfficeById(id));
		md.addAttribute("areas", studentServ.findListCountry());
		return "registerOfficeAdd";
	}
	@PostMapping("/register/add")
	public String updateRegister(@ModelAttribute("registerOffice") RegisterOffice ro) {
		registerService.saveRegisterOffice(ro);
		
		return "redirect:/admin/register";
	}
	
	@GetMapping("/register/remove/{id}")
	public String removeRegister(@PathVariable("id") Long id, Model md) {
		registerService.removeRegisterOfficeById(id);
		return "redirect:/admin/register";
	}
	@GetMapping("/register/add")
	public String addRegister(Model md) {
		md.addAttribute("areas", studentServ.findListCountry());
		md.addAttribute("registerOffice", new RegisterOffice());
		return "registerOfficeAdd";
	}
	
	
}
