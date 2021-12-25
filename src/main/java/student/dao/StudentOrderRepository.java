package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrder;

@Repository
public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long>{
	
	
}
