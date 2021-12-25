package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.RegisterOffice;

@Repository
public interface RegisterOfficeRepository extends JpaRepository<RegisterOffice, Long>{

}
