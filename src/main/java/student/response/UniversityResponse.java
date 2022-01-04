package student.response;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import student.util.LocalDateStringConverter;
import student.util.StringLocalDateConverter;

public class UniversityResponse {
	private String documentNumber;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate documentDate;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate expiredDate;
	private String facultyName;
	private String universityName;
	private String studentForm;
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public LocalDate getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(LocalDate documentDate) {
		this.documentDate = documentDate;
	}
	public LocalDate getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getStudentForm() {
		return studentForm;
	}
	public void setStudentForm(String studentForm) {
		this.studentForm = studentForm;
	}

}
