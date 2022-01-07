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
import student.domain.ChildRequest;
import student.domain.ChildResponse;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.request.RegisterOfficeRequest;
import student.response.RegisterOfficeResponse;
import student.util.ConnectAndCheckMarriage;

@Service
public class RequestRegisterService {
	
	private ConnectAndCheckMarriage checkMarriage = new ConnectAndCheckMarriage();
	
	@Autowired
	private StudentOrderRepository studentDao;
	@Autowired
	private StudentOrderChildRepository childDao;
	

	
	@Transactional
	public void buildRegisterOfficeRequest(Long id) {
		Optional<List<StudentOrderChild>> childs;
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
			
			childs = childDao.findAllByStudentOrder(so);
			if(childs.isPresent()) {
				List<ChildRequest> listChilds = new LinkedList<>();
				for(StudentOrderChild soc: childs.get()) {
					ChildRequest req = new ChildRequest();
					req.setFirstName(soc.getChild().getGivenName());
					req.setDateOfBirth(soc.getChild().getDateOfBirth());
					req.setIssueDate(soc.getChild().getCertificateDate());
					req.setLastName(soc.getChild().getSurName());
					req.setNumberCertificate(soc.getChild().getChildCertificate());
					req.setPatronymic(soc.getChild().getPatronymic());
					
					listChilds.add(req);
				}
				request.setChilds(listChilds);
			}
			try {
				saveRegisterOfficeInfo(checkMarriage.checkMarriage(request), so, childs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}



	private void saveRegisterOfficeInfo(RegisterOfficeResponse checkMarriage, StudentOrder so, Optional<List<StudentOrderChild>> childs) {
		boolean marriage = checkMarriage.isExistingMarriage();
		boolean wife = checkMarriage.isExistingMother();
		boolean husband = checkMarriage.isExistingFather();
		if(wife||husband||marriage) {
			so.setMarriage(marriage);
			Adult wifeSo = so.getWife();
			Adult husbandSo = so.getHusband();
			wifeSo.setCheckRegisterMarriage(wife);
			husbandSo.setCheckRegisterMarriage(husband);;
			so.setWife(wifeSo);
			so.setHusband(husbandSo);
			if(wife&&husband) {
				for(ChildResponse cr : checkMarriage.getChilds()) {
					if(childs.isPresent()) {
						for(StudentOrderChild soc: childs.get()) {
							if(soc.getChild().getGivenName().equals(cr.getName())) {
								soc.getChild().setCheckChildRegister(true);
								childDao.save(soc);
								break;
							}
						}
					
					} else {
						break;
					}
				}
			}
			studentDao.save(so);
		}
	}
	
	
	
	
}
