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
 * Kiem tra xem bai bao da co trong dblp chua:
 * B1 : Kiem tra title- > neu chua ton tai -> bai bao moi : ket thuc qua trinh kiem tra
 *                        neu da ton tai --> B2
 * B2 : kiem tra nam xuat ban (select cac bai 
 * bao co title va nam xuat ban )
 *    neu khong co --> bai bao moi : ket thuc qua trinh kiem tra
 *    neu co       --> B3 (neu thay can thiet).
 * B3 : kiem tra ten tac gia dau tien cua bai bao 
 * ( de phu hop voi database)
 *    neu khong co --> bai bao moi : them vao du lieu ket thuc qua trinh kiem tra
 *    neu co       --> bai bao cu : ket thuc qua trinh kiem tra
 *Kiem tra xem bai bao da co trong du lieu cua dbsa chua 
 *
 * Kiem tra thong tin gom title, nam xuat ban va ten tac gia bai bao.
 */
public class CheckExist {
	static Session session = null;
	
	/**
	 * 
	 * @param pub danh sach cac bai bao dua vao de kiem tra
	 * 
	 * @return ArrayList neu bai bao ton tai
	 *  se tra ve  id tuong ung trong danh sach  kiem tra. Danh sach tra ve rong
	 *  neu bai bao ko co trong du lieu dblp
	 */
	public ArrayList<Integer> CheckTitlePublications(ArrayList<DBSAPublication> pub) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i =0; i<pub.size();i++){
			if(CheckTitilePublication(pub.get(i).getTitle()) == true || CheckPublicationInDBSA(pub.get(i))== true) {
				result.add(i);
			}
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
	/**
	 * 
	 * @param pub : arraylist pub to check
	 * @return list : pub exit
	 */
	public ArrayList<Integer> CheckTitleSignaturePublications(ArrayList<DBSAPublication> pub) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i =0; i<pub.size();i++){
			if(CheckTitileSignaturePublication(pub.get(i).getTitle()) == true || CheckPublicationInDBSA(pub.get(i))== true) {
				result.add(i);
			}
		}
		return result;	
	}
	
	/**
	 * 
	 * @param titleSignature : tua de cua bai bao khong co cac ki tu dac biet bao gom:
	 *  \ \\ . , ? - ' sau do chuyen sang chu thuong va loai bo khoang trang
	 * @return
	 */
	
	private static Boolean CheckTitileSignaturePublication(String titleSignature) {
		try
		{
		titleSignature = titleSignature.replace(",", "").replace(".","").replace("?","").replace("!","").replace("\\", "").replace("'", "").replace("-", "").replace("\\", "").toLowerCase().replaceAll("\\s+", "");
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from Publication pub where pub.titleSignature = :var");
	      q.setString("var", titleSignature);
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
	/**
	 * 
	 * @param publ: danh sach cac bai trong co titile ton tai trong dblp
	 * @return: true neu bai bao do co cung title va  nam xuat ban trong dblp
	 *  neu co gia tri false thi gia tri do la bai bao co cung ten nhung 
	 *  khac nam xuat ban  ==> bai bao do chua ton tai trong database
	 */
	private static Boolean CheckYearPublication(DBSAPublication publ) {
		try
		{
		//System.out.printf(publ.getTitle());
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from Publication pub where pub.title = :var and pub.year = :ya");
	      q.setString("var", publ.getTitle());
	      q.setInteger("ya", publ.getYear());
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
	/**
	 * 
	 * @param publ
	 * @return
	 */
	private static Boolean CheckAuthorPublication(DBSAPublication publ) {
		try
		{
		//System.out.printf(publ.getTitle());
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from Publication pub where pub.title = :var and pub.year = :ya and pub.author = :au");
	      q.setString("var", publ.getTitle());
	      q.setInteger("ya", publ.getYear());
	      // can phai xu ly lay ten tac gia dau tien thoi
	      q.setString("au", publ.getAuthors());
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
	/**
	 *  Ham kiem tra su ton tai cua bai bao trong du lieu cua DBSA
	 * @param publ: Bai bao tim kiem ve 
	 * @return true neu bai bao da ton tai trong du lieu DBSA roi.
	 * 			false neu bai bao chua ton tai trong DBSA
	 */
	public static Boolean CheckPublicationInDBSA(DBSAPublication publ) {
		try
		{
		//System.out.printf(publ.getTitle());
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  session = sessionFactory.openSession();
		  org.hibernate.Query q = session.createQuery("from DBSAPublication pub where pub.title = :var and pub.year = :ya and pub.authors = :au");
	      q.setString("var", publ.getTitle());
	      q.setInteger("ya", publ.getYear());
	      q.setString("au", publ.getAuthors());
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
