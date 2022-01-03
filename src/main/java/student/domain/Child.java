package student.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

public class Child extends Person{
	private String childCertificate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate certificateDate;
	@ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="c_register_office_id")
	private RegisterOffice registerOffice;
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
	
	
	
}
