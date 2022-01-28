package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StudentOrderStatusRepository;
import student.domain.StudentOrderStatus;
import student.exception.DataNotFoundException;

@Service
public class StatusService {
	
	@Autowired
	private StudentOrderStatusRepository statusDao;
	
	
	@Transactional
	public List<StudentOrderStatus> getListStatus(){
		
		return statusDao.findAll();
	}


	public void removeStatusById(Long id) {
		statusDao.deleteById(id);
		
	}


	public void saveStatus(StudentOrderStatus status) {
		statusDao.save(status);
		
	}


	public StudentOrderStatus getStatusById(Long id) throws DataNotFoundException {
		
		return statusDao.findById(id).orElseThrow(()->new DataNotFoundException("Статус с данным id не найден"));
	}
}
