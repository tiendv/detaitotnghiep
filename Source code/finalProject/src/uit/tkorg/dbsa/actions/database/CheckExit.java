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

/**
 * @author tiendv
 *
 */
public class CheckExit {
	static Session session = null;
	
	/**
	 * 
	 * @param pub danh sach cac bai bao dua vao de kiem tra
	 * 
	 * @return ArrayList neu bai bao ton tai
	 *  se tra ve  id tuong ung trong danh sach  kiem tra. Danh sach tra ve rong
	 *  neu bai bao ko co trong du lieu dblp
	 */
	private ArrayList<Integer> CheckTitlePublications(ArrayList<DBSAPublication> pub) {
		ArrayList<Integer> result = null;
		for(int i =0; i<pub.size();i++){
			if(CheckTitilePublication(pub.get(i).getTitle()) == true)
				result.add(i);
		}
		return result;	
	}
	/**
	 * 
	 * @param tittle : title cua bai bao dua vao kiem tra
	 * @return : true neu bai bao co trong dblp
	 */
	private static Boolean CheckTitilePublication(String tittle) {
		try
		{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from Publication pub where pub.title = :var");
	      q.setString("var", tittle);
	      List result = q.list();
	      if(result.isEmpty())
	    	  return false;// bai bao khong co trong dblp
	      
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
			}
		return true;//// bai bao co trong dblp
		
	}
	private static Boolean CheckYearPublication(DBSAPublication publ) {
		try
		{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from Publication pub where pub.title = :var and pub.year =?");
	      q.setString("var", publ.getPublisher());
	      q.setInteger("?", publ.getYear());
	      List result = q.list();
	      if(result.isEmpty())
	    	  return false;// it Contanin
	      
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
			}
		return true;
		
	}

}
