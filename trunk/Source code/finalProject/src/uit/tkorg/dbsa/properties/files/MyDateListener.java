package uit.tkorg.dbsa.properties.files;

import java.util.Calendar;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;

public class MyDateListener implements DateListener {

	private static String _date;
	public void dateChanged(DateEvent e) {
		Calendar c = e.getSelectedDate();
		if (c != null) {
			System.out.println();			
			
			String date = TimeShift.formatDate(c.getTime() ,"yyyy-MM-dd");
			
			setDateSelected(date);
			
		} else {
			System.out.println("No time selected.");
		}
	}
	
	public static String getDateSelected(){
		return _date;
	}
	
	public static void setDateSelected(String value){
		_date = value;
	}

}
