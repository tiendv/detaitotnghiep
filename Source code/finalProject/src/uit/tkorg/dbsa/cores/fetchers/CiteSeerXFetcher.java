package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import net.sf.jabref.BibtexEntry;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
/**
 * @author Tiger
 * @modify tiendv
 *
 */
public class CiteSeerXFetcher {
	
	private static boolean shouldContinue = true;
	public static String baseURL = "http://citeseerx.ist.psu.edu/search?q=";
	public static String startSearchYearPub = "http://citeseer.ist.psu.edu/search?q=";
	public static String endSearchYearPub = "&submit=Search&sort=rlv&start=";
	private static int resultNumber = 0;
	private static Pattern searchPattern = Pattern.compile("\\d+");
	private static String pages = null;
	 /* search title, author ,table
	 * doc
	 * auth
	 * table
	 */
	
	public static final String typeDoc="docs";
	public static final String typeAuthor="auth";
	public static final String typeTable="table";
	public static final String typeAction="&t=";	
	/**
	 * Append feed action can get a XML-based page
	 * atom
	 * rss
	 */
	public static final String feedRss = "rss"; 
	public static final String feedAtom = "atom";
	public static final String feedAction="&feed=";
	public int i =0;
	
	
	protected SAXParserFactory parserFactory;
	protected SAXParser saxParser;
	public CiteSeerXFetcher(){
		
	}
	
	public boolean processQuery(String keyword/*, String type, String feedType*/){
	
		shouldContinue = true;
		//replace all white-space to '+'
		keyword = keyword.replaceAll("\\s", "+");
				
		String queryString = baseURL + keyword + feedAction + feedAtom + "&sort=rel";
		List<BibtexEntry> entries = new ArrayList<BibtexEntry>();
		System.out.println(queryString);
		
		try {
			
			URL citeseerUrl = new URL(queryString);
			
			HttpURLConnection citeseerConnection = (HttpURLConnection) citeseerUrl.openConnection();
			InputStream inputStream = citeseerConnection.getInputStream();
			
			DefaultHandler handlerBase = new CiteSeerXAtomEntryHandler(entries);
			
            if (saxParser == null)
            	saxParser = SAXParserFactory.newInstance().newSAXParser();
        
            saxParser.parse(inputStream, handlerBase);
            
            int checkResult = 0;
            for(BibtexEntry entry : entries){
            	
            	if(checkResult == 0){
            		String yearURL = startSearchYearPub + keyword + endSearchYearPub ;
	    			URL url1 = new URL(yearURL);
	    			System.out.println(url1);
	    			pages = getResults(url1);
            	}else if(checkResult%10 == 0){
            		resultNumber += 10;
	    			String yearURL = startSearchYearPub + keyword + endSearchYearPub + resultNumber;
	    			URL url1 = new URL(yearURL);
	    			System.out.println(url1);
	    			pages = getResults(url1);
            	}
            	if(checkResult >= FetcherPanel.getCiteResultNumber()){
            		shouldContinue = false;
            		break;
            	}
            	
            	checkResult++;
            	if(shouldContinue == true || checkResult > FetcherPanel.getCiteResultNumber()){
            		
	            	
	            	Set<String> fields = entry.getAllFields();
	            	
	            	for(String f:fields){
	            		System.out.println(f+":"+entry.getField(f));
	            	}	
	            	
	            	DBSAApplication.fetcherResultPanel.setRowNumber(1);
	            	
	            	if(entry.getField(DBSAApplicationConst.TITLE) == null){
	            		entry.setField((DBSAApplicationConst.TITLE), "");
	            	}
	            	DBSAApplication.fetcherResultPanel.setTitle(entry.getField(DBSAApplicationConst.TITLE).replaceAll("/em", ""));
	            	if(entry.getField(DBSAApplicationConst.AUTHOR) == null){
	            		entry.setField((DBSAApplicationConst.AUTHOR), "");
	            	}
	            
	            	String searchString = (entry.getField(DBSAApplicationConst.AUTHOR));
	            	
	            	int ind = pages.indexOf(searchString);
	            	String number = "0";
	            	
	            	if(ind != -1){
		            	String subString = pages.substring(ind, ind + searchString.length() + 100);
		    	        
		    	        String temp = subString.replaceAll("&#8212;"," ");
		    	        
		    	        Matcher m = searchPattern.matcher(temp);
		    	        
		    	        if(m.find()){
		    	        	number = m.group(0);
			            	System.out.println(number);
			            	
		    	        }
	            	}
	            	DBSAApplication.fetcherResultPanel.setAuthor(entry.getField(DBSAApplicationConst.AUTHOR));
	            	if(entry.getField(DBSAApplicationConst.CITESEERURL) == null){
	            		entry.setField((DBSAApplicationConst.CITESEERURL), "");
	            	}
	            	DBSAApplication.fetcherResultPanel.setLink(entry.getField(DBSAApplicationConst.CITESEERURL));
	            	
	            	if(entry.getField(DBSAApplicationConst.YEAR) == null){
	            		entry.setField(number, "");
	            	}
	            	DBSAApplication.fetcherResultPanel.setYear(Integer.parseInt(number));
	            	if(entry.getField(DBSAApplicationConst.ABSTRACT) == null){
	            		entry.setField((DBSAApplicationConst.ABSTRACT), "");
	            	}
	            	DBSAApplication.fetcherResultPanel.setAbstract(entry.getField(DBSAApplicationConst.ABSTRACT));
            		
	            	DBSAApplication.fetcherResultPanel.getResultsJTable();
            	}
            }
            
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
		
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public static String getResults(URL source) throws IOException {
        
        InputStream in = source.openStream();
        StringBuffer sb = new StringBuffer();
        byte[] buffer = new byte[256];
        while(true) {
            int bytesRead = in.read(buffer);
            if(bytesRead == -1) break;
            for (int i=0; i<bytesRead; i++)
                sb.append((char)buffer[i]);
        }
        return sb.toString();
    }
	
}
