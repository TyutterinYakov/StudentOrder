package student.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringLocalDateConverter extends StdConverter<String, LocalDate> 
{

	@Override
	public LocalDate convert(String value) {
		if(value==null || value.trim().isEmpty()) {
			return null;
		} else {
			return LocalDate.parse(value, DateTimeFormatter.ofPattern(LocalDateStringConverter.Date_FORMAT));
		}
	}

}
