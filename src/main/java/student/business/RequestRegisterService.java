package student.business;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderRepository;
import student.domain.Adult;
import student.domain.StudentOrder;
import student.request.RegisterOfficeRequest;
import student.response.RegisterOfficeResponse;
import student.util.ConnectAndCheckMarriage;

@Service
public class RequestRegisterService {
	
	private ConnectAndCheckMarriage checkMarriage = new ConnectAndCheckMarriage();
	
	@Autowired
	private StudentOrderRepository studentDao;
	

	
	@Transactional
	public void buildRegisterOfficeRequest(Long id) {
		Optional<StudentOrder> studentOrder = studentDao.findById(id);
		if(studentOrder.isPresent()) {
			StudentOrder so = studentOrder.get();
			RegisterOfficeRequest request = new RegisterOfficeRequest();
			Adult h = so.getHusband(); 
			request.setHusbandDateOfBirth(h.getDateOfBirth());
			request.setHusbandGivenName(h.getGivenName());
			request.setHusbandPassportIssueDate(h.getIssueDate());
			request.setHusbandPassportNumber(h.getPassportNumber());
			request.setHusbandPassportSeria(h.getPassportSeria());
			request.setHusbandPatronymic(h.getPatronymic());
			request.setHusbandSurName(h.getSurName());
			Adult w = so.getWife(); 
			request.setWifeDateOfBirth(w.getDateOfBirth());
			request.setWifeGivenName(w.getGivenName());
			request.setWifePassportIssueDate(w.getIssueDate());
			request.setWifePassportNumber(w.getPassportNumber());
			request.setWifePassportSeria(w.getPassportSeria());
			request.setWifePatronymic(w.getPatronymic());
			request.setWifeSurName(w.getSurName());
			
			request.setMarriageCertificateDate(so.getMarriageDate());
			request.setMarriageCertificateNumber(so.getCertificateNumber());
			try {
				saveRegisterOfficeInfo(checkMarriage.checkMarriage(request), so);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	private void saveRegisterOfficeInfo(RegisterOfficeResponse checkMarriage, StudentOrder so) {
		boolean marriage = checkMarriage.isExistingMarriage();
		boolean wife = checkMarriage.isExistingMother();
		boolean husband = checkMarriage.isExistingFather();
		if(wife||husband||marriage) {
			so.setMarriage(marriage);
			so.setRegisterWife(wife);
			so.setRegisterHusband(husband);
			studentDao.save(so);
		}
	}
	
	
	
	
}
