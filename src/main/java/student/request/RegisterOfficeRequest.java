package student.request;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import student.domain.ChildRequest;
import student.util.LocalDateStringConverter;
import student.util.StringLocalDateConverter;


public class RegisterOfficeRequest {
	private String husbandSurName;
	private String husbandGivenName;
	private String husbandPatronymic;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate husbandDateOfBirth;
	private String husbandPassportSeria;
	private String husbandPassportNumber;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate husbandPassportIssueDate;
	
	private String wifeSurName;
	private String wifeGivenName;
	private String wifePatronymic;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate wifeDateOfBirth;
	private String wifePassportSeria;
	private String wifePassportNumber;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate wifePassportIssueDate;
	
	private String marriageCertificateNumber;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate marriageCertificateDate;
	private List<ChildRequest> childs;
	
	
	public List<ChildRequest> getChilds() {
		return childs;
	}
	public void setChilds(List<ChildRequest> childs) {
		this.childs = childs;
	}
	public String getHusbandSurName() {
		return husbandSurName;
	}
	public void setHusbandSurName(String husbandSurName) {
		this.husbandSurName = husbandSurName;
	}
	public String getHusbandGivenName() {
		return husbandGivenName;
	}
	public void setHusbandGivenName(String husbandGivenName) {
		this.husbandGivenName = husbandGivenName;
	}
	public String getHusbandPatronymic() {
		return husbandPatronymic;
	}
	public void setHusbandPatronymic(String husbandPatronymic) {
		this.husbandPatronymic = husbandPatronymic;
	}
	public LocalDate getHusbandDateOfBirth() {
		return husbandDateOfBirth;
	}
	public void setHusbandDateOfBirth(LocalDate husbandDateOfBirth) {
		this.husbandDateOfBirth = husbandDateOfBirth;
	}
	public String getHusbandPassportSeria() {
		return husbandPassportSeria;
	}
	public void setHusbandPassportSeria(String husbandPassportSeria) {
		this.husbandPassportSeria = husbandPassportSeria;
	}
	public String getHusbandPassportNumber() {
		return husbandPassportNumber;
	}
	public void setHusbandPassportNumber(String husbandPassportNumber) {
		this.husbandPassportNumber = husbandPassportNumber;
	}
	public LocalDate getHusbandPassportIssueDate() {
		return husbandPassportIssueDate;
	}
	public void setHusbandPassportIssueDate(LocalDate husbandPassportIssueDate) {
		this.husbandPassportIssueDate = husbandPassportIssueDate;
	}
	public String getWifeSurName() {
		return wifeSurName;
	}
	public void setWifeSurName(String wifeSurName) {
		this.wifeSurName = wifeSurName;
	}
	public String getWifeGivenName() {
		return wifeGivenName;
	}
	public void setWifeGivenName(String wifeGivenName) {
		this.wifeGivenName = wifeGivenName;
	}
	public String getWifePatronymic() {
		return wifePatronymic;
	}
	public void setWifePatronymic(String wifePatronymic) {
		this.wifePatronymic = wifePatronymic;
	}
	public LocalDate getWifeDateOfBirth() {
		return wifeDateOfBirth;
	}
	public void setWifeDateOfBirth(LocalDate wifeDateOfBirth) {
		this.wifeDateOfBirth = wifeDateOfBirth;
	}
	public String getWifePassportSeria() {
		return wifePassportSeria;
	}
	public void setWifePassportSeria(String wifePassportSeria) {
		this.wifePassportSeria = wifePassportSeria;
	}
	public String getWifePassportNumber() {
		return wifePassportNumber;
	}
	public void setWifePassportNumber(String wifePassportNumber) {
		this.wifePassportNumber = wifePassportNumber;
	}
	public LocalDate getWifePassportIssueDate() {
		return wifePassportIssueDate;
	}
	public void setWifePassportIssueDate(LocalDate wifePassportIssueDate) {
		this.wifePassportIssueDate = wifePassportIssueDate;
	}
	public String getMarriageCertificateNumber() {
		return marriageCertificateNumber;
	}
	public void setMarriageCertificateNumber(String marriageCertificateNumber) {
		this.marriageCertificateNumber = marriageCertificateNumber;
	}
	public LocalDate getMarriageCertificateDate() {
		return marriageCertificateDate;
	}
	public void setMarriageCertificateDate(LocalDate marriageCertificateDate) {
		this.marriageCertificateDate = marriageCertificateDate;
	}
}
