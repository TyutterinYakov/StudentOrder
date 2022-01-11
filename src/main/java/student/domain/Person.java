package student.domain;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Person {
	@Size(max = 100, min=2, message="Фамилия не может быть меньше 2 символов")
	@NotEmpty(message="Не может быть пустой")
	private String surName;
	@Size(max = 100, min=2, message="Имя не может быть меньше двух символов")
	@NotEmpty(message="Не может быть пустым")
	private String givenName;
	private String patronymic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Укажите дату рождения")
	private LocalDate dateOfBirth;
	@NotNull(message="Укажите адрес")
	private Address address;
	
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
