package student.business;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderChildRepository;
import student.dao.StudentOrderRepository;
import student.dao.StudentOrderStatusRepository;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;

@Service
public class StudentOrderChildService {

	@Autowired
	private StudentOrderChildRepository childDao;
	@Autowired
	private StudentOrderRepository studentDao;
	@Autowired
	private StudentOrderStatusRepository statusDao;
	
	@Transactional
	public Optional<StudentOrderChild> getChildOrderById(Long id){
		return childDao.findById(id);
	}


	public List<StudentOrderChild> getChildOrderByStudentOrderId(StudentOrder so) {
		
		return childDao.findAllByStudentOrder(so);
	}


	public void saveChild(StudentOrderChild soc) {
		studentDao.findById(soc.getStudentOrder().getStudentOrderId()).get().setStatus(statusDao.getOne(5L));;
		childDao.save(soc);
		
	}

	
}
