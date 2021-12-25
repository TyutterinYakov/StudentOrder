package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{

}
