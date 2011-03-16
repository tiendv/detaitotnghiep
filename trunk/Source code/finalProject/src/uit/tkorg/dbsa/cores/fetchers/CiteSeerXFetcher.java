package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
/**
 * @author Tiger
 * @modify tiendv
 *
 */
public class CiteSeerXFetcher {
	
	
	// pattern for get ATOM link
	
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
	 * Parameter for autorun
	 * 
	 */
	public static boolean flagAutorun = false;
	public static boolean flagReportDone = false;
	public static int numberOfResult = 0;
	public static ArrayList<DBSAPublication> newDBSAPublication = new ArrayList<DBSAPublication>();
	// Patterb for get Number of result
	
	public static String endSearchResultNumber ="&submit=Search&sort=rel";
	private static String contentPage = null;
	private static Pattern getPageResult = Pattern.compile(".*(\\d+,*\\d*,*\\d*,*\\d*).* documents");
	
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
	protected static SAXParser saxParser;
	public CiteSeerXFetcher(){
		
	}
	
	static int atomStart = 0;	
	static int maxResult = FetcherPanel.getCiteResultNumber();
	int start = 0;
	public static boolean processQuery(String keyword, int startNumber){
		
		shouldContinue = true;
		
		//replace all white-space to '+'
		keyword = keyword.replaceAll("\\s", "+");
		
		if(isFlagAutorun() == true){
			
			String urlQuery = baseURL + keyword + endSearchResultNumber;
			URL urlGetResultNumber;
		 
			try {
				urlGetResultNumber = new URL(urlQuery);
				contentPage = getResults (urlGetResultNumber);		
				numberOfResult= getNumberOfHits(contentPage,getPageResult);	
				maxResult = numberOfResult;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		String queryString = baseURL + keyword + feedAction + feedAtom + "&sort=rel&start=" + atomStart;
		ArrayList<BibtexEntry> entries = new ArrayList<BibtexEntry>();		
		//System.out.println(queryString);
		
		try {
			
			URL citeseerUrl = new URL(queryString);
			HttpURLConnection citeseerConnection = (HttpURLConnection) citeseerUrl.openConnection();
			InputStream inputStream = citeseerConnection.getInputStream();
			DefaultHandler handlerBase = new CiteSeerXAtomEntryHandler(entries);
			
            if (saxParser == null)
            	saxParser = SAXParserFactory.newInstance().newSAXParser();
            
            saxParser.parse(inputStream, handlerBase);
           
            for(BibtexEntry entry: entries){          	
            	
            	if(startNumber % 10 == 0 && startNumber != 0 ){
            		resultNumber += 10;
	    			String yearURL = startSearchYearPub + keyword + endSearchYearPub + resultNumber;
	    			URL url1 = new URL(yearURL);
	    			pages = getResults(url1);	    			
            	}
            	else if(startNumber == 0){
            		
            		String yearURL = startSearchYearPub + keyword + endSearchYearPub;
	    			URL url1 = new URL(yearURL);
	    			
	    			pages = getResults(url1);
	    			
            	}else
            	if(startNumber > maxResult){
            		shouldContinue = false;
            		setFlagReportDone(true);
            		break;
            	}
            	
            	startNumber++;
            	if(startNumber == 100)
            		atomStart += 100;
            	
            	if(shouldContinue == true && startNumber <= maxResult){
            		
	            	Set<String> fields = entry.getAllFields();
	            	DBSAPublication temp = new DBSAPublication();
	            	
	            //	for(String f:fields){
	            	//	System.out.println(f+":"+entry.getField(f));
	            //	}	
	            	if(isFlagAutorun() == false)
	            	DBSAApplication.fetcherResultPanel.setRowNumber(1);
	            	
	            	if(entry.getField(DBSAApplicationConst.TITLE) == null){
	            		if ( isFlagAutorun() == false)
	            		entry.setField((DBSAApplicationConst.TITLE), "");
	            		else 
	            			temp.setTitle("");
	            	}
	            	if(isFlagAutorun() == false)
	            		DBSAApplication.fetcherResultPanel.setTitle(entry.getField(DBSAApplicationConst.TITLE).replaceAll("/em", ""));
	            	else
	            		temp.setTitle(entry.getField(DBSAApplicationConst.TITLE).replaceAll("/em", ""));	
	            	
	            	if(entry.getField(DBSAApplicationConst.AUTHOR) == null){
	            		if(isFlagAutorun() == false)
	            		entry.setField((DBSAApplicationConst.AUTHOR), "");
	            		else
	            			temp.setAuthors("");
	            	}
	            
	            	String searchString = (entry.getField(DBSAApplicationConst.AUTHOR));
	            	
	            	int ind = pages.indexOf(searchString);
	            	String number = "0";
	            	
	            	if(ind != -1){
		            	String subString = pages.substring(ind, ind + searchString.length() + 100);
		    	        
		    	        String temp1 = subString.replaceAll("&#8212;"," ");
		    	        
		    	        Matcher m = searchPattern.matcher(temp1);
		    	        
		    	        if(m.find()){
		    	        	number = m.group(0);
			            	System.out.println(number);
			            	
		    	        }
	            	}
	            	if(isFlagAutorun() == false)
	            	DBSAApplication.fetcherResultPanel.setAuthor(entry.getField(DBSAApplicationConst.AUTHOR));
	            	else 
	            		temp.setAuthors(entry.getField(DBSAApplicationConst.AUTHOR));
	            	
	            	if(entry.getField(DBSAApplicationConst.CITESEERURL) == null){
	            		if(isFlagAutorun() == false)
	            			entry.setField((DBSAApplicationConst.CITESEERURL), "");
	            		else 
	            			temp.setLinks("");
	            	}
	            	if (isFlagAutorun() == false)
	            		DBSAApplication.fetcherResultPanel.setLink(entry.getField(DBSAApplicationConst.CITESEERURL));
	            	else
	            		temp.setLinks(entry.getField(DBSAApplicationConst.CITESEERURL));
	            	
	            	if(entry.getField(DBSAApplicationConst.YEAR) == null){
	            		if(isFlagAutorun() == false)
	            				entry.setField(number, "");
	            		else
	            			temp.setYear(0);
	            	}
	            	if(isFlagAutorun() == false)
	            		DBSAApplication.fetcherResultPanel.setYear(Integer.parseInt(number));
	            	else
	            		temp.setYear(Integer.parseInt(number));
	            	
	            	if(entry.getField(DBSAApplicationConst.ABSTRACT) == null){
	            		if(isFlagAutorun() == false)
	            			entry.setField((DBSAApplicationConst.ABSTRACT), "");
	            		else
	            			temp.setAbstractPub("");
	            	}
	            	if(isFlagAutorun() == false)
	            		DBSAApplication.fetcherResultPanel.setAbstract(entry.getField(DBSAApplicationConst.ABSTRACT));
	            	else
	            		temp.setAbstractPub(entry.getField(DBSAApplicationConst.ABSTRACT));
	            	
	            	DBSAApplication.fetcherResultPanel.setDigitalLibrary("CITESEER");
	            	if(isFlagAutorun() == false)
	            		DBSAApplication.fetcherResultPanel.getResultsJTable();
	            	else
	            		newDBSAPublication.add(temp);
            	}
            	
            }
            
            if(startNumber%100 == 0 && startNumber <= FetcherPanel.getCiteResultNumber() && shouldContinue == true){
            	maxResult -= 100;
            	resultNumber += 10;
            	
        		processQuery(keyword, 0);        		
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
	/*
	 * Ham tim 1 chuoi trong file html dua vao noi dung the (Pattern). 
	 * The nay (Pattern) duoc dinh nghia bang cach su dung Regular Expressions.
	 * @return int
	 */
	private static int getNumberOfHits(String page, Pattern pattern) throws IOException {
	        Matcher m = pattern.matcher(page);
	        if (!m.find()) {
	        	System.out.println("Unmatched!");
	        } else {
	            try {
	            	String number = m.group(0);   
	            	System.out.println("============so ra nay============="+number);
	            	number = number.replaceAll("documents", "");
	            	System.out.println("============so ra nay============="+number);
	            	number = number.replaceAll(",", "");
	            	System.out.println("============so ra nay============="+number);
	            	number = number.replaceAll(" ", "");
	            	
	                return Integer.parseInt(number);
	            } catch (NumberFormatException ex) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            } catch (IllegalStateException e) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            }
	        }
	        throw new IOException(Globals.lang("Could not parse number of hits"));
	}
	
	// Method for autorun
	
	public static boolean isFlagAutorun() {
		return flagAutorun;
	}


	public static void setFlagAutorun(boolean flagAutorun) {
		CiteSeerXFetcher.flagAutorun = flagAutorun;
	}


	public static boolean isFlagReportDone() {
		return flagReportDone;
	}


	public static void setFlagReportDone(boolean flagReportDone) {
		CiteSeerXFetcher.flagReportDone = flagReportDone;
	}


	public static ArrayList<DBSAPublication> getNewDBSAPublication() {
		return newDBSAPublication;
	}


	public static void setNewDBSAPublication(
			ArrayList<DBSAPublication> newDBSAPublication) {
		CiteSeerXFetcher.newDBSAPublication = newDBSAPublication;
	}
	public static int getNumberOfResult() {
		return numberOfResult;
	}


	public static void setNumberOfResult(int numberOfResult) {
		CiteSeerXFetcher.numberOfResult = numberOfResult;
	}

	
}
