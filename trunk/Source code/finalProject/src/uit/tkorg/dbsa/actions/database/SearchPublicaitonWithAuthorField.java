/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.Author;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;

/**
 * @author tiendv
 *
 */
public class SearchPublicaitonWithAuthorField {
	
	public static ArrayList<Publication> getDBLPPublicaitonWithAuthorName (String authorName) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from Author au where au.author = :var");
		        q.setString("var", authorName);
		        List authors = q.list();
			if(authors.isEmpty())
				return null;
			else {
						for (int i = 0; i < authors.size(); i++) {
							Author au = (Author) authors.get(i);
							Publication mypub = au.getPublications();
							result.add(mypub);
			        }
				
			}
			return result;
			
		} finally {
			//session.close();
		}	
	}
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithAuthorName (String authorName) {
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from DBSAPublication  where authors like '%"+authorName+"%'");
			//System.out.printf("from DBSAPublication  where authors like '\\%"+authorName+"\\%'");
			 ArrayList<DBSAPublication>   result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			
			return result;
			
		} finally {
			//session.close();
		}	
	}
	
	/*public static void main(String[] args) {
	 	ArrayList<DBSAPublication> test = new ArrayList<DBSAPublication>();
	 	test = getDBSAPublicaitonWithAuthorName("tin huynh");
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		System.out.printf("......................");
	 	}
	}
*/
}
