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

public class CiteSeerXFetcher {
	
	private static boolean shouldContinue = false;
	public static final String baseURL="http://citeseerx.ist.psu.edu/search?q=";
	
	/**
	 * @author Tiger
	 * @modify tiendv
	 *
	 */
	
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
	
	
	protected SAXParserFactory parserFactory;
	protected SAXParser saxParser;
	
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
            	
            for(BibtexEntry entry : entries){
            	
            	Set<String> fields = entry.getAllFields();
            	for(String f:fields){
            		System.out.println(f+":"+entry.getField(f));
            	}
            	System.out.println();
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