/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;

/**
 * @author tiendv
 * Load paper from dbsa
 *
 */
public class LoadPublicationsFromDBSA {
	 
	@SuppressWarnings("unchecked")
	public static ArrayList<DBSAPublication> getPaper (){
		Session session = null;
		try {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		org.hibernate.Query q = session.createQuery("from DBSAPublication");
		ArrayList<DBSAPublication>   result = (ArrayList<DBSAPublication>) q.list();
		if(result.isEmpty())
			return null;
		
		return result;
		} finally {
			session.close();
		}
	}
public static ArrayList<DBSAPublication> getPublicationsWithDate(String date) {
		
		// Day format : Year - moth - day
		
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query q = session.createQuery("from DBSAPublication where mdate='"+ date+"'");
			ArrayList<DBSAPublication>   tempresult = (ArrayList<DBSAPublication>) q.list();
			if(tempresult.isEmpty())
				return null;
			else {
				for (int i = 0; i< tempresult.size();i++) {
					result.add(tempresult.get(i));
				}
				return result;
			}

		} finally {
			session.close();
		}
		
	}

/*	public static void main(String[] args) {
	 	ArrayList<DBSAPublication> test =  getPaper();
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		DBSAPublication test1 = test.get(0);
	 		System.out.println("Tua de bai bao:"+ test1.getTitle());
	 		System.out.println("Abstract bai bao:"+ test1.getAbstractPub());
	 		
	 	}
	}*/
}
