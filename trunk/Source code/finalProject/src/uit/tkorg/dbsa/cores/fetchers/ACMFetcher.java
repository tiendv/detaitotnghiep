package uit.tkorg.dbsa.cores.fetchers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.HTMLConverter;
import net.sf.jabref.imports.ParserResult;

public class ACMFetcher {
	
	//Tu khoa can tim kiem
	private static String keywordString = null;
	
	private final static HTMLConverter htmlConverter = new HTMLConverter();
	
	//Cac chuoi tao cau query chua tu khoa can tim kiem
	private static String startUrl = "http://portal.acm.org/";
	private static String searchUrlPart = "results.cfm?query=";
	private static String searchUrlPartII = "&dl=";
	static String endUrl = "&coll=Portal&short=0";//&start=";
	/**
	 * Vi du:
	 * Tu khoa: computer vision 
	 * Câu query: http://portal.acm.org/results.cfm?query=computer20%vision&dl=ACM&coll=Portal&short=0
	 */
	
	private static int MAX_FETCH = 20; // ket qua thu thap toi da
	private static int perPage = MAX_FETCH;
	private static int hits = 0; // so ket qua thu thap duoc
	
	//Cac the dung de nhan dang noi dung file html vua thu thap
	private static Pattern hitsPattern = Pattern.compile(".*Found <b>(\\d+,*\\d*)</b> of.*");
    private static Pattern maxHitsPattern = Pattern.compile(".*Results \\d+ - \\d+ of (\\d+,*\\d*).*");
    //Nhan dang file BibTex trong file html
    private static Pattern bibPattern = Pattern.compile(".*(popBibTex.cfm.*)','BibTex'.*");
    //Nhan dang URL cua mot bai bao khoa hoc trong file html
	private static Pattern fullCitationPattern = Pattern.compile("<A HREF=\"(citation.cfm.*)\" class.*");
	//Nhan dang phan Abstract cua bai bao trong file html
	private static Pattern absPattern = Pattern.compile(".*ABSTRACT</A></span>\\s+<p class=\"abstract\">\\s+(.*)");
	
	//Bien cho phep lua chon tiep tuc tim kiem hay không
	public static boolean shouldContinue = true;
	
	private static FetcherResultPanel resultFetch = new FetcherResultPanel(1);
	
	public ACMFetcher(){
		
	}	
	public static void Fetcher(String keyword) throws IOException{
		
		keywordString = keyword; 
		
		//Tao URL tu keyword do nguoi dung nhap
		URL url = MakeUrl(0);
		
		//Thu thap trang html dau tien sau khi gui tu khoa len thu vien so.
		System.out.println("Url = " + url);
		String page = getFetcherResult(url);
		
		//lay so ket qua tu 
		GetResultNumber(page);
		
		//lay thong tin cua bai bao khoa hoc tu trang html
		parse(page, 0, 1);
		
		int firstEntry = perPage;
		
		System.out.println("Hits = " + hits);
		
        while (shouldContinue && (firstEntry < hits)) {            
        	page = getFetcherResult(MakeUrl(firstEntry));
        		
            if (!shouldContinue)
                break;

            parse(page, 0, firstEntry + 1);
            
            System.out.println("FirstEntry = " + firstEntry);
            
            firstEntry += perPage;
         
        }
	}
	
	/*
	 * Ham tao URL tu cac chuoi tren (chuoi startUrl, searchUrlPart, searchUrlPartII, endUrl) 
	 * va tu khoa do nguoi dung nhap.
	 */
	public static URL MakeUrl(int startIndex){
		StringBuffer sb = new StringBuffer(startUrl); 
		sb.append(searchUrlPart);		
		sb.append(keywordString.replaceAll(" ", "20%"));	//Chuyen khoang trang (" ") thanh 20% de gui len search
		sb.append(searchUrlPartII);
		sb.append("ACM");
		sb.append(endUrl);
		
		URL urlSearch = null;		
		try {
			//Tao URL tu chuoi tren
			urlSearch = new URL(sb.toString());
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		
		return urlSearch;
	}
	
	/*
	 * Thu thap 1 trang html dua vao Url cua trang do
	 * @return String
	 */
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
	
	/*
	 * Lay so ket qua cua thu vien so tim duoc theo tu khoa do nguoi dung vua nhap. 
	 * @return null
	 */
	public static void GetResultNumber(String page){
		
			try {
				hits = getNumberOfHits(page, "Found", hitsPattern);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			int index = page.indexOf("Found");
			int maxHits = 0;
			
			if (index >= 0) {
            	page = page.substring(index + 5);
            	
				index = page.indexOf("Found");
				if (index >= 0)
            		page = page.substring(index);
			}
			
			try {
				maxHits = getNumberOfHits(page, "Results", maxHitsPattern);	
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			if(hits > maxHits){
				hits = MAX_FETCH;
			}		
	}
	
	/*
	 * Ham tim 1 chuoi trong file html dua vao noi dung the (Pattern). 
	 * The nay (Pattern) duoc dinh nghia bang cach su dung Regular Expressions.
	 * @return int
	 */
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
	            	String number = m.group(1);
	            	
	            	number = number.replaceAll(",", "");
	            	
	                return Integer.parseInt(number);
	            } catch (NumberFormatException ex) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            } catch (IllegalStateException e) {
	                throw new IOException(Globals.lang("Could not parse number of hits"));
	            }
	        }
	        throw new IOException(Globals.lang("Could not parse number of hits"));
	}
	
	/*
	 * Ham lay cac bai bao khoa hoc tu 1 trang.
	 */
	static int piv = 0;
	
	private static void parse(String text, int startIndex, int firstEntryNumber) {
        piv = startIndex;
        int fetcherNumber = FetcherPanel.getAcmResultNumber() + firstEntryNumber;
        
        int entryNumber = firstEntryNumber;
         
        while ((shouldContinue)) {
        	
        	if(entryNumber >= fetcherNumber){
        		
        		shouldContinue = false;
        		break;
        	}
        	
        	parseNextEntry(text, piv, entryNumber);
        	
        	entryNumber++;  
        	
        	FetcherPanel.setAcmProgressBar(entryNumber/fetcherNumber*100);
        	
            try {
            	Thread.sleep(10000);//wait between requests or you will be blocked by ACM
            } catch (InterruptedException e) {
            	System.err.println(e.getStackTrace());
            }
        }
    }
	
	/*
	 * Ham lay 1 bai bao khoa hoc 
	 * @return BibTexEntry
	 */
	private static BibtexEntry parseNextEntry(String allText, int startIndex, int entryNumber){
		
		String toFind = new StringBuffer().append("<strong>").append(entryNumber).append("</strong>").toString();
		
		int index = allText.indexOf(toFind, startIndex);
		
		int endIndex = allText.indexOf("</table>", index+1);
		
		endIndex = allText.length();
		
		BibtexEntry entry = null;
		
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
	
	static /*
	 * Ham phan tich cu phap va lay thong tin cua mot bai bao khoa hoc
	 * @return BibTexEntry
	 */
	int number = 0;
	@SuppressWarnings("static-access")
	private static BibtexEntry parseEntryBibTeX(String fullCitation){
		URL url;
		
		try {
			url = new URL(startUrl + fullCitation);
			String page = getFetcherResult(url);
			
			System.out.println("url = " + url);
			
			Thread.sleep(10000); //wait between requests or you will be blocked by ACM
			
			Matcher bibtexAddr = bibPattern.matcher(page);
			
			if(bibtexAddr.find()){
				URL bibtexUrl = new URL(startUrl + bibtexAddr.group(1));
				
				System.out.println(bibtexAddr);
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
				
				Thread.sleep(1000);//wait between requests or you will be blocked by ACM
				
				System.out.println("Title : " + entry.getField("title"));
				System.out.println("Authors : " + entry.getField("author"));
				System.out.println("Year : " + entry.getField("year"));
				System.out.println("Abstract : " + entry.getField("abstract"));
				System.out.println("Publisher : " + entry.getField("publisher"));
				System.out.println("Volume : " + entry.getField("volume"));
				
				
				
				number ++;
				resultFetch.setRowNumber(number);
				resultFetch.setTitle(entry.getField("title"));
				resultFetch.setAuthor(entry.getField("author"));
				resultFetch.setYear(Integer.parseInt(entry.getField("year")));
				resultFetch.setAbstract(entry.getField("abstract"));
				resultFetch.setPublisher(entry.getField("publisher"));

				resultFetch.getResultsJTable();
				
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
