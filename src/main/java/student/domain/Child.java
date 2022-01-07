package student.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

public class Child extends Person{
	private String childCertificate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate certificateDate;
	@ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="c_register_office_id")
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
