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
		Boolean flag = false;
		ArrayList<String> listResult = new ArrayList<String>();
		ArrayList<String> listResult2 = new ArrayList<String>();
		GoogleSearchEngine search = new GoogleSearchEngine();
		String query = "\"home page\","+"\""+authorname+"\""; // search "homepage","authorname"
		String query2 = authorname;// make query only authorname
		System.out.printf("String truy van "+query+"\n");
		
		listResult = search.makeQuery(query, 20);
		if(listResult.isEmpty()) {
			flag = false;
			return "";
		}
		else {
			for(int i =0;i < listResult.size();i++) {
				if (processResult(listResult.get(i),authorname) == true) {
					String[] tempArr = listResult.get(i).split("<br>");
					url = tempArr[0];
					flag=true;
					break;
				} 
					
			}
		}
		if(flag == false) {
			listResult2 = search.makeQuery(query2, 20);
			if(listResult2.isEmpty()) {
				flag = false;
				return "";
			}
			else {
				for(int i =0;i < listResult2.size();i++) {
					if (processResult(listResult2.get(i),authorname) == true) {
						String[] tempArr = listResult2.get(i).split("<br>");
						url = tempArr[0];
						flag=true;
						break;
					} 
						
				}
			}
		}
		return url;
	} 
	/**
	 * 
	 * @param urlWithTitle
	 * @param authorname
	 * @return
	 */
	public static Boolean processResult (String urlWithTitle , String authorname) {
		//http://wwwinfo.deis.unical.it/trunfio/<br>Paolo Trunfio - Home page
		String[] tempArr = urlWithTitle.split("<br>");
		System.out.println( tempArr[1]);
		String authorHompage = authorname+" "+ "-"+ " "+"Home page";
		// - Personal Page
		String authorPersonal = authorname+" "+ "-"+ " "+"Personal Page";
		//System.out.println("duoi" + authorHompage);
		if(authorHompage.equals( tempArr[1] )== true || authorname.equals(tempArr[1])|| authorPersonal.equals(tempArr[1]))
			return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		String url =getHomePage("Amelia Carlson");
		if(url != null)
			System.out.println("Home page la:"+url);
		else
			System.out.println("Khogn tim thay home page");
	}


}
