package student.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="jc_street")
public class Street {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="street_code")
	private Long streetCode;
	@Column(name="street_name")
	@NotEmpty(message = "Название улицы не должно быть пустым")
	private String streetName;
	
	public Long getStreetCode() {
		return streetCode;
	}
	public void setStreetCode(Long streetCode) {
		this.streetCode = streetCode;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	
	
	
}
