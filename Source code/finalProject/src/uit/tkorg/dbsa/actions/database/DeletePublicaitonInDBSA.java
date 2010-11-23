/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.DBSAPublication;

/**
 * @author tiendv
 *
 */
public class DeletePublicaitonInDBSA {
	
	public void deleteListPublications (List<DBSAPublication> listPubDelete) {
		for(int i = 0; i<listPubDelete.size() ; i++) {
			deletePublication(listPubDelete.get(i));
		}
		
	}
	/**
	 * 
	 * @param pubDelete : pubfor delete;
	 */
	public void deletePublication (DBSAPublication pubDelete) {
		Session session = null;
		try
		{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DBSAPublication temp = (DBSAPublication) session.get(DBSAPublication.class , pubDelete.getId());
			session.delete(temp);
			tx.commit();
		} finally {
			session.close();
		}
	}

}
