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

public class SearchPublicationInDatabase {
	public static ArrayList<Publication> getDBLPPublicaitonWithTitle (String keyword) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Query q = session.createQuery("from Publication where  title like '%"+keyword+"%'");
			q.setMaxResults(1000);
			result = (ArrayList<Publication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithTitle (String keyword) {
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from DBSAPublication where  title like '%"+keyword+"%' ");
			 q.setMaxResults(1000);
			 result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<Publication> getDBLPPublicaitonWithAuthorName (String authorName) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Query q = session.createQuery("from Author au where au.author = :var");
			q.setMaxResults(1000);
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
			 Query q = session.createQuery("from DBSAPublication  where authors like '%" + authorName + "%'");
			 q.setMaxResults(1000);
			 //System.out.printf("from DBSAPublication  where authors like '%"+authorName+"%'");
			 ArrayList<DBSAPublication>   result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			
			return result;
			
		} finally {
			//session.close();
		}	
	}
		
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithAbtract (String keyword) {
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Query q = session.createQuery("from DBSAPublication where abstract like '%"+keyword+"%'");
			q.setMaxResults(1000);
			result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<Publication> getDBLPPublicaitonWithPublisher (String keyword) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Query q = session.createQuery("from Publication where publisher like '%"+keyword+"%'");
			q.setMaxResults(1000);
			result = (ArrayList<Publication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithPublisher (String keyword) {
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from DBSAPublication where publisher like '%"+keyword+"%'");
			 q.setMaxResults(1000);
			 result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<Publication> getDBLPPublicaitonWithYear (int year) {
		ArrayList<Publication> result = new ArrayList<Publication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from Publication where year = " + year);
			 q.setMaxResults(1000);
			 result = (ArrayList<Publication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
	
	public static ArrayList<DBSAPublication> getDBSAPublicaitonWithYear (int year) {
		ArrayList<DBSAPublication> result = new ArrayList<DBSAPublication>();
		Session session = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			 Query q = session.createQuery("from DBSAPublication where year = " + year);
			 q.setMaxResults(1000);
			 result = (ArrayList<DBSAPublication>) q.list();
			if(result.isEmpty())
				return null;
			else {
					return result;
			}
			
		} finally {
			session.close();
		}	
	}
}
