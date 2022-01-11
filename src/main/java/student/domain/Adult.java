package student.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class Adult extends Person {
	
	@Size(max = 4, min=4, message="Size passport seria = 4")
	@NotEmpty
	private String passportSeria;
	@Size(max = 6, min=6, message="Size passport number = 6")
	@NotEmpty
	private String passportNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Укажите дату выдачи")
	private LocalDate issueDate;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@NotNull(message="Укажите место выдачи")
	private PassportOffice passportOffice;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@NotNull(message="Укажите место обучения")
	private University university;
	@NotEmpty
	@Size(max = 10, min=6, message="Размер студенческого 6-10")
	private String studentNumber;
	private boolean checkCityRegister;
	private boolean checkUniversity;
	private boolean checkRegisterMarriage;
	
	
	

	public boolean isCheckRegisterMarriage() {
		return checkRegisterMarriage;
	}
	public void setCheckRegisterMarriage(boolean checkRegisterMarriage) {
		this.checkRegisterMarriage = checkRegisterMarriage;
	}
	public boolean isCheckUniversity() {
		return checkUniversity;
	}
	public void setCheckUniversity(boolean checkUniversity) {
		this.checkUniversity = checkUniversity;
	}
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
