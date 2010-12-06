/**
 * 
 */
package uit.tkorg.dbsa.actions.searchs;

import java.util.ArrayList;

import uit.tkorg.dbsa.core.searchengines.GoogleSearchEngine;

/**
 * @author TKORG-PC1
 *
 */
public class SearchAuthorHomePage {
	
	/**
	 * 
	 * @param authorname
	 * @return string of URL, the link of home page Author 
	 */
	public static String getHomePage(String authorname) {
		String url = null;
		ArrayList<String> listResult = new ArrayList<String>();
		GoogleSearchEngine search = new GoogleSearchEngine();
		String query = "\"home page\","+"\""+authorname+"\"";
		
		listResult = search.makeQuery(query, 20);
		return url;
	} 
	public Boolean processResult (String urlWithTitle) {
		
	//http://wwwinfo.deis.unical.it/trunfio/<br>Paolo Trunfio - Home page
		return null;
	}
	


}
