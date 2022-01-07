package student.business;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.CountryStructRepository;
import student.dao.PassportOfficeRepository;
import student.dao.RegisterOfficeRepository;
import student.dao.StreetRepository;
import student.dao.StudentOrderRepository;
import student.dao.StudentOrderStatusRepository;
import student.dao.UniversityRepository;
import student.domain.Adult;
import student.domain.CountryStruct;
import student.domain.PassportOffice;
import student.domain.RegisterOffice;
import student.domain.Street;
import student.domain.StudentOrder;
import student.domain.StudentOrderStatus;
import student.domain.University;
import student.domain.User;

@Service
public class StudentService {

	@Autowired
	private StudentOrderRepository studentDao;
	@Autowired
	private StudentOrderStatusRepository statusDao;
	@Autowired
	private PassportOfficeRepository passportDao;
	@Autowired
	private UniversityRepository universityDao;
	@Autowired
	private StreetRepository streetDao;
	@Autowired
	private RegisterOfficeRepository registerDao;
	@Autowired
	private CountryStructRepository countryDao;

	
	@Transactional
	public List<StudentOrder> getAllStudentOrder(){
		List<StudentOrder> studs = studentDao.findAll();
		
		
		return studs;
	}
	
	@Transactional
	public StudentOrderStatus getStatus() {
		return statusDao.getOne(1L);
	}
	
	@Transactional
	public Adult getWifeByStudentOrderId(Long id) {
		
		return studentDao.findById(id).get().getWife();
	}
	@Transactional
	public Adult getHusbandByStudentOrderId(Long id) {
		
		return studentDao.findById(id).get().getHusband();
	}

	@Transactional
	public void removeStudentOrderById(Long id) {
		studentDao.deleteById(id);
		
	}
	@Transactional
	public List<StudentOrder> getListStudentOrderById(Long id) {
		Optional<StudentOrder> so = studentDao.findById(id);
		List<StudentOrder> lists = new LinkedList<>();
		if(so.isPresent()) {
			lists.add(so.get());
		return lists;
		}
		return lists;
	}
	
	@Transactional
	public Optional<StudentOrder> getStudentOrderById(Long id){
		return studentDao.findById(id);
	}

	public void updateStudentOrder(StudentOrder so, User us) {
		//so.setStudentOrderDate(studentDao.findById(so.getStudentOrderId()).get().getStudentOrderDate());
		so.setStatus(statusDao.getOne(5L));
		so.setEmailAdd(studentDao.findById(so.getStudentOrderId()).get().getEmailAdd());
		so.setEmailEdit(us.getEmail());
		so.setStudentOrderDate(LocalDateTime.now());
		studentDao.save(so);
		//statusDao.save(studentDao.findById(so.getStudentOrderId()).get().getStatus());
		
	}

	public List<PassportOffice> getListPassportOffice() {
		return passportDao.findAll();
	}

	public List<RegisterOffice> getListRegisterOffice() {
		
		return registerDao.findAll();
	}

	public List<University> getListUnivers() {
		
		return universityDao.findAll();
	}

	public List<Street> findListStreet() {
		return streetDao.findAll();
	}

	public List<StudentOrderStatus> findListStatus() {
		return statusDao.findAll();
	}
	
	public List<CountryStruct> findListCountry(){
		return countryDao.findAll();
	}

	public void saveStudentOrder(StudentOrder so, User us) {
		so.setStudentOrderDate(LocalDateTime.now());
		so.setStatus(statusDao.getOne(6L));
		so.setEmailAdd(us.getEmail());
		studentDao.save(so);
		
	}

	public Optional<StudentOrder> getStudentOrderByEmailAdd(User user) {
		Optional<StudentOrder> stud = studentDao.findStudentOrderByEmailAdd(user.getEmail());
		return stud;
	}

	public void checkAllOrders() {
		Set<Long> sos = new HashSet<>();
		
		sos = studentDao.checkAllStatus();
		if(!sos.isEmpty()) {
			for(Long id: sos) {
				Optional<StudentOrder> soOptional = studentDao.findById(id);
				if(soOptional.isPresent()) {
					StudentOrder so = soOptional.get();
					Optional<StudentOrderStatus> statusOptional = statusDao.findById(3L);
					if(statusOptional.isPresent()) {
					so.setStatus(statusOptional.get());
					studentDao.save(so);
					}
				}
			}
		}
	}
	
}
