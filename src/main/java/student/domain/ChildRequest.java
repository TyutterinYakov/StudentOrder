package student.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import student.util.LocalDateStringConverter;
import student.util.StringLocalDateConverter;

public class ChildRequest {
	
	private String firstName;
	private String lastName;
	private String patronymic;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate dateOfBirth;
	private String numberCertificate;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate issueDate;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNumberCertificate() {
		return numberCertificate;
	}
	public void setNumberCertificate(String numberCertificate) {
		this.numberCertificate = numberCertificate;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	
	
	
	
}
