package student.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import student.request.UniversityRequest;
import student.response.UniversityResponse;

public class ConnectAndCheckUniversity {

	//TODO
	private Logger log = LoggerFactory.getLogger(ConnectAndCheckUniversity.class);
	
	public List<UniversityResponse> checkUniversity(UniversityRequest request) throws IOException {
		List<UniversityResponse> list;
		ObjectMapper mapper = new ObjectMapper();
		URL url = new URL("http://localhost:8005/checkUniversity");
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		String jsonString = mapper.writeValueAsString(request);
		log.info("University request: {}",jsonString);
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
		log.info("University response: {}",json);
		list = mapper.readValue(json, new TypeReference<List<UniversityResponse>>(){});

		http.disconnect();
		return list;
		}	
	
}
