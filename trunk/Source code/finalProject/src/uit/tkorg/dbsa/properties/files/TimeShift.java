package uit.tkorg.dbsa.properties.files;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeShift {
	public static String formatDate(Date date, String format) {
		SimpleDateFormat form = new SimpleDateFormat(format);
		return form.format(date);
	}
	
	public static String formatTime(Date time, String format) {
		SimpleDateFormat form = new SimpleDateFormat(format);  
   		return form.format(time);
	} 
}
