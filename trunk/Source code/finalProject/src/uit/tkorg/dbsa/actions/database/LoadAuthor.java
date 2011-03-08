/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.Author;

/**
 * @author tiendv
 *
 */
public class LoadAuthor {
	/**
	 * 
	 * @param maximum : number of author want to show from DBLP author
	 * @return
	 */
	public static ArrayList<Author> getAuthor(int maximum){
		Session session = null;
		try {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		org.hibernate.Query q = session.createQuery("from Author where id < "+maximum);
		ArrayList<Author>   result = (ArrayList<Author>) q.list();
		if(result.isEmpty())
			return null;
		
		return result;
		} finally {
			session.close();
		}
	}

/*	public static void main(String[] args) {
	 	ArrayList<Author> test =  getAuthor(50);
	 	
	 	if(test.isEmpty())
	 	System.out.print("romg");
	 	else {
	 		for( int i=0; i< test.size();i++){
	 			
		 		Author test1 = test.get(i);
		 		System.out.println( test1.getAuthor()+"\n");
		 		//System.out.println("ID cua tac gia bai bao:"+ test1.getAuthorNum());
		 		}
	 		
	 	}
	}*/

}
