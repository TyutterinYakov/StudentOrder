package student.business;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderRepository;
import student.domain.Adult;
import student.domain.StudentOrder;
import student.request.UniversityRequest;
import student.response.UniversityResponse;
import student.util.ConnectAndCheckUniversity;

@Service
public class RequestUniversityService {
	
	@Autowired
	private StudentOrderRepository studentDao;
	
	
	@Transactional
	public void buildWifeUniversityRequest(Long id) {
		Optional<StudentOrder> so = studentDao.findById(id);
		if(so.isPresent()) {
			StudentOrder sor = so.get();
			Adult w = sor.getWife();
			boolean statusCheck = buildAdult(w);
			Adult wife = sor.getWife();
			wife.setCheckUniversity(statusCheck);
			sor.setWife(wife);
			studentDao.save(sor);
			
		}
	}	
	@Transactional
	public void buildHusbandUniversityRequest(Long id) {
		Optional<StudentOrder> so = studentDao.findById(id);
		if(so.isPresent()) {
			StudentOrder sor = so.get();
			Adult h = sor.getHusband();
			boolean statusCheck = buildAdult(h);
			Adult husband = sor.getHusband();
			husband.setCheckUniversity(statusCheck);
			sor.setHusband(husband);
			studentDao.save(sor);
			
		}
	}
	private boolean buildAdult(Adult a) {
		UniversityRequest request = new UniversityRequest();
		request.setFirstName(a.getGivenName());
		request.setLastName(a.getSurName());
		request.setMiddleName(a.getPatronymic());
		request.setDateOfBirth(a.getDateOfBirth());
		request.setPassportDate(a.getIssueDate());
		request.setPassportNumber(a.getPassportNumber());
		request.setPassportSeria(a.getPassportSeria());
		boolean statusCheck = checkStudentResponse(studentInfo(request));
		return statusCheck;
	}
	
	private boolean checkStudentResponse(List<UniversityResponse> resp) {
		if(resp!=null) {
		for(int i=0; i<resp.size(); i++) {
			if(resp.get(i).getExpiredDate()==null) {
				return true;
			}
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
