package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long>{

}
