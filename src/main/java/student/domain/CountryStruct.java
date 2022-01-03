package student.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jc_country_struct")
public class CountryStruct {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="area_id")
	private Long areaId;
	@Column(name="area_name")
	private String areaName;
	
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	
}
