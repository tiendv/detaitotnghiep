
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.Subject;;

/**
 * @author tiendv
 *
 */
public class InsertSubject {

	public static void InsertSubjectOfPublication(ArrayList<Subject> sb) {
		Session session = null;
		try
		{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  for(int i = 0; i< sb.size();i++)
		  {
			  Transaction tx = session.beginTransaction();
			  session.save(sb.get(i));
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
}
