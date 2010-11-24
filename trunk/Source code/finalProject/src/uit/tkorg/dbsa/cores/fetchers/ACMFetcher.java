package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.beans.StringBean;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;
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
	private static String searchUrlPart = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_SEARCH_URL_PART);
	private static String searchUrlPartII = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_SEARCH_URL_PART_II);
	static String endUrl = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.ACM_END_URL);
	
	
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
	
	private static int MAX_FETCH = 20; // ket qua thu thap toi da
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
	private static Pattern absPattern = Pattern.compile("<div style=\"display:inline\">.*</div>");
	
	
	//Bien cho phep lua chon tiep tuc tim kiem hay không
	public static boolean shouldContinue = true;
	
	//Lay thong tin file bitex : http://portal.acm.org/exportformats.cfm?id=152611&expformat=bibtex
	//Lay thong tin cua abstract http://portal.acm.org/tab_abstract.cfm?id=152611&usebody=tabbody
	//Lay id http://portal.acm.org/citation.cfm?id=152610.152611&coll=DL&dl=GUIDE&CFID=115229885&CFTOKEN=24731416
	// citation.cfm?id=\d+\.\d+
	private static Pattern idPaper = Pattern.compile("\\d+&");
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
//			int maxHits = 0;
			
			if (index >= 0) {
            	page = page.substring(index + 5);
            	
				index = page.indexOf("Found");
				if (index >= 0)
            		page = page.substring(index);
			}
			
//			try {
//				maxHits = getNumberOfHits(page, "Results", maxHitsPattern);	
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//			
//			if(hits > maxHits){
//				hits = MAX_FETCH;
//			}		
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
	        
//	        System.out.println("Sub = " + substring);
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
		//System.out.println("Tat ca string in ra "+ allText);
		
		String toFind = new StringBuffer().append("<strong>").append(entryNumber).append("</strong>").toString();
		
		int index = allText.indexOf(toFind, startIndex);
		
		int endIndex = allText.indexOf("</table>", index+1);
		
		endIndex = allText.length();
		
		BibtexEntry entry = null;
		
		if(index >= 0){
			piv = index + 1;
			String text = allText.substring(index, endIndex);
			
			//System.out.printf("string text dung de tim kiem"+ text);
			
			Matcher fullCitition = fullCitationPattern.matcher(text);
			
		//	System.out.println("fullcition"+fullCitition.group(1));
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(6000);
				String urlGetBitex = startGetBibtex + id + endGetBibtex;
				String bitex = getUrlContentsAsText(urlGetBitex);
				entry = BibtexParser.singleFromString(bitex);
				
				/**
				 * 
				 *
				* to get and add in bitex here 
				* 
				* */
				
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
					System.out.println(abstr);
					Matcher getdAbstract = absPattern.matcher(abstr);
					
					 if (!getdAbstract.find()) {
				        	System.out.println("Unmatched Abstract!");
				        } else {
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
					
				System.out.println("Title : " + entry.getField("title"));
				System.out.println("Authors : " + entry.getField("author"));
				System.out.println("Link : " + entry.getField("url"));
				System.out.println("Year : " + entry.getField("year"));
				//System.out.println("Abstract : " + entry.getField("abstract"));
				System.out.println("Publisher : " + entry.getField("publisher"));
				System.out.println("Volume : " + entry.getField("volume"));
				
				number ++;
				resultFetch.setRowNumber(number);
				resultFetch.setTitle(entry.getField("title"));
				resultFetch.setAuthor(entry.getField("author"));
				resultFetch.setLink(entry.getField("url"));
				resultFetch.setYear(Integer.parseInt(entry.getField("year")));
				resultFetch.setAbstract(entry.getField("abstract"));
				resultFetch.setPublisher(entry.getField("publisher"));

				resultFetch.getResultsJTable();
				return entry;
	}

	
	/**
     * This method must convert HTML style char sequences to normal characters.
     * @param text The text to handle.
     * @return The converted text.
     */
//    private static String convertHTMLChars(String text) {
//
//        return htmlConverter.format(text);
//    }
//    
    public static  String getUrlContentsAsText(String url) { 
        String content = ""; 
        StringBean stringBean = new StringBean(); 
        stringBean.setURL(url); 
        content = stringBean.getStrings(); 
        return content; 
	} 
    
}
