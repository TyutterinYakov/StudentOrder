package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.PassportOfficeRepository;
import student.domain.PassportOffice;
import student.exception.DataNotFoundException;

@Service
public class PassportOfficeService {

	@Autowired
	private PassportOfficeRepository passportDao;
	
	
	@Transactional
	public List<PassportOffice> findListPassportOffice(){
		return passportDao.findAll();
	}


	public void savePassport(PassportOffice pass) {
		passportDao.save(pass);
		
	}


	public void removePassportById(Long id) {
		passportDao.deleteById(id);
		
	}


	public Object getPassportById(Long id) throws DataNotFoundException {
		return passportDao.findById(id).orElseThrow(()->
				new DataNotFoundException("Паспорт по id не найден!"));
	}
}
