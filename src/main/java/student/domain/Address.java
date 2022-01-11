package student.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	@Size(max = 8, min=6, message="Посткод может иметь длину 6-8 цифр")
	@NotEmpty
	private String postCode;
	@Size(max = 5, min=1, message="Дом не может иметь больше 5 цифр")
	@NotEmpty
	private String building;
	private String extension;
	private String apartment;
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@NotNull(message="Укажите улицу")
	@Valid
	private Street street;
	
	
	
	public Street getStreet() {
		return street;
	}
	public void setStreet(Street street) {
		this.street = street;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
	
	
	
}
