/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;

/**
 * @author tiendv
 * Load paper from dbsa
 *
 */
public class LoadPublicationsFromDBSA {
	 
	public static List<DBSAPublication> getPaper (){
		Session session = null;
		try {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		org.hibernate.Query q = session.createQuery("from DBSAPublication");
		List<DBSAPublication>   result = q.list();
		if(result.isEmpty())
			return null;
		
		return result;
		} finally {
			session.close();
		}
	}

	/*public static void main(String[] args) {
	 	List<DBSAPublication> test = getPaper();
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		DBSAPublication test1 = test.get(0);
	 		System.out.println("Tua de bai bao:"+ test1.getTitle());
	 		System.out.println("Abstract bai bao:"+ test1.getAbstractPub());
	 		
	 	}
	}*/
}
