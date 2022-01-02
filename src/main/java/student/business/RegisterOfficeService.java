package student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.dao.RegisterOfficeRepository;
import student.domain.RegisterOffice;

@Service
public class RegisterOfficeService {

	@Autowired
	private RegisterOfficeRepository registerRepo;
	
	
	public List<RegisterOffice> findListRegister(){
		return registerRepo.findAll(); 
	}


	public RegisterOffice findRegisterOfficeById(Long id) {
		return registerRepo.findById(id).get();
	}


	public void saveRegisterOffice(RegisterOffice ro) {
		registerRepo.save(ro);
	}


	public void removeRegisterOfficeById(Long id) {
		registerRepo.deleteById(id);
	}
}
