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
import student.exception.RoleNotFoundException;



@Service
public class LoginService {
	@Autowired
	private BCryptPasswordEncoder cryptPass;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private RoleRepository roleDao;
	
	
	@Transactional
	public boolean register(User user, HttpServletRequest request) throws ServletException, RoleNotFoundException {
		if(!userDao.findUserByEmail(user.getEmail()).isPresent()) {
		String password = user.getPassword();
		user.setPassword(cryptPass.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleDao.findById(2).orElseThrow(()->
			new RoleNotFoundException("Ошибка присваивания роли, роль пользователя не найдена!")));
		user.setRoles(roles);
		userDao.save(user);
		request.login(user.getEmail(), password);
		return true;
		}
		return false;
	}


	
}
