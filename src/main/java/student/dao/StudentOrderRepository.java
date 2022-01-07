package student.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrder;

@Repository
public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long>{

	Optional<StudentOrder> findStudentOrderByEmailAdd(String email);
	@Query(nativeQuery=true, value="select jc_student_order.student_order_id from jc_student_order "
			+ "inner join jc_student_child "
			+ "on jc_student_order.student_order_id = jc_student_child.student_order_id "
			+ "where jc_student_order.h_check_city_register='true' and "
			+ "jc_student_order.w_check_city_register='true' and "
			+ "jc_student_order.h_check_register_marriage='true' and "
			+ "jc_student_order.w_check_register_marriage='true' and "
			+ "jc_student_order.h_check_university='true' and "
			+ "jc_student_order.w_check_university='true' and "
			+ "jc_student_order.marriage = 'true' and "
			+ "jc_student_order.student_order_status_id!=2 and "
			+ "jc_student_order.student_order_status_id!=3")
	Set<Long> checkAllStatus();
	
}
