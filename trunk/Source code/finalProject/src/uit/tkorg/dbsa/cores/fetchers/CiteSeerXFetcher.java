package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import net.sf.jabref.BibtexEntry;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
/**
 * @author Tiger
 * @modify tiendv
 *
 */
public class CiteSeerXFetcher {
	
	private static boolean shouldContinue = true;
	public static final String baseURL="http://citeseerx.ist.psu.edu/search?q=";
	
    private static FetcherResultPanel resultFetch = new FetcherResultPanel(1);
    
	
	
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
	
		//replace all white-space to '+'
		keyword = keyword.replaceAll("\\s", "+");
				
		String queryString = baseURL+keyword+feedAction+feedAtom+"&sort=rel";
		List<BibtexEntry> entries = new ArrayList<BibtexEntry>();
		
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
	            	
            		resultFetch.setRowNumber(1);
            		resultFetch.setTitle(entry.getField("title"));
            		resultFetch.setAuthor(entry.getField("author"));
            		resultFetch.setYear(Integer.parseInt(entry.getField("year")));
            		resultFetch.setAbstract(entry.getField("abstract"));
            		
            		resultFetch.getResultsJTable();
            	}
            }
            
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
/*	public static void main(String [] args) {
		String keyword = "Controlled,   Systematic, and Efficient Code Replacement for Running Java Programs";
		CiteSeerXFetcher fetcher = new CiteSeerXFetcher();
		fetcher.processQuery(keyword);
	}*/
	
}
