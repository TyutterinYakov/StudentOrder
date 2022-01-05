package student.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrder;
import student.domain.StudentOrderChild;

@Repository
public interface StudentOrderChildRepository extends JpaRepository<StudentOrderChild, Long>{

	Optional<List<StudentOrderChild>> findAllByStudentOrder(StudentOrder so);
	List<StudentOrderChild> findAllByEmailAdd(String email);



}
