package uit.tkorg.dbsa.cores.fetchers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jabref.Globals;

import antlr.ByteBuffer;

public class ACMFetcher {
	
	//Tu khoa can tim kiem
	private String keywordString = null;
	
	private static String startUrl = "http://portal.acm.org/";
	private static String searchUrlPart = "results.cfm?query=";
	private static String searchUrlPartII = "&dl=";
	static String endUrl = "&coll=Portal&short=0";//&start=";
	/**
	 * ex: http://portal.acm.org/results.cfm?coll=ACM&dl=ACM&CFID=107834110&CFTOKEN=22371969
	 */
	
	private static int hits = 0;
	
	private static Pattern hitsPattern = Pattern.compile(".*Found <b>(\\d+,*\\d*)</b> of.*");
	private static Pattern maxHitsPattern = Pattern.compile(".*Results \\d+ - \\d+ of (\\d+,*\\d*).*");
	
	public ACMFetcher(){
		
	}
	
	public static URL MakeUrl(String keyword){
		StringBuffer sb = new StringBuffer(startUrl).append(searchUrlPart);
		
		sb.append(keyword.replaceAll(" ", "20%"));
		sb.append(searchUrlPartII);
		sb.append("ACM");
		sb.append(endUrl);
		System.out.println(" sb.toString = " + sb.toString());
		URL urlSearch = null;
		
		try {
			urlSearch = new URL(sb.toString());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		System.out.println("urlsearch = " + urlSearch);
		return urlSearch;
	}
	
	public static void Fetcher(String keyword){
		
		/*
		 * 
		 */
		URL url = MakeUrl(keyword);
		
		/*
		 * 
		 */
		String page = getFetcherResult(url);
		
		FileOutputStream fileOutput;
		DataOutputStream dataOutput;
		File file = new File("C:\\abc.html");
		try {
			fileOutput = new FileOutputStream(file);
			dataOutput =  new DataOutputStream(fileOutput);
			try {
				dataOutput.writeChars(page);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		
		GetResultList(page);
		//System.out.println("---------====><====-----------" + page);
	}
	
	public static String getFetcherResult(URL url){
		
		StringBuffer sb = new StringBuffer();
		
		try {
			InputStream in = url.openStream();
			byte [] buffer = new byte[256];
			
			while(true){
				int byteRead = in.read(buffer);
				if(byteRead == -1)
					break;
				for(int i = 0; i < byteRead; i++){
					sb.append((char)buffer[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static void GetResultList(String page){
		try {
			hits = getNumberOfHits(page, "Found", hitsPattern);
			
			int index = page.indexOf("Found");
			System.out.println("index = " + index);
			if (index >= 0) {
				//System.out.println("page = " + page);
            	page = page.substring(index + 9, index + 12);
            	System.out.println("page = index + 9 = " + page);
				index = page.indexOf("Found");
				if (index >= 0)
            		page = page.substring(index);
			}
			
			//int maxHits = getNumberOfHits(page, "Results", maxHitsPattern);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(">''< ==> " + hits);
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
}
