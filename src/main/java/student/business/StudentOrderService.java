package student.business;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderRepository;
import student.domain.Person;
import student.domain.StudentOrder;

@Service
public class StudentOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentOrderService.class);
	
	@Autowired
	private StudentOrderRepository dao;
	
	
	@Transactional
	public void testSave() {
		StudentOrder so = new StudentOrder();
		so.setHusband(buildPerson(false));
		so.setWife(buildPerson(true));
		dao.save(so);
	}
	@Transactional
	public void testGet() {
		
		List<StudentOrder> orders = dao.findAll();
		logger.info("testGet");
		logger.info(orders.get(0).getWife().getSurName());
	}
	
	private Person buildPerson(boolean wife) {
		Person p = new Person();
		p.setDateOfBirth(LocalDate.now());
		if(wife) {
			p.setGivenName("...");
			p.setSurName("Тюттерина");
			p.setPatronymic("...");
		} else {
			p.setGivenName("Яков");
			p.setSurName("Тюттерин");
			p.setPatronymic("Николаевич");
		}
		return p;
	}
}
