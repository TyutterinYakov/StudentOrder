package student.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalDateStringConverter extends StdConverter<LocalDate, String> 
{
	static final String Date_FORMAT = "dd.MM.yyyy";
	
	@Override
	public String convert(LocalDate value) {
		
		return value==null ?  "" : value.format(DateTimeFormatter.ofPattern(Date_FORMAT));
	}
	
}
