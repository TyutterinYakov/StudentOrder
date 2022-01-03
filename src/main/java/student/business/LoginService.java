package student.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.dao.RoleRepository;
import student.dao.UserRepository;
import student.domain.Role;
import student.domain.User;



@Service
public class LoginService {
	@Autowired
	private BCryptPasswordEncoder cryptPass;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	
	
	@Transactional
	public void register(User user, HttpServletRequest request) throws ServletException {
		String password = user.getPassword();
		user.setPassword(cryptPass.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), password);
	}
}
