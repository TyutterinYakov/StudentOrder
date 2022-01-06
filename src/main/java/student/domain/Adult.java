package student.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class Adult extends Person {
	
	private String passportSeria;
	private String passportNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate issueDate;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private PassportOffice passportOffice;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private University university;
	private String studentNumber;
	private boolean checkCityRegister;
	
	
	
	public boolean isCheckCityRegister() {
		return checkCityRegister;
	}
	public void setCheckCityRegister(boolean checkCityRegister) {
		this.checkCityRegister = checkCityRegister;
	}
	public String getPassportSeria() {
		return passportSeria;
	}
	public void setPassportSeria(String passportSeria) {
		this.passportSeria = passportSeria;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public PassportOffice getPassportOffice() {
		return passportOffice;
	}
	public void setPassportOffice(PassportOffice passportOffice) {
		this.passportOffice = passportOffice;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	
}
