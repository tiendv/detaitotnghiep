package uit.tkorg.dbsa.core.databasemanagement;

import javax.swing.JOptionPane;

import uit.tkorg.dbsa.actions.database.InsertDBSAPublication;
import uit.tkorg.dbsa.model.DBSAPublication;

public class InsertArtcileToDatabase {
	
	public InsertArtcileToDatabase(){
		
	}
	
	public void insertArtcile(String title, String authors, String link, int year, String abstractArtcile, String publisher){
		
		DBSAPublication dbsaPublication = new DBSAPublication();
		
		dbsaPublication.setTitle(title);
		dbsaPublication.setAuthors(authors);
		dbsaPublication.setLinks(link);
		dbsaPublication.setYear(year);
		dbsaPublication.setAbstractPub(abstractArtcile);
		dbsaPublication.setPublisher(publisher);
		
		InsertDBSAPublication insert = new InsertDBSAPublication();
		insert.InsertPublication(dbsaPublication);
		
		JOptionPane.showMessageDialog(null, "Insert article successful!");
	}
}
