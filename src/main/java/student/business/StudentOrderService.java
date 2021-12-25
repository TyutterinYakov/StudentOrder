package student.business;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StreetRepository;
import student.dao.StudentOrderRepository;
import student.domain.Address;
import student.domain.Person;
import student.domain.Street;
import student.domain.StudentOrder;

@Service
public class StudentOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentOrderService.class);
	
	@Autowired
	private StudentOrderRepository orderDao;
	@Autowired
	private StreetRepository streetDao;
	
	
	@Transactional
	public void testSave() {
		StudentOrder so = new StudentOrder();
		so.setHusband(buildPerson(false));
		so.setWife(buildPerson(true));
		orderDao.save(so);
	}
	@Transactional
	public void testGet() {
		
		List<StudentOrder> orders = orderDao.findAll();
		logger.info("testGet");
		logger.info(orders.get(0).getWife().getSurName());
	}
	
	private Person buildPerson(boolean wife) {
		Person p = new Person();
		Address ad = new Address();
		Street one = streetDao.getOne(2L);
		ad.setStreet(one);
		ad.setPostCode("3333");
		ad.setBuilding("3A");
		ad.setExtension("4");
		ad.setApartment("237");
		p.setDateOfBirth(LocalDate.now());
		if(wife) {
			p.setGivenName("...");
			p.setSurName("Тюттерина");
			p.setPatronymic("...");
			p.setAddress(ad);
		} else {
			p.setGivenName("Яков");
			p.setSurName("Тюттерин");
			p.setPatronymic("Николаевич");
			p.setAddress(ad);
		}
		return p;
	}
}
