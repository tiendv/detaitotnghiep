package uit.tkorg.dbsa.cores.fetchers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.HTMLConverter;
import net.sf.jabref.imports.ImportInspector;
import net.sf.jabref.imports.ParserResult;

import antlr.ByteBuffer;

public class ACMFetcher {
	
	//Tu khoa can tim kiem
	private String keywordString = null;
	
	private static String terms = null;
	
	private final static HTMLConverter htmlConverter = new HTMLConverter();
	
	private static String startUrl = "http://portal.acm.org/";
	private static String searchUrlPart = "results.cfm?query=";
	private static String searchUrlPartII = "&dl=";
	static String endUrl = "&coll=Portal&short=0";//&start=";
	/**
	 * ex: http://portal.acm.org/results.cfm?coll=ACM&dl=ACM&CFID=107834110&CFTOKEN=22371969
	 */
	private static int MAX_FETCH = 20;
	private static int perPage = MAX_FETCH;
	private static int hits = 0;
	
	private static Pattern hitsPattern = Pattern.compile(".*Found <b>(\\d+,*\\d*)</b> of.*");
    private static Pattern maxHitsPattern = Pattern.compile(".*Results \\d+ - \\d+ of (\\d+,*\\d*).*");
    private static Pattern bibPattern = Pattern.compile(".*(popBibTex.cfm.*)','BibTex'.*");
	private static Pattern fullCitationPattern = Pattern.compile("<A HREF=\"(citation.cfm.*)\" class.*");
	private static Pattern absPattern = Pattern.compile(".*ABSTRACT</A></span>\\s+<p class=\"abstract\">\\s+(.*)");
	private static boolean shouldContinue = false;
	private int unparseable = 0;
	private static int parsed = 0;
	
	public ACMFetcher(){
		
	}
	
	public static URL MakeUrl(int startIndex){
		StringBuffer sb = new StringBuffer(startUrl); 
		sb.append(searchUrlPart);		
		sb.append(terms.replaceAll(" ", "20%"));
		sb.append(searchUrlPartII);
		sb.append("ACM");
		sb.append(endUrl);
		
		URL urlSearch = null;
		
		try {
			urlSearch = new URL(sb.toString());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		return urlSearch;
	}
	
	public static void Fetcher(String keyword) throws IOException{
		
		terms = keyword;
	
		/*
		 * 
		 */
		URL url = MakeUrl(0);
		
		
		/*
		 * 
		 */
		System.out.println("Url = " + url);
		
		String page = getFetcherResult(url);
		
		System.out.println("page 1");
		
		GetResultList(page);
		
		parse(page, 0, 1);
		
		int firstEntry = perPage;
        while (shouldContinue && (firstEntry < hits)) {
            
        	page = getFetcherResult(MakeUrl(firstEntry));
        	
        	//System.out.println("Fetching from: "+firstEntry);
            
//        	address = makeUrl(firstEntry);
            //System.out.println(address);
//            page = getResults(new URL(address));
            
            //dialog.setProgress(firstEntry+perPage/2, hits);
            if (!shouldContinue)
                break;

            parse(page, 0, 1+firstEntry);
            firstEntry += perPage;
        }
        
//		FileOutputStream fileOutput;
//		DataOutputStream dataOutput;
//		File file = new File("C:\\abc.txt");
//		try {
//			fileOutput = new FileOutputStream(file);
//			dataOutput =  new DataOutputStream(fileOutput);
//			try {
//				dataOutput.writeChars(page);
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		}
	}
	
	public static String getFetcherResult(URL url) throws IOException{
	
		InputStream in = url.openStream();
		StringBuffer sb = new StringBuffer();
		
		byte [] buffer = new byte[256];
		
		while(true){
			int byteRead = in.read(buffer);
			if(byteRead == -1)
				break;
			for(int i = 0; i < byteRead; i++){
				sb.append((char)buffer[i]);
			}
		}
		return sb.toString();
	}
	
	public static void GetResultList(String page){
		
			try {
				hits = getNumberOfHits(page, "Found", hitsPattern);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int index = page.indexOf("Found");
			
			if (index >= 0) {
            	page = page.substring(index + 5);
            	
				index = page.indexOf("Found");
				if (index >= 0)
            		page = page.substring(index);
			}
			
			try {
				int maxHits = getNumberOfHits(page, "Results", maxHitsPattern);	
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			if(hits > MAX_FETCH){
				hits = MAX_FETCH;
			}
		
		
	}
	
	private static int getNumberOfHits(String page, String marker, Pattern pattern) throws IOException {
	        int ind = page.indexOf(marker);
	        if (ind < 0) {
	        	System.out.println(page);
	            throw new IOException(Globals.lang("Could not parse number of hits"));
	        }
	        String substring = page.substring(ind, Math.min(ind + 42, page.length()));
	        Matcher m = pattern.matcher(substring);
	        if (!m.find()) {
	        	System.out.println("Unmatched!");
	        	System.out.println(substring);
	        } else {
	            try {
	            	// get rid of ,
	            	String number = m.group(1);
	            	//NumberFormat nf = NumberFormat.getInstance();
	            	//return nf.parse(number).intValue();
	            	number = number.replaceAll(",", "");
	            	//System.out.println(number);
	                return Integer.parseInt(number);
	            } catch (NumberFormatException ex) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            } catch (IllegalStateException e) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            }
	        }
	        throw new IOException(Globals.lang("Could not parse number of hits"));
	}
	
	static int piv = 0;
	
	private static void parse(String text, int startIndex, int firstEntryNumber) {
        piv = startIndex;
        int entryNumber = firstEntryNumber;
        
        BibtexEntry entry;
        while (((entry = parseNextEntry(text, piv, entryNumber)) != null) && (shouldContinue)) {
        
        	if (entry.getField("title") != null) {
        		System.out.println( "title" + entry.getField("title"));
                //dialog.addEntry(entry);
               // dialog.setProgress(parsed + unparseable, hits);
                parsed++;
            }
            entryNumber++;
            try {
            	Thread.sleep(10000);//wait between requests or you will be blocked by ACM
            } catch (InterruptedException e) {
            	System.err.println(e.getStackTrace());
            }
        }
    }
	
	private static BibtexEntry parseNextEntry(String allText, int startIndex, int entryNumber){
		
		String toFind = new StringBuffer().append("<strong>").append(entryNumber).append("</strong>").toString();
		
		int index = allText.indexOf(toFind, startIndex);
		
		int endIndex = allText.indexOf("</table>", index+1);
		
		endIndex = allText.length();
		
		BibtexEntry entry = null;
		
		System.out.println("index = " + index);
		
		if(index >= 0){
			piv = index + 1;
			String text = allText.substring(index, endIndex);
			
			Matcher fullCitition = fullCitationPattern.matcher(text);
			
			if(fullCitition.find()){
				try {
					Thread.sleep(1000);
					entry  = parseEntryBibTeX(fullCitition.group(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}else{
				System.out.printf("Citation Unmatched %d\n", entryNumber);
				System.out.printf(text);
			}
			
			if (entry != null) { // fetch successful
                return entry;
            }
			
		}
		return null;
	}
	
	private static BibtexEntry parseEntryBibTeX(String fullCitation){
		URL url;
		
		try {
			url = new URL(startUrl + fullCitation);
			String page = getFetcherResult(url);
			
			System.out.println("url = " + url);
			
			Thread.sleep(10000);
			
			Matcher bibtexAddr = bibPattern.matcher(page);
			
			if(bibtexAddr.find()){
				URL bibtexUrl = new URL(startUrl + bibtexAddr.group(1));
				
				BufferedReader in = new BufferedReader(new InputStreamReader(bibtexUrl.openStream()));
				
				ParserResult result = BibtexParser.parse(in);
				
				in.close();
				Collection<BibtexEntry> item = result.getDatabase().getEntries();
				
				BibtexEntry entry = item.iterator().next();
				
				// get abstract
				Matcher absMatch = absPattern.matcher(page);
				if (absMatch.find()) {
					String absBlock = absMatch.group(1);
					entry.setField("abstract", convertHTMLChars(absBlock).trim());
				} else {
					System.out.println("No abstract matched.");
					//System.out.println(page);
				}
				
				Thread.sleep(10000);//wait between requests or you will be blocked by ACM
				System.out.println("Title : " + entry.getField("title"));
				System.out.println("Year : " + entry.getField("year"));
				System.out.println("Abstract : " + entry.getField("abstract"));
				
				return entry;
				
			}else{
				return null;
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * This method must convert HTML style char sequences to normal characters.
     * @param text The text to handle.
     * @return The converted text.
     */
    private static String convertHTMLChars(String text) {

        return htmlConverter.format(text);
    }
    
}
