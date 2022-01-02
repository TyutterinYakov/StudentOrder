package student.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import student.dao.StudentOrderRepository;
import student.dao.StudentOrderStatusRepository;
import student.domain.Adult;
import student.domain.StudentOrder;
import student.domain.StudentOrderStatus;

@Service
public class StudentService {

	@Autowired
	private StudentOrderRepository studentDao;
	@Autowired
	private StudentOrderStatusRepository statusDao;
	
	
	@Transactional
	public List<StudentOrder> getAllStudentOrder(){
		List<StudentOrder> studs = studentDao.findAll();
		
		
		return studs;
	}
	
	@Transactional
	public StudentOrderStatus getStatus() {
		return statusDao.getOne(1L);
	}
	
	@Transactional
	public Adult getWifeByStudentOrderId(Long id) {
		
		return studentDao.findById(id).get().getWife();
	}
	@Transactional
	public Adult getHusbandByStudentOrderId(Long id) {
		
		return studentDao.findById(id).get().getHusband();
	}

	@Transactional
	public void removeStudentOrderById(Long id) {
		studentDao.deleteById(id);
		
	}
	
}
