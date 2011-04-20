/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
public class LoadPublicaitonFromDBLP {
	
	public static ArrayList<Publication> getListPaper (int numberofpub){
		ArrayList<Publication> result = new ArrayList<Publication>();
		for(int i =0; i< numberofpub; i++) {
			Publication temp = new Publication();
			temp = getPublicaitonWithID(i);
			result.add(temp);
		}
		return result;
	}	
	public static Publication getPublicaitonWithID (int id) {
		Publication result = new Publication();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query q = session.createQuery("from Publication where id ="+ id);
			ArrayList<Publication>   tempresult = (ArrayList<Publication>) q.list();
			if(tempresult.isEmpty())
				return null;
			
			return result = tempresult.get(0);
		} finally {
			session.close();
		}
		
	}
	public static ArrayList<Publication> getPublicationsWithIDSpace(int from, int to) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query q = session.createQuery("from Publication where id between "+from +" and "+to);
			ArrayList<Publication>   tempresult = (ArrayList<Publication>) q.list();
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
	public static String  getPublicaitonAuthorWith (int id) {
		String listname = new String();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query q = session.createQuery("from Author where id ="+ id);
			ArrayList<Author>   tempresult = (ArrayList<Author>) q.list();
			if(tempresult.isEmpty())
				return null;
			else {
				for(int i= 0; i< tempresult.size();i++){
					listname +=tempresult.get(i).getAuthor()+",";
				}
				return listname;
			}
			
		} finally {
			session.close();
		}
	}
	public static ArrayList<Publication> getPublicationsWithDate(String date) {
		
		// Day format : Year - moth - day
		
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			org.hibernate.Query q = session.createQuery("from Publication where mdate='"+ date+"'");
			System.out.printf("from Publication where mday= '"+ date+"'");
			//JOptionPane.showMessageDialog(null, "from Publication where mday= '"+ date+"'");
			ArrayList<Publication>   tempresult = (ArrayList<Publication>) q.list();
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
	
//	public static void main(String[] args) {
//	 	ArrayList<Publication> test = new ArrayList<Publication>();
//	 	test = LoadPublicaitonFromDBLP.getPublicationsWithDate("2009-04-13");
//	 	
//	 	for(int i = 1; i < test.size(); i++ ){
//	 		System.out.println(" year " + i + " - " + test.get(i).getYear());	
//	 	}
//	 	System.out.printf("title of pub la:"+ test.get(10).getTitle());
//	 	
//	 //	String testauthor = new String();
//	 	
//	 	
//	}

}
