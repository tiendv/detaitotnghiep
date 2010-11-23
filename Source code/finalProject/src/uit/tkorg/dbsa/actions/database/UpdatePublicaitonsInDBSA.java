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

	public class UpdatePublicaitonsInDBSA {
		 
		
		/**
		 * 
		 * @param updateList : list DBSAPublication for update
		 */
		public void updateListData (List<DBSAPublication> updateList) {
			for(int i =0 ; i < updateList.size();i++) {
				updatePublication(updateList.get(i));
			}
			
		}
		/**
		 * 
		 * @param pubUpdate : Publication for update 
		 */
		public void updatePublication (DBSAPublication pubUpdate) {
			Session session = null;
			try
			{
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				DBSAPublication temp = (DBSAPublication) session.get(DBSAPublication.class , pubUpdate.getId());
				temp.setAuthors(pubUpdate.getAuthors());
				temp.setAbstractPub(pubUpdate.getAbstractPub());
				temp.setPublisher(pubUpdate.getPublisher());
				temp.setSbj_id(pubUpdate.getSbj_id());
				temp.setLinks(pubUpdate.getLinks());
				temp.setTitle(pubUpdate.getTitle());
				temp.setYear(pubUpdate.getYear());
				tx.commit();
			} finally {
				session.close();
			}
			
	}
}
