package student.response;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import student.util.LocalDateStringConverter;
import student.util.StringLocalDateConverter;


public class CityRegisterResponse {
	private String firstName;
	private String patronymic;
	private boolean registered;
	private boolean isTemporal;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate startDate;
	@JsonSerialize(converter = LocalDateStringConverter.class)
	@JsonDeserialize(converter = StringLocalDateConverter.class)
	private LocalDate endDate;
	
	
	
	public boolean isTemporal() {
		return isTemporal;
	}
	public void setTemporal(boolean isTemporal) {
		this.isTemporal = isTemporal;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	@Override
	public String toString() {
		return registered+" "+firstName+" "+patronymic;
	}
	
}
