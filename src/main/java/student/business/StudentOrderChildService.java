package student.business;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderChildRepository;
import student.dao.StudentOrderRepository;
import student.domain.StudentOrder;
import student.domain.StudentOrderChild;

@Service
public class StudentOrderChildService {

	@Autowired
	private StudentOrderChildRepository childDao;
	@Autowired
	private StudentOrderRepository orderDao;
	
	
	@Transactional
	public Optional<StudentOrderChild> getChildOrderById(Long id){
		return childDao.findById(id);
	}

	
}
