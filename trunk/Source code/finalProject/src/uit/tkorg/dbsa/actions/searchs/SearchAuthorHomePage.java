/**
 * 
 */
package uit.tkorg.dbsa.actions.searchs;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		ArrayList<String> listResult3 = new ArrayList<String>();
		GoogleSearchEngine search = new GoogleSearchEngine();
		String query = "\"home page\","+"\""+authorname+"\""; // search "homepage","authorname"
		String query2 = authorname;// make query only authorname
		String query3 = authorname +","+"home page";
		
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
		if(flag == false) {
			listResult3 = search.makeQuery(query3, 20);
			if(listResult3.isEmpty()) {
				flag = false;
				return "";
			}
			else {
				for(int i =0;i < listResult3.size();i++) {
					if (processResult(listResult3.get(i),authorname) == true) {
						String[] tempArr = listResult3.get(i).split("<br>");
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
		//title : name - Home Page
		
		String authorHomepage = authorname+" "+ "-"+ " "+"Home page";
		
		// title : name's Home Page
		
		String authorHomepage2 = authorname+"&#39;s"+" "+"Home Page";
		
		// title: name's Homepage
		
		String authorHomepage3 = authorname+"&#39;s"+" "+"Homepage";
		
		//System.out.println("Chuoi so sanh"+ authorHomepage2);
		
		//title name's - Homepage
		
		String authorHomepage4 = authorname+" "+ "-"+ " "+"Homepage";
		
		// title: name - Personal Page
		
		String authorPersonal = authorname+" "+ "-"+ " "+"Personal Page";
		
		//title: Home Page of name
		
		String authorHomePage5 = "Home Page of"+" " + authorname;
		
		// title : name's Home page
		
		String authorHomepage6 = authorname+"&#39;s"+" "+"Home page";
		
		// title : name - Homepage
		String authorHomepage7 = authorname+" "+ "-"+ " "+"Homepage";
	
		//System.out.println("duoi" + authorHompage);
		if(authorHomepage.equals( tempArr[1] )== true || authorname.equals(tempArr[1])==true|| authorPersonal.equals(tempArr[1])==true 
				||authorHomepage2.equals(tempArr[1])==true ||authorHomepage3.equals(tempArr[1])==true || authorHomepage4.equals(tempArr[1])==true
				||authorHomePage5.equals(tempArr[1])==true || authorHomepage6.equals(tempArr[1])==true || authorHomepage7.equals(tempArr[1])==true)
			return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		String url =getHomePage("Weiyi Meng");

		if(url != null)
			System.out.println("Home page la:"+url);
		else
			System.out.println("Khogn tim thay home page");
	}


}
