package student.business;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderChildRepository;
import student.dao.StudentOrderRepository;
import student.dao.StudentOrderStatusRepository;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;
import student.domain.User;

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


	public void saveChild(StudentOrderChild soc, User user) {
		studentDao.findById(soc.getStudentOrder().getStudentOrderId()).get().setStatus(statusDao.getOne(5L));
		soc.setEmailEdit(user.getEmail());
		childDao.save(soc);
		
	}
	public void saveChildNew(StudentOrderChild soc, User user) {
		Optional<StudentOrder> stud = studentDao.findStudentOrderByEmailAdd(user.getEmail());
		soc.setStudentOrder(stud.get());
		StudentOrder so = stud.get();
		so.setStatus(statusDao.getOne(1L));
		studentDao.save(so);
		
		soc.setEmailAdd(user.getEmail());
		
		childDao.save(soc);
		
	}


	public List<StudentOrderChild> getStudentOrderChildByEmailAdd(User user) {
		return childDao.findAllByEmailAdd(user.getEmail());
	}

	
}
