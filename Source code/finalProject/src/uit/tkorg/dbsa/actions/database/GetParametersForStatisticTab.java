
/**
 * @author tiendv
 * Get parameters for static tab
 *
 */

package uit.tkorg.dbsa.actions.database;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
public class GetParametersForStatisticTab {

	public static int getNumberOfPublicationsInDBLP(){ 
		int numberofpublication = 0;
		Session session = null;
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.openSession();
				numberofpublication = ((Long)session.createQuery("select count(*) from Publication").uniqueResult()).intValue();
			} finally {
				session.close();
			}
		return numberofpublication;
		}
	
	public static int getNumberOfPublicationInDBSA(){
		int nuberofpublicationinDBSA = 0;
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			nuberofpublicationinDBSA = ((Long)session.createQuery("select count(*) from DBSAPublication").uniqueResult()).intValue();
		} finally {
			session.close();
		}
		return nuberofpublicationinDBSA;
	}
	
/*	public static void main(String[] args) {
		Session session = null;
		GetParametersForStatisticTab test = new GetParametersForStatisticTab();
		int count =  test.getNumberOfPublicationsInDBLP();
		int count1 =  test.getNumberOfPublicationInDBSA();
		System.out.printf("so ket qua trong dblp la:"+ count);
		System.out.printf("so ket qua trong DBSA la:"+ count1);
 	}*/
}
