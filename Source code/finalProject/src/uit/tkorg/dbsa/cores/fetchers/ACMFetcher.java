package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.xerces.impl.xpath.regex.ParseException;
import org.htmlparser.beans.StringBean;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.BibtexParser;
//import net.sf.jabref.imports.HTMLConverter;

public class ACMFetcher {
	
	//Tu khoa can tim kiem
	private static String keywordString = null;
	
//	private final static HTMLConverter htmlConverter = new HTMLConverter();
	
	//Cac chuoi tao cau query chua tu khoa can tim kiem\
	
	private static String startUrl = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_START_URL);
	//private static String searchUrlPart = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_SEARCH_URL_PART);
	private static String searchUrlPart = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_SEARCH_URL_PART);
	static String endUrl = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_END_URL);
	
	//Cac chuoi tao cau query cho advancde search chua tu tim kiem node trong cay phan lop ACM can tim kiem\
	
	private static String startAdvancedSearchNodeUrl ="http://portal.acm.org/results.cfm?CFID=113292600&CFTOKEN=55934314&adv=1&COLL=DL&DL=ACM&termzone=all&allofem=&anyofem=&noneofem=&peoplezone=Name&people=&peoplehow=and&keyword=&keywordhow=AND&affil=&affilhow=AND&pubin=&pubinhow=and&pubby=&pubbyhow=OR&since_year=&before_year=&pubashow=OR&sponsor=&sponsorhow=AND&confdate=&confdatehow=OR&confloc=&conflochow=OR&isbnhow=OR&isbn=&doi=&ccs=";
	private static String endAdvancedSearchNodeUrl = "&subj=&hasft=on&hasabs=on&hasrev=on&Go.x=34&Go.y=11";
	
	// Cac chuoi tao cau query cho  advancde search cho subject 
	
	private static String startAdvancedSearchSubjectUrl ="http://portal.acm.org/results.cfm?CFID=113292600&CFTOKEN=55934314&adv=1&COLL=DL&DL=ACM&termzone=all&allofem=&anyofem=&noneofem=&peoplezone=Name&people=&peoplehow=and&keyword=&keywordhow=AND&affil=&affilhow=AND&pubin=&pubinhow=and&pubby=&pubbyhow=OR&since_year=&before_year=&pubashow=OR&sponsor=&sponsorhow=AND&confdate=&confdatehow=OR&confloc=&conflochow=OR&isbnhow=OR&isbn=&doi=&ccs=+&subj=";
	private static String endAdvancedSearchSubjectUrl = "&hasft=on&hasabs=on&hasrev=on&Go.x=12&Go.y=10";
	
	// Chuoi de lay thong tin :
	private static String startGetBibtex = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_START_GET_BIBTEX);
	private static String endGetBibtex = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_END_GET_BIBTEX);
	
	// Chuoi de lay abstract 
	private static String startGetAbstract = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_START_GET_ABSTRACT);
	private static String endGetAbstract = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_END_GET_ABSTRACT);
	/**
	 * Vi du:
	 * Tu khoa: computer vision 
	 * Câu query: http://portal.acm.org/results.cfm?query=computer20%vision&dl=ACM&coll=Portal&short=0
	 */
	
	private static int MAX_FETCH = 100; // ket qua thu thap toi da
	private static int perPage = MAX_FETCH;
	private static int hits = 0; // so ket qua thu thap duoc
	
	//Cac the dung de nhan dang noi dung file html vua thu thap
	private static Pattern hitsPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_HITS_PATTERN));
//    private static Pattern maxHitsPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_MAX_HITS_PATTERN));
    //Nhan dang file BibTex trong file html
//    private static Pattern bibPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_BIB_PATTERN));
    //Nhan dang URL cua mot bai bao khoa hoc trong file html
	private static Pattern fullCitationPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_FULL_CITATION_PATTERN));
//	private static Pattern getIDBitex = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_GET_ID_BIBTEX));
	//Nhan dang phan Abstract cua bai bao trong file html
	private static Pattern absPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_ABSTRACT_PATTERN));
	
	
	//Bien cho phep lua chon tiep tuc tim kiem hay không
	public static boolean shouldContinue = true;
	
	//Lay thong tin file bitex : http://portal.acm.org/exportformats.cfm?id=152611&expformat=bibtex
	//Lay thong tin cua abstract http://portal.acm.org/tab_abstract.cfm?id=152611&usebody=tabbody
	//Lay id http://portal.acm.org/citation.cfm?id=152610.152611&coll=DL&dl=GUIDE&CFID=115229885&CFTOKEN=24731416
	// citation.cfm?id=\d+\.\d+
	private static Pattern idPaper = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_ID_PAPER));
	
	public ACMFetcher(){
		
	}	
	public static void Fetcher(String keyword) throws IOException{
		
		keywordString = keyword; 
	
		int entryNumber = 0;
		
		while(shouldContinue != false){
			
			if(entryNumber%20 == 0){
				URL url = MakeUrl(entryNumber);
				System.out.println("Url = " + url);
				
				String page = getFetcherResult(url);
				GetResultNumber(page);
				entryNumber = parse(page, 0, entryNumber + 1);
				System.out.println("entryNumber " + entryNumber);
			}
			
			int fetcherNumber = FetcherPanel.getAcmResultNumber();

			if(entryNumber >= fetcherNumber){
        		System.out.println("fetcherNumber " + fetcherNumber);
        		shouldContinue = false;
        		break;
        	}
		}
	}
	
	/*
	 * Ham tao URL tu cac chuoi tren (chuoi startUrl, searchUrlPart, searchUrlPartII, endUrl) 
	 * va tu khoa do nguoi dung nhap.
	 */
	public static URL MakeUrl(int startIndex){
		
		URL urlSearch = null;	
		
		if(FetcherPanel.checkSearchByAll){
			StringBuffer sb = new StringBuffer(startUrl); 
			sb.append(keywordString.replaceAll(" ", "20%"));	//Chuyen khoang trang (" ") thanh 20% de gui len search
			sb.append(searchUrlPart);
			sb.append(startIndex + 1);
			sb.append(endUrl);
			
				
			try {
				//Tao URL tu chuoi tren
				urlSearch = new URL(sb.toString());
			} catch (MalformedURLException e) {			
				e.printStackTrace();
			}
		}else{
			StringBuffer sb = new StringBuffer(startAdvancedSearchSubjectUrl);
			sb.append(keywordString.replaceAll(" ", "+"));
			sb.append(endAdvancedSearchSubjectUrl);
			try {
				urlSearch = new URL(sb.toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			if (index >= 0) {
            	page = page.substring(index + 5);
            	
				index = page.indexOf("Found");
				if (index >= 0)
            		page = page.substring(index);
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
	        String subString = page.substring(ind, Math.min(ind + 42, page.length()));
	        
	        Matcher m = pattern.matcher(subString);
	        if (!m.find()) {
	        	System.out.println("Unmatched!");
	        	System.out.println(subString);
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
	
	private static int parse(String text, int startIndex, int firstEntryNumber) {
        piv = startIndex;
        int maxNumber = FetcherPanel.getAcmResultNumber();
        
        int fetcherNumber = firstEntryNumber + 20;
        
        if(firstEntryNumber + 20 > maxNumber)
        	fetcherNumber = maxNumber + 1;
        
        int entryNumber = firstEntryNumber;
         
    	for(int i = firstEntryNumber; i < fetcherNumber; i++){
    		parseNextEntry(text, piv, entryNumber);
    		entryNumber++;  
    	}
    	
        try {
        	Thread.sleep(6000);//wait between requests or you will be blocked by ACM
        } catch (InterruptedException e) {
        	System.err.println(e.getStackTrace());
        }
        return entryNumber - 1;
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
					Thread.sleep(6000);
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
	
	/*
	 * Ham phan tich cu phap va lay thong tin cua mot bai bao khoa hoc
	 * @return BibTexEntry
	 */
	static int number = 0;
	private static BibtexEntry parseEntryBibTeX(String fullCitation){
		URL url = null;
		String  id = null;
		BibtexEntry entry = null;
			try {
				url = new URL(startUrl + fullCitation);
				Matcher getdPaper = idPaper.matcher(url.toString());
				
				 if (!getdPaper.find()) {
			        	System.out.println("Unmatched!");
			        } else {
			        		id = getdPaper.group(0);
			        		id=id.replaceAll("&", ""); 
			        }
				 	
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(6000);
				String urlGetBitex = startGetBibtex + id + endGetBibtex;
				String bitex = getUrlContentsAsText(urlGetBitex);
				System.out.println(bitex.length());

				if(bitex.length() > 90){
					entry = BibtexParser.singleFromString(bitex);
				}else{
					FetcherPanel.setAcmResultNumber(FetcherPanel.getAcmResultNumber() - 1);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(6000);
				String urlGetAbStract = startGetAbstract + id + endGetAbstract;
				
				URL test = null;
				try {
					test = new URL(urlGetAbStract);
					String abstr;
					abstr = getFetcherResult(test);
					abstr = abstr.replace("\r\n", " ").replace("\n", " "); 
					
					// remove line break
					Matcher getdAbstract = absPattern.matcher(abstr);
					
					if (!getdAbstract.find() && entry != null) {
						 entry.setField("abstract", " ");
				        System.out.println("Unmatched Abstract!");
				    }else if(entry != null){
				        abstr = getdAbstract.group(0);
						abstr = abstr.replaceAll("\\<.*?>","");
						entry.setField("abstract", abstr);
				    }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(entry != null){				
				number ++;
				DBSAApplication.fetcherResultPanel.setRowNumber(number);
				if(entry.getField("title") == null){
					entry.setField("title", "");
				}
				DBSAApplication.fetcherResultPanel.setTitle(entry.getField("title"));
				if(entry.getField("author") == null){
					entry.setField("author", "");
				}
				DBSAApplication.fetcherResultPanel.setAuthor(entry.getField("author"));
				
				if(entry.getField("url") == null){
					entry.setField("url", "");
				}
				DBSAApplication.fetcherResultPanel.setLink(entry.getField("url"));
			
				if(entry.getField("year") == null){
					entry.setField("year", "");
				}
				DBSAApplication.fetcherResultPanel.setYear(Integer.parseInt(entry.getField("year")));
				if(entry.getField("abstract") == null){
					entry.setField("abstract", "");
				}
				DBSAApplication.fetcherResultPanel.setAbstract(entry.getField("abstract"));
				if(entry.getField("publisher") == null){
					entry.setField("publisher", "");
				}
				DBSAApplication.fetcherResultPanel.setPublisher(entry.getField("publisher"));
				DBSAApplication.fetcherResultPanel.getResultsJTable();
		}
		return entry;
	}
	/**
	 * 
	 * @param url: URL to get Content 
	 * @return : String HTML content
	 */
    public static  String getUrlContentsAsText(String url) { 
        String content = ""; 
        StringBean stringBean = new StringBean(); 
        stringBean.setURL(url); 
        content = stringBean.getStrings(); 
        return content; 
	} 
    
}
