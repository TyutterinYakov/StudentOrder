package student.business;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderChildRepository;
import student.dao.StudentOrderRepository;
import student.domain.Adult;
import student.domain.Child;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.request.CityRegisterRequest;
import student.response.CityRegisterResponse;
import student.util.ConnectAndCheckCityRegister;

@Service
public class RequestCityRegisterService {

	private ConnectAndCheckCityRegister cityGetResponse = new ConnectAndCheckCityRegister();
	@Autowired
	private StudentOrderRepository studentDao;
	@Autowired
	private StudentOrderChildRepository childDao;
	
	@Transactional
	public void buildCityRegisterRequest(Long id) throws IOException {
		List<CityRegisterRequest> listRequest = new LinkedList<>();
		
		Optional<StudentOrder> soOptional =  studentDao.findById(id);
		if(soOptional.isPresent()) {
			StudentOrder so = soOptional.get();
			listRequest.add(buildAdult(so.getWife(), so));
			listRequest.add(buildAdult(so.getHusband(), so));
			
			Optional<List<StudentOrderChild>> socOptional = childDao.findAllByStudentOrder(so);
			if(socOptional.isPresent()) {
				for(StudentOrderChild soc: socOptional.get()) {
					CityRegisterRequest request = new CityRegisterRequest();
					request.setApartment(soc.getChild().getAddress().getApartment());
					request.setExtension(soc.getChild().getAddress().getExtension());
					request.setBuilding(soc.getChild().getAddress().getBuilding());
					request.setStreetName(soc.getChild().getAddress().getStreet().getStreetName());
					request.setDateOfBirth(soc.getChild().getDateOfBirth());
					request.setGivenName(soc.getChild().getGivenName());
					request.setPatronymic(soc.getChild().getPatronymic());
					request.setSurName(soc.getChild().getSurName());
					request.setBirthCertificateDate(soc.getChild().getCertificateDate());
					request.setBirthCertififcate(soc.getChild().getChildCertificate());
					listRequest.add(request);
				}
			}
				CheckCityRegister(cityGetResponse.checkCityRegister(listRequest), so, socOptional);
		}
	}
	
	
	
	private void CheckCityRegister(List<CityRegisterResponse> response, StudentOrder so, Optional<List<StudentOrderChild>> socOptional) {
		Adult wife = so.getWife();
		Adult husband = so.getHusband();
		boolean checkSoc = socOptional.isPresent();
		if(!response.isEmpty()) {
			for(CityRegisterResponse resp: response) {
				if(resp.getEndDate()==null&&resp.isRegistered()&&resp.isTemporal()==false) {
					buildWife(so, wife, resp);
					buildHusband(so, husband, resp);
					buildChild(socOptional, checkSoc, resp);
				}
			}
			
		}
		
		
	}

	private void buildChild(Optional<List<StudentOrderChild>> socOptional, boolean checkSoc,
			CityRegisterResponse resp) {
		if(checkSoc) {
			for(StudentOrderChild soc: socOptional.get()) {
				Child ch = soc.getChild();
				if(ch.getGivenName().equals(resp.getFirstName())&&ch.
						getPatronymic().equals(resp.getPatronymic())) 
				{
					ch.setCheckChildCityRegister(true);
					soc.setChild(ch);
					childDao.save(soc);
					
					
				}
			}
		}
	}

	private void buildWife(StudentOrder so, Adult wife, CityRegisterResponse resp) {
		if(wife.getGivenName().equals(resp.getFirstName())&&
				wife.getPatronymic().equals(resp.getPatronymic())) {
				wife.setCheckCityRegister(true);
				so.setWife(wife);
				studentDao.save(so);
		}
	}

	private void buildHusband(StudentOrder so, Adult husband, CityRegisterResponse resp) {
		if(husband.getGivenName().equals(resp.getFirstName())&&
				husband.getPatronymic().equals(resp.getPatronymic())) {
				husband.setCheckCityRegister(true);
				so.setHusband(husband);
				studentDao.save(so);
		}
	}

	private CityRegisterRequest buildAdult(Adult adult, StudentOrder so) {
		CityRegisterRequest request = new CityRegisterRequest();
		request.setApartment(adult.getAddress().getApartment());
		request.setExtension(adult.getAddress().getExtension());
		request.setBuilding(adult.getAddress().getBuilding());
		request.setStreetName(adult.getAddress().getStreet().getStreetName());
		request.setDateOfBirth(adult.getDateOfBirth());
		request.setGivenName(adult.getGivenName());
		request.setPassportDate(adult.getIssueDate());
		request.setPassportNumber(adult.getPassportNumber());
		request.setPassportSeria(adult.getPassportSeria());
		request.setPatronymic(adult.getPatronymic());
		request.setSurName(adult.getSurName());
		return request;
	}
	

}
