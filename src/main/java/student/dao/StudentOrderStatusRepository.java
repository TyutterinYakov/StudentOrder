package student.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrderStatus;

@Repository
public interface StudentOrderStatusRepository extends JpaRepository<StudentOrderStatus, Long>{

}
