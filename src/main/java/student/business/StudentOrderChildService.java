package student.business;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderChildRepository;
import student.domain.StudentOrderChild;

@Service
public class StudentOrderChildService {

	@Autowired
	private StudentOrderChildRepository childDao;
	
	
	@Transactional
	public Optional<StudentOrderChild> getChildOrderById(Long id){
		return childDao.findById(id);
	}
	
}
