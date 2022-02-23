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
import student.util.ConnectAndCheck;

@Service
public class RequestCityRegisterService {

	private final ConnectAndCheck cityGetResponse;
	private final StudentOrderRepository studentDao;
	private final StudentOrderChildRepository childDao;
	
	
	@Autowired
	public RequestCityRegisterService(ConnectAndCheck cityGetResponse, StudentOrderRepository studentDao,
			StudentOrderChildRepository childDao) {
		super();
		this.cityGetResponse = cityGetResponse;
		this.studentDao = studentDao;
		this.childDao = childDao;
	}



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
					listRequest.add(buildChild(soc));
				}
			}
				checkCityRegister(cityGetResponse.getResponseCity(listRequest), so, socOptional);
		}
	}



	private CityRegisterRequest buildChild(StudentOrderChild soc) {
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
		return request;
	}
	
	
	
	private void checkCityRegister(List<CityRegisterResponse> response, StudentOrder so, Optional<List<StudentOrderChild>> socOptional) {
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
				if(ch.getGivenName().equalsIgnoreCase(resp.getFirstName())&&ch.
						getPatronymic().equalsIgnoreCase(resp.getPatronymic())) 
				{
					ch.setCheckChildCityRegister(true);
					soc.setChild(ch);
					childDao.save(soc);
					
					
				}
			}
		}
	}

	private void buildWife(StudentOrder so, Adult wife, CityRegisterResponse resp) {
		if(wife.getGivenName().equalsIgnoreCase(resp.getFirstName())&&
				wife.getPatronymic().equalsIgnoreCase(resp.getPatronymic())) {
				wife.setCheckCityRegister(true);
				so.setWife(wife);
				studentDao.save(so);
		}
	}

	private void buildHusband(StudentOrder so, Adult husband, CityRegisterResponse resp) {
		if(husband.getGivenName().equalsIgnoreCase(resp.getFirstName())&&
				husband.getPatronymic().equalsIgnoreCase(resp.getPatronymic())) {
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
