/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uit.tkorg.dbsa.cores.hibernate.HibernateUtil;
import uit.tkorg.dbsa.model.Subject;

/**
 * @author cuongnp
 *
 */
public class DeleteSubject{
	
	public void deleteListSubject (List<Subject> listSubjectDelete) {
		for(int i = 0; i< listSubjectDelete.size() ; i++) {
			deleteSubject(listSubjectDelete.get(i));
		}
		
	}
	/**
	 * 
	 * @param subject  :  delete subject;
	 */
	public void deleteSubject (Subject subject) {
		Session session = null;
		try
		{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Subject temp = (Subject) session.get(Subject.class , subject.getId());
			session.delete(temp);
			tx.commit();
		} finally {
			session.close();
		}
	}
	
}
