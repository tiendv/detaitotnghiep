/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;

/**
 * @author tiendv
 *
 */
public class SearchPublicaitonWithKeyWordInTitle {
	public static ArrayList<Publication> getDBLPPublicaitonWithTitle (String keyword) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from Publication where title like '%"+keyword+"%'");
			 result = (ArrayList<Publication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithTitle (String keyword) {
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from DBSAPublication where title like '%"+keyword+"%'");
			 result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
/*	public static void main(String[] args) {
	 	ArrayList<DBSAPublication> test = new ArrayList<DBSAPublication>();
	 	test = getDBSAPublicaitonWithAuthorName("tin huynh");
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		System.out.printf("......................");
	 	}
	}*/

}
