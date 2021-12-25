package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.PassportOffice;

@Repository
public interface PassportOfficeRepository extends JpaRepository<PassportOffice, Long> {

}
