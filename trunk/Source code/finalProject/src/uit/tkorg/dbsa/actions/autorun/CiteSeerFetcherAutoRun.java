/**
 * 
 */
package uit.tkorg.dbsa.actions.autorun;

import java.io.IOException;
import java.util.ArrayList;

import uit.tkorg.dbsa.model.DBSAPublication;

/**
 * @author tiendv
 *
 */
public class CiteSeerFetcherAutoRun {
	
public static ArrayList<DBSAPublication> getNewDBSAPubicationNew (ArrayList<String> listAuthorname){
		
		ArrayList<DBSAPublication> newDBSAPublication = new ArrayList<DBSAPublication>();
		for (int i=0 ; i< listAuthorname.size(); i++) {
			ArrayList<DBSAPublication> temp = new ArrayList<DBSAPublication>();
			temp = getDBSAPublication(listAuthorname.get(i));
			
			if(temp.isEmpty()== false)
				for(int j =0; j< temp.size() ; j++) {
					newDBSAPublication.add(temp.get(j));
				}
				
		}
		return newDBSAPublication;
	}
	/**
	 * 
	 * @param authorName: Name of Author
	 * @return : arraylist DBSAPublication from ACM Digital Library new
	 */
	public static ArrayList<DBSAPublication> getDBSAPublication (String authorName) {
		
		// Get Publication from Digital Library
		
		uit.tkorg.dbsa.cores.fetchers.CiteSeerXFetcher.setFlagAutorun(true);
		ArrayList<DBSAPublication> newDBSAPublication = new ArrayList<DBSAPublication>();
		uit.tkorg.dbsa.cores.fetchers.CiteSeerXFetcher.processQuery(authorName, 0);
		newDBSAPublication = uit.tkorg.dbsa.cores.fetchers.ACMFetcher.getNewDBSAPublication();
		
		// Check Exit in Database
		ArrayList<Integer> indexDBSAPublicationExitInDatabase = new ArrayList<Integer>();
		indexDBSAPublicationExitInDatabase = uit.tkorg.dbsa.actions.database.CheckExist.CheckTitlePublications(newDBSAPublication);
		// Remove Item exit in database
		for (int i= 0; i< indexDBSAPublicationExitInDatabase.size(); i++) {
			newDBSAPublication.remove(indexDBSAPublicationExitInDatabase.get(i));
		}
		return newDBSAPublication;	
	}

}
