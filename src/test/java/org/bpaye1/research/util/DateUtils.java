package org.bpaye1.research.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date newDate(int year, int month, int date){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		calendar.set(Calendar.MILLISECOND, 0);  
		return calendar.getTime();
	}

}
