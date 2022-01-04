package student.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import student.response.UniversityResponse;

@Controller
public class RequestController {
	
	
	//@PostMapping(path="/admin/checkUniversity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	@PostMapping("/admin/checkUniversity")
	public String getStudentInfo() throws UnknownHostException, IOException {
		URL url = new URL("http://localhost:8005/checkUniversity");
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		byte[] out = "{\"passportDate\":\"30.04.2016\",\"passportNumber\":\"222222\",\"dateOfBirth\":\"12.04.2000\",\"passportSeria\":\"1111\",\"middleName\":\"Middle\", \"firstName\":\"First\", \"lastName\":\"Last\"}" .getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		    
		}
		BufferedReader br = new BufferedReader(new InputStreamReader( http.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
		    sb.append(line + "\n");
		}
		br.close();
		String json = sb.toString();
		ObjectMapper mapper = new ObjectMapper();
		List<UniversityResponse> list = mapper.readValue(json, new TypeReference<List<UniversityResponse>>(){});
		for(UniversityResponse ur: list) {
			System.out.println(ur.getFacultyName());
		}
		System.out.println(""+sb.toString());
		
		
		
		http.disconnect();
		return "redirect:/admin/orders"; 
	}
}
