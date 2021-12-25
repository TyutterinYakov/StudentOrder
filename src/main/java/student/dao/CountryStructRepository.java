package student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.domain.CountryStruct;

@Repository
public interface CountryStructRepository extends JpaRepository<CountryStruct, String>  {

}
