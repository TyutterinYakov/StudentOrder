package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.StreetRepository;
import student.domain.Street;
import student.exception.DataNotFoundException;

@Service
public class StreetService {
	private final StreetRepository streetDao;
	
	@Autowired
	public StreetService(StreetRepository streetDao) {
		super();
		this.streetDao = streetDao;
	}

	public List<Street> findListStreet() {
		return streetDao.findAll();
	}
	
	public void removeStreetById(Long id) {
		streetDao.deleteById(id);
	}
	
	public void updateStreetById(Street street) {
		streetDao.save(street);
	}

	public Street getStreetById(Long id) throws DataNotFoundException {
		return streetDao.findById(id).orElseThrow(()->
			new DataNotFoundException("Улицы с данным id нет"));
	}

	public void saveStreet(Street street) {
		streetDao.save(street);
		
	}
}
