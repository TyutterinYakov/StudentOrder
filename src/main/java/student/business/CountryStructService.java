package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.dao.CountryStructRepository;
import student.domain.CountryStruct;

@Service
public class CountryStructService {
	
	@Autowired
	private CountryStructRepository structDao;
	
	public List<CountryStruct> findAllStruct(){
		return structDao.findAll();
	}

	public CountryStruct findStructById(String id) {
		
		return structDao.findById(id).get();
	}

	public void saveCountryStruct(CountryStruct str) {
		structDao.save(str);
		
	}

	public void removeStructById(String id) {
		structDao.deleteById(id);
		
	}
}
