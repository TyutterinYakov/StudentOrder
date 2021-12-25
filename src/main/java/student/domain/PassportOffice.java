package student.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jc_passport_office")
public class PassportOffice {
	
	@Id
	@Column(name="p_office_id")
	private Long passportOfficeId;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn(name="p_office_area_id")
	private CountryStruct passportAreaId;
	@Column(name="p_office_name")
	private String passportOfficeName;
	

	public Long getPassportOfficeId() {
		return passportOfficeId;
	}
	public void setPassportOfficeId(Long passportOfficeId) {
		this.passportOfficeId = passportOfficeId;
	}
	public CountryStruct getPassportAreaId() {
		return passportAreaId;
	}
	public void setPassportAreaId(CountryStruct passportAreaId) {
		this.passportAreaId = passportAreaId;
	}
	public String getPassportOfficeName() {
		return passportOfficeName;
	}
	public void setPassportOfficeName(String passportOfficeName) {
		this.passportOfficeName = passportOfficeName;
	}
	

	
	
	
}
