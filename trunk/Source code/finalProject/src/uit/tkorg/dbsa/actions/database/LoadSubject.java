/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.Subject;

/**
 * @author tiendv
 * @modifer cuongnp
 */
public class LoadSubject {
	/**
	 * 
	 * @return List subject in DBLP
	 */
	
	public static ArrayList<Subject> getSubject (){
		Session session = null;
		try {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		org.hibernate.Query q = session.createQuery("from Subject");
		ArrayList<Subject>   result = (ArrayList<Subject>) q.list();
		if(result.isEmpty())
			return null;
		
		return result;
		} finally {
			session.close();
		}
	}
	
	/*public static void main(String[] args) {
	 	List<Subject> test = getSubject();
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		Subject test1 = test.get(0);
	 		System.out.println("ID:"+ test1.getId());
	 		System.out.println("Name:"+ test1.getSbj_name());
	 		
	 	}
	}
	
*/
}
