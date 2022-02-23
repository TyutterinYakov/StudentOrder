package student.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.dao.CountryStructRepository;
import student.domain.CountryStruct;
import student.exception.DataNotFoundException;

@Service

public class CountryStructService {
	
	private final CountryStructRepository structDao;
	
	@Autowired
	public CountryStructService(CountryStructRepository structDao) {
		super();
		this.structDao = structDao;
	}
	

	public List<CountryStruct> findAllStruct(){
		return structDao.findAll();
	}

	public CountryStruct findStructById(Long id) throws DataNotFoundException {
		CountryStruct countryStruct = structDao.findById(id).orElseThrow(()->
			new DataNotFoundException("Country struct not found by Id"));
		return countryStruct;
	}

	public void saveCountryStruct(CountryStruct str) {
		structDao.save(str);
		
	}

	public void removeStructById(Long id) {
		structDao.deleteById(id);
		
	}
}
