package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.StudentOrderChild;

@Repository
public interface StudentOrderChildRepository extends JpaRepository<StudentOrderChild, Long>{

}
