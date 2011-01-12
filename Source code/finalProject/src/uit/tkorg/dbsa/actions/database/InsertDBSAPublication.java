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
