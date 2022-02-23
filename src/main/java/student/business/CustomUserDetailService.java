package student.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import student.dao.UserRepository;
import student.domain.CustomUserDetail;
import student.domain.User;



@Service
public class CustomUserDetailService implements UserDetailsService{

	private final UserRepository userDao;
	
	@Autowired
	public CustomUserDetailService(UserRepository userDao) {
		super();
		this.userDao = userDao;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDao.findUserByEmail(username);
		user.orElseThrow(()->
			new UsernameNotFoundException("Такой пользователь не найден"));
		return user.map(CustomUserDetail::new).get();
	}

}
