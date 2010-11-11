/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;

/**
 * @author tiendv
 * Luu cac bai bao xuong database. 
 * Bai bao duoc luu la cac bai bao duoc nguoi dung chon de luu xuong database
 *
 */
public class InsertDBSAPublication {
	public static void InsertPublication(ArrayList<DBSAPublication> pub) {
		Session session = null;
		try
		{
			
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  for(int i = 0; i< pub.size();i++)
		  {
			  Transaction tx = session.beginTransaction();
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

	/*public static void main(String[] args) {
		 	Session session = null;
	        DBSAPublication test = new DBSAPublication();
	       // test.setId(2);
	        test.setTitle(" the Design and Implementation of Object Databases.");
	        test.setYear(2000);
	        try {
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(test);
			 tx.commit();
			System.out.println(test.getId());
	        }finally {
	        	session.flush();
				session.close();
	        }
			
	    }*/


}
