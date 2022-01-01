package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderRepository;
import student.dao.StudentOrderStatusRepository;
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
	
}
