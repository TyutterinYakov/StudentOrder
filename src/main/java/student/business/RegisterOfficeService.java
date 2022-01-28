package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.dao.RegisterOfficeRepository;
import student.domain.RegisterOffice;
import student.exception.DataNotFoundException;

@Service
public class RegisterOfficeService {

	@Autowired
	private RegisterOfficeRepository registerRepo;
	
	
	public List<RegisterOffice> findListRegister(){
		return registerRepo.findAll(); 
	}


	public RegisterOffice findRegisterOfficeById(Long id) throws DataNotFoundException {
		return registerRepo.findById(id).orElseThrow(()->
		new DataNotFoundException("Зал регистрации не найден по id"));
	}


	public void saveRegisterOffice(RegisterOffice ro) {
		registerRepo.save(ro);
	}


	public void removeRegisterOfficeById(Long id) {
		registerRepo.deleteById(id);
	}
}
