package student.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import student.business.StudentService;
import student.business.UniversityRequestService;
import student.domain.StudentOrder;
import student.request.UniversityRequest;
import student.response.UniversityResponse;

@Controller
public class RequestController {
	
	@Autowired
	private UniversityRequestService requestUniversityService;
	@Autowired
	private StudentService studentServ;
	
	
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
		requestUniversityService.buildWifeUniversityRequest(id);
		return "redirect:/admin/checkUniversity/{id}"; 
	}
	@PostMapping("/admin/checkUniversity/husband/{id}")
	public String postStudentHusband(@PathVariable("id") Long id) {
		requestUniversityService.buildHusbandUniversityRequest(id);
		return "redirect:/admin/checkUniversity/{id}"; 
	}


}
