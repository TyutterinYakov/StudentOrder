package student.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Child extends Person{
	@NotEmpty(message="Укажите сертификат")
	@Size(max = 15, min=10, message="Сертификат должен быть 10-15 символов")
	private String childCertificate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Дата выдачи не может быть пустой")
	private LocalDate certificateDate;
	@ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="c_register_office_id")
	@NotNull(message="Отделение выдачи не может быть пустым")
	private RegisterOffice registerOffice;
	private boolean checkChildCityRegister;
	private boolean checkChildRegister;
	
	
	
	
	public boolean isCheckChildRegister() {
		return checkChildRegister;
	}
	public void setCheckChildRegister(boolean checkChildRegister) {
		this.checkChildRegister = checkChildRegister;
	}
	public String getChildCertificate() {
		return childCertificate;
	}
	public void setChildCertificate(String childCertificate) {
		this.childCertificate = childCertificate;
	}
	public LocalDate getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(LocalDate certificateDate) {
		this.certificateDate = certificateDate;
	}
	public RegisterOffice getRegisterOffice() {
		return registerOffice;
	}
	public void setRegisterOffice(RegisterOffice registerOffice) {
		this.registerOffice = registerOffice;
	}
	public boolean isCheckChildCityRegister() {
		return checkChildCityRegister;
	}
	public void setCheckChildCityRegister(boolean checkChildCityRegister) {
		this.checkChildCityRegister = checkChildCityRegister;
	}
	
	
	
}
