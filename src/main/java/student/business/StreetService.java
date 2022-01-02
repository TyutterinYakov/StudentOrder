package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StreetRepository;
import student.domain.Street;

@Service
public class StreetService {
	@Autowired
	private StreetRepository streetDao;
	
	
	@Transactional
	public List<Street> findListStreet() {
		
		return streetDao.findAll();
	}
	
	@Transactional
	public void removeStreetById(Long id) {
		streetDao.deleteById(id);
	}
	
	@Transactional
	public void updateStreetById(Street street) {
		streetDao.save(street);
	}

	public Street getStreetById(Long id) {
		// TODO Auto-generated method stub
		return streetDao.findById(id).get();
	}

	public void saveStreet(Street street) {
		streetDao.save(street);
		
	}
}
