package student.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import student.util.LocalDateStringConverter;
import student.util.StringLocalDateConverter;


public class CityRegisterRequest {
	private String surName;
	private String givenName;
	private String patronymic;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate dateOfBirth;
	private String streetName;
	private String building;
	private String extension;
	private String apartment;
	private String passportNumber;
	private String passportSeria;
	private String birthCertififcate;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate passportDate;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate birthCertificateDate;
	
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
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	
	
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getPassportSeria() {
		return passportSeria;
	}
	public void setPassportSeria(String passportSeria) {
		this.passportSeria = passportSeria;
	}
	public String getBirthCertififcate() {
		return birthCertififcate;
	}
	public void setBirthCertififcate(String birthCertififcate) {
		this.birthCertififcate = birthCertififcate;
	}
	public LocalDate getPassportDate() {
		return passportDate;
	}
	public void setPassportDate(LocalDate passportDate) {
		this.passportDate = passportDate;
	}
	public LocalDate getBirthCertificateDate() {
		return birthCertificateDate;
	}
	public void setBirthCertificateDate(LocalDate birthCertificateDate) {
		this.birthCertificateDate = birthCertificateDate;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	@Override
	public String toString() {
		return surName+" "+givenName+" "+patronymic+" "+dateOfBirth+" "+streetName+" "+
				building+" "+extension+" "+apartment;
	}
	
}
