package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	
}
