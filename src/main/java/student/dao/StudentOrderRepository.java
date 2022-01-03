package student.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrder;
import student.domain.User;

@Repository
public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long>{

	Optional<StudentOrder> findStudentOrderByEmailAdd(String email);
	
}
