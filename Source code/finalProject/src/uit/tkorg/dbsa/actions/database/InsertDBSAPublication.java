/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.freixas.jcalendar.JCalendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import sun.security.jca.GetInstance;
import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.properties.files.TimeShift;

/**
 * @author tiendv
 *
 * Luu cac bai bao xuong database. 
 * Bai bao duoc luu la cac bai bao duoc nguoi dung chon de luu xuong database
 * @modifer cuongnp
 * add insertPublication function
 */
public class InsertDBSAPublication {
	public static void InsertPublicationList(ArrayList<DBSAPublication> pub) {
		Session session = null;
		try
		{
			
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  for(int i = 0; i < pub.size(); i++)
		  {
			  Transaction tx = session.beginTransaction();
			  
			  Date _date = new Date();
			  
			  Calendar c = Calendar.getInstance();
			  
			  _date = c.getTime();
			  
			  pub.get(i).setMdate(_date);
			  
			  session.save(pub.get(i));
			  tx.commit();
		  }
		  
		  // Show thanh cong cho nguoi dung biet
		 
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			// Actual Public insertion will happen at this step
			session.flush();
			session.close();
			
			}
	}
	
	public static Date getDateNow() {
		String DATE_FORMAT = "yyyy-MM-dd";
		
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    String date = sdf.format(cal.getTime());
	   
	    Date _date = new Date(date);
	    return _date;
	}
	
	/***
	 * insert one paper to database
	 * @param pub
	 */
	public void InsertPublication(DBSAPublication pub) {
		Session session = null;
		try
		{
			
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  
		  Transaction tx = session.beginTransaction();
		  session.save(pub);
		  tx.commit();
		  
		  
		  // Show thanh cong cho nguoi dung biet
		 
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			// Actual Public insertion will happen at this step
			session.flush();
			session.close();
			
			}
	}
	
}
