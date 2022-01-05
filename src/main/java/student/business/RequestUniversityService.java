package student.business;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderRepository;
import student.domain.Adult;
import student.domain.StudentOrder;
import student.request.UniversityRequest;
import student.response.UniversityResponse;
import student.util.ConnectAndCheckMarriage;
import student.util.ConnectAndCheckUniversity;

@Service
public class RequestUniversityService {
	private Properties property = new Properties();
	
	@Autowired
	private StudentOrderRepository studentDao;
	
	
	@Transactional
	public void buildWifeUniversityRequest(Long id) {
		Optional<StudentOrder> so = studentDao.findById(id);
		if(so.isPresent()) {
			StudentOrder sor = so.get();
			Adult w = sor.getWife();
			UniversityRequest request = new UniversityRequest();
			request.setFirstName(w.getGivenName());
			request.setLastName(w.getSurName());
			request.setMiddleName(w.getPatronymic());
			request.setDateOfBirth(w.getDateOfBirth());
			request.setPassportDate(w.getIssueDate());
			request.setPassportNumber(w.getPassportNumber());
			request.setPassportSeria(w.getPassportSeria());
			boolean statusCheck = checkStudentResponse(studentInfo(request));
			sor.setCheckUniversityWife(statusCheck);
			studentDao.save(sor);
			
		}
	}	
	@Transactional
	public void buildHusbandUniversityRequest(Long id) {
		Optional<StudentOrder> so = studentDao.findById(id);
		if(so.isPresent()) {
			StudentOrder sor = so.get();
			Adult h = sor.getHusband();
			UniversityRequest request = new UniversityRequest();
			request.setFirstName(h.getGivenName());
			request.setLastName(h.getSurName());
			request.setMiddleName(h.getPatronymic());
			request.setDateOfBirth(h.getDateOfBirth());
			request.setPassportDate(h.getIssueDate());
			request.setPassportNumber(h.getPassportNumber());
			request.setPassportSeria(h.getPassportSeria());
			boolean statusCheck = checkStudentResponse(studentInfo(request));
			sor.setCheckUniversityHusband(statusCheck);
			studentDao.save(sor);
			
		}
	}
	
	private boolean checkStudentResponse(List<UniversityResponse> resp) {
		for(int i=0; i<resp.size(); i++) {
			if(resp.get(i).getExpiredDate()==null) {
				return true;
			}
		}
		return false;
	}
	
	
	
	private List<UniversityResponse> studentInfo(UniversityRequest request) {
		try {
			ConnectAndCheckUniversity uniCon = new ConnectAndCheckUniversity();
			return uniCon.checkUniversity(request);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return null;
	}

	
	

}
