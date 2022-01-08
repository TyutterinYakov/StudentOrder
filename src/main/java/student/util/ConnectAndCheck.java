package student.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import student.request.CityRegisterRequest;
import student.request.RegisterOfficeRequest;
import student.request.UniversityRequest;
import student.response.CityRegisterResponse;
import student.response.RegisterOfficeResponse;
import student.response.UniversityResponse;

public class ConnectAndCheck {

	private Logger log = LoggerFactory.getLogger(ConnectAndCheck.class);
	
	private String check(Object request, String connect) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		URL url = new URL(connect);
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		String jsonString = mapper.writeValueAsString(request);
		log.info("Request: {}",jsonString);
		byte[] out = jsonString.getBytes(StandardCharsets.UTF_8);
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
		http.disconnect();
		
		return json;
	}
	
	
	//CITY
		public List<CityRegisterResponse> getResponseCity(List<CityRegisterRequest> request) throws IOException{
		String json = check(request,"http://localhost:8004/checkCityRegister");
		ObjectMapper mapper = new ObjectMapper();
		log.info("City response: {}",json);
		return mapper.readValue(json, new TypeReference<List<CityRegisterResponse>>(){}); 
		}	
		
	//MARRIAGE
		public RegisterOfficeResponse getResponseMarriage(RegisterOfficeRequest request) throws IOException{
		String json = check(request,"http://localhost:8003/checkMarriage");
		ObjectMapper mapper = new ObjectMapper();
		log.info("Marriage response: {}",json);
			return mapper.readValue(json, new TypeReference<RegisterOfficeResponse>(){});
		}	
	//UNIVERSITY
		public List<UniversityResponse> getResponseUniversity(UniversityRequest request) throws IOException{
		String json = check(request,"http://localhost:8005/checkUniversity");
		ObjectMapper mapper = new ObjectMapper();
		log.info("University response: {}",json);
		return mapper.readValue(json, new TypeReference<List<UniversityResponse>>(){});
		}	
		
	
	
}
