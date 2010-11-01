package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.fetcher.FetcherResultPanel;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.Globals;
import net.sf.jabref.OutputPrinter;
import net.sf.jabref.Util;
import net.sf.jabref.imports.HTMLConverter;

public class IEEEXploreFetcher {

	OutputPrinter status;
    final static HTMLConverter htmlConverter = new HTMLConverter();  
    private static final int MAX_FETCH = 50;//100
    private static int perPage = MAX_FETCH;
	static int hits = 0;
	static int unparseable = 0;
	static int parsed = 0;
    private static int piv = 0;
    private static boolean shouldContinue = false;
    private static boolean includeAbstract = true;
  //Cac chuoi tao cau query chua tu khoa can tim kiem
    
    private static String terms;
    private final static String startUrl = "http://ieeexplore.ieee.org/search/freesearchresult.jsp?queryText=";
    private final static String endUrl = "&rowsPerPage=" + Integer.toString(perPage) + "&pageNumber=";
    private static String searchUrl;
    /**
     * Kieu cau truy van sau khi duoc thanh lap:
     * http://ieeexplore.ieee.org/search/freesearchresult.jsp?newsearch=true&queryText=database&x=0&y=0
     */
    
    //Cac pattern tim kiem cac thong tin ve so ket qua tra ve, ket qua lon nhat, fiel bibtex,abtract cua bai bao va referent cua bai bao
    // Tim kiem so ket qua tra ve tu page
    private final static Pattern hitsPattern = Pattern.compile("([0-9,]+) results");
    // Tim kiem loai cua tai lieu
    private final static Pattern typePattern = Pattern.compile("<span class=\"type\">\\s*(.+)");
    // Tim kiem abstract cua tai lieu
    private final static Pattern absPattern = Pattern.compile("<p>\\s*(.+)");
    // Map chua string ket qua tra ve va cac pattern de tim kiem cac truong thong tin tai lieu
    private static HashMap<String, String> fieldPatterns = new HashMap<String, String>();

    Pattern stdEntryPattern = Pattern.compile(".*<strong>(.+)</strong><br>"+ "\\s+(.+)");
    static Pattern publicationPattern = Pattern.compile("(.*), \\d*\\.*\\s?(.*)");
    static Pattern proceedingPattern = Pattern.compile("(.*?)\\.?\\s?Proceedings\\s?(.*)");
    Pattern abstractLinkPattern = Pattern.compile("<a href=\"(.+)\" class=\"bodyCopySpaced\">Abstract</a>");
    static String abrvPattern = ".*[^,] '?\\d+\\)?";
	private static FetcherResultPanel resultFetch = new FetcherResultPanel();
    Pattern ieeeArticleNumberPattern = Pattern.compile("<a href=\".*arnumber=(\\d+).*\">");
    
    
    public IEEEXploreFetcher() {
    	super();
        }
    
    public static void processQuery(String query){
        terms = query;
        piv = 0;
        shouldContinue = true;
        parsed = 0;
        unparseable = 0;
        int pageNumber = 1;
        searchUrl = makeUrl(pageNumber);//start at page 1
        
        try {
        	URL url = new URL(searchUrl);
        	String page = getResults(url);
        	
            /**
             * Xu ly cac su kien khong nhan duoc ket qua tra ve tu page
             * 
             * Dua thong bao ra man hinh.
             */
        	
            if (page.indexOf("You have entered an invalid search") >= 0) {
            	System.out.print("You have entered an invalid search ");
                return ;
            }
            
            if (page.indexOf("Bad request") >= 0) {
            	System.out.print("Bad Request");
            	return ;
            }
            
            if (page.indexOf("No results were found.") >= 0) {
            	System.out.print("No entries found for the search string");
                return ;
            }
            
            // Tim so ket qua tra ve tu trang 
            hits = getNumberOfHits(page, "display-status", hitsPattern);
        
            /**
             * Neu so ket qua tim kiem duoc lon hon so ket qua gioi han boi chuong trinh
             * Dua thong bao ra man hinh
             */
            if (hits > MAX_FETCH) {
           		hits = MAX_FETCH;
            }

            parse(page, 0, 1);
            int firstEntry = perPage;
            while (shouldContinue && firstEntry < hits) {
            	pageNumber++;
                searchUrl = makeUrl(pageNumber);
                page = getResults(new URL(searchUrl));
                
                /** Thoat khoi vong lap neu nhan duoc lenh tam dung tu nguoi dung
                 * if (!shouldContinue)
                 * break;
                 */
                parse(page, 0, firstEntry + 1);
                firstEntry += perPage;

            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
        	// Dua ra thong bao loi ket noi internet cua chuong trinh.
        	System.out.print("Loi trong qua trinh connection voi IEEE");
        } catch (IOException e) {
        	 e.printStackTrace();
        }
    }
    
    /**
     * This method is called by the dialog when the user has cancelled.
     * 
     */
    public void stopFetching() {
        shouldContinue = false;
    }
    /**
     * @param Tao URL ket noi voi thu vien so
     * @return
     */
    private static String makeUrl(int startIndex) {
        StringBuffer sb = new StringBuffer(startUrl);
        sb.append(terms.replaceAll(" ", "+"));
        sb.append(endUrl);
        sb.append(String.valueOf(startIndex));
        return sb.toString();
    }
    /**
     * 
     * @param text : Noi dung lay ve tu thu vien so
     * @param startIndex: page lay ve
     * @param firstEntryNumber : so ket qua lay ve
     */
    private static void parse(String text, int startIndex, int firstEntryNumber) {
        piv = startIndex;
        int fetcherNumber = FetcherPanel.getIeeeResultNumber() + firstEntryNumber;
        
        int entryNumber = firstEntryNumber;

	    while ( shouldContinue) {

	            parseNextEntry(text, piv);
	            entryNumber++;
	            
	            if(entryNumber >= fetcherNumber){
	            	shouldContinue = false;
	            	break;
	            }
	            
	            //FetcherPanel.setIeeeProgressBar(entryNumber/fetcherNumber*100);
	            //FetcherPanel.PROPER
	        }   
    }
    /**
     * Hoan chinh noi dung file Bibtex
     * @param entry : Bibtex sau khi duoc tao
     * @return
     */
    private static BibtexEntry cleanup(BibtexEntry entry) {
    	if (entry == null)
    		return null;
    	// clean up author
    	String author = (String)entry.getField("author");
    	if (author != null) {
	    	author = author.replaceAll("\\.", ". ");
	    	author = author.replaceAll("  ", " ");
	    	author = author.replaceAll("\\. -", ".-");
	    	author = author.replaceAll("; ", " and ");
	    	author = author.replaceAll("[,;]$", "");
	    	entry.setField("author", author);
    	}
    	
    	String title = (String)entry.getField("title");
    	if (title != null) {
    		title = title.replaceAll("span class='snippet'>","");
	    	entry.setField("title", title);
    	}
    	// clean up publication field
    	BibtexEntryType type = entry.getType();
    	String sourceField = "";
		if (type.getName() == "Article") {
        	sourceField = "journal";
			entry.clearField("booktitle");
		} else if (type.getName() == "Inproceedings"){
            sourceField = "booktitle";
		}
        String fullName = entry.getField(sourceField);
        if (fullName != null) {
	        if (type.getName() == "Article") {
	        	int ind = fullName.indexOf(": Accepted for future publication");
				if (ind > 0) {
					fullName = fullName.substring(0, ind);
					entry.setField("year", "to be published");
					entry.clearField("month");
					entry.clearField("pages");
				}
		        String[] parts = fullName.split("[\\[\\]]"); //[see also...], [legacy...]
		        fullName = parts[0];
		        if (parts.length == 3) {
					fullName += parts[2];
				}
	        } else {
	        	fullName = fullName.replace("Conference Proceedings", "Proceedings").replace("Proceedings of", "Proceedings").replace("Proceedings.", "Proceedings");
	        	fullName = fullName.replaceAll("International", "Int.");
	        	fullName = fullName.replaceAll("Symposium", "Symp.");
	        	fullName = fullName.replaceAll("Conference", "Conf.");
	        	fullName = fullName.replaceAll(" on", " ").replace("  ", " ");
	        }
	        
	        Matcher m1 = publicationPattern.matcher(fullName);
			if (m1.find()) {
				String prefix = m1.group(2).trim();
				String postfix = m1.group(1).trim();
				String abrv = "";
				String[] parts = prefix.split("\\. ", 2);
				if (parts.length == 2) {
					if (parts[0].matches(abrvPattern)) {
						prefix = parts[1];
						abrv = parts[0];
					} else {
						prefix = parts[0];
						abrv = parts[1];
					}
				}
				if (prefix.matches(abrvPattern) == false) {
					fullName = prefix + " " + postfix + " " + abrv;
					fullName = fullName.trim();
				} else {
					fullName = postfix + " " + prefix;
				}
			}
			if (type.getName() == "Inproceedings") {
	            Matcher m2 = proceedingPattern.matcher(fullName);
				if (m2.find()) {
					String prefix = m2.group(2); 
					String postfix = m2.group(1).replaceAll("\\.$", "");
					if (prefix.matches(abrvPattern) == false) {
						String abrv = "";
					
						String[] parts = postfix.split("\\. ", 2);
						if (parts.length == 2) {
							if (parts[0].matches(abrvPattern)) {
								postfix = parts[1];
								abrv = parts[0];
							} else {
								postfix = parts[0];
								abrv = parts[1];
							}
						}
						fullName = prefix.trim() + " " + postfix.trim() + " " + abrv;
						
					} else {
						fullName = postfix.trim() + " " + prefix.trim();
					}
					
				}
				
				fullName = fullName.trim();
				
				fullName = fullName.replaceAll("^[tT]he ", "").replaceAll("^\\d{4} ", "").replaceAll("[,.]$", "");
				String year = entry.getField("year");
				fullName = fullName.replaceAll(", " + year + "\\.?", "");
				
	        	if (fullName.contains("Abstract") == false && fullName.contains("Summaries") == false && fullName.contains("Conference Record") == false)
	        		fullName = "Proc. " + fullName;
	        }
			entry.setField(sourceField, fullName);
        }
		return entry;
    }
   /**
    * 
    * @param allText
    * @param startIndex
    * @return
    */
    static int number = 0;

	private static BibtexEntry parseNextEntry(String allText, int startIndex) {
        BibtexEntry entry = null;
        
        fieldPatterns.put("title", "<a\\s*href=[^<]+>\\s*(.+)\\s*</a>");
        fieldPatterns.put("author", "<p>\\s+(.+)");
        fieldPatterns.put("volume", "Volume:\\s*(\\d+)");
        fieldPatterns.put("number", "Issue:\\s*(\\d+)");
        fieldPatterns.put("year", "Publication Year:\\s*(\\d{4})");
        fieldPatterns.put("pages", "Page\\(s\\):\\s*(\\d+)\\s*-\\s*(\\d*)");
        fieldPatterns.put("doi", "Digital Object Identifier:\\s*<a href=.*>(.+)</a>");
        
     	int index = allText.indexOf("<div class=\"detail", piv);
        int endIndex = allText.indexOf("</div>", index);

        if (index >= 0 && endIndex > 0) {
        	endIndex += 6;
        	piv = endIndex;
        	String text = allText.substring(index, endIndex);
            
            BibtexEntryType type = null;
            String sourceField = null;
            
            String typeName = "";
            Matcher typeMatcher = typePattern.matcher(text);
            if (typeMatcher.find()) {
	            typeName = typeMatcher.group(1);
	            if (typeName.equalsIgnoreCase("IEEE Journals") || typeName.equalsIgnoreCase("IEEE Early Access") ||
	            		typeName.equalsIgnoreCase("IET Journals") || typeName.equalsIgnoreCase("AIP Journals") ||
					   	typeName.equalsIgnoreCase("AVS Journals") || typeName.equalsIgnoreCase("IBM Journals")) {
	                type = BibtexEntryType.getType("article");
	                sourceField = "journal";
	            } else if (typeName.equalsIgnoreCase("IEEE Conferences") || typeName.equalsIgnoreCase("IET Conferences")) {
	                type = BibtexEntryType.getType("inproceedings");
	                sourceField = "booktitle";
		        } else if (typeName.equalsIgnoreCase("IEEE Standards")) {
	                type = BibtexEntryType.getType("standard");
	                sourceField = "number";
		        } else if (typeName.equalsIgnoreCase("IEEE Educational Courses")) {
		        	type = BibtexEntryType.getType("Electronic");
		        	sourceField = "note";
		        } else if (typeName.equalsIgnoreCase("IEEE Book Chapter")) {
		        	type = BibtexEntryType.getType("inCollection");
		        	sourceField = "booktitle";
		        }
            }
            
            if (type == null) {
            	type = BibtexEntryType.getType("misc");
            	sourceField = "note";
                System.err.println("Type detection failed. Use MISC instead.");
                unparseable++;
                System.err.println(text);
            }
        
            entry = new BibtexEntry(Util.createNeutralId(), type);
            
            if (typeName.equalsIgnoreCase("IEEE Standards")) {
            	entry.setField("organization", "IEEE");
            }
            
            if (typeName.equalsIgnoreCase("IEEE Book Chapter")) {
            	entry.setField("publisher", "IEEE");
            }
            
            if (typeName.equalsIgnoreCase("IEEE Early Access")) {
            	entry.setField("note", "Early Access");
            }
       
            Set<String> fields = fieldPatterns.keySet();
            for (String field: fields) {
            	Matcher fieldMatcher = Pattern.compile(fieldPatterns.get(field)).matcher(text);
            	if (fieldMatcher.find()) {
            		entry.setField(field, htmlConverter.format(fieldMatcher.group(1)));
            		if (field.equals("title") && fieldMatcher.find()) {
            			String sec_title = htmlConverter.format(fieldMatcher.group(1));
            			if (entry.getType() == BibtexEntryType.getStandardType("standard")) {
            				sec_title = sec_title.replaceAll("IEEE Std", "");
            			}
            			entry.setField(sourceField, sec_title);
            		}
            		if (field.equals("pages") && fieldMatcher.groupCount() == 2) {
            			entry.setField(field, fieldMatcher.group(1) + "-" + fieldMatcher.group(2));
            		}
            	}
            }
           if (entry.getType() == BibtexEntryType.getStandardType("inproceedings") && entry.getField("author").equals("")) {
            	entry.setType(BibtexEntryType.getStandardType("proceedings"));
            }
        
            if (includeAbstract) {
            	index = allText.indexOf("<div class=\"abstract RevealContent", piv);
	            if (index >= 0) {
	            	endIndex = allText.indexOf("</div>", index) + 6;
		            piv = endIndex;
	            	text = allText.substring(index, endIndex);
	            	Matcher absMatcher = absPattern.matcher(text);
	            	if (absMatcher.find()) {
	            		entry.setField("abstract", absMatcher.group(1).replaceAll("\\<.*?>",""));
	            	}
	            }
            }	
        }
        
        if (entry == null) {
        	System.out.printf("Khong part duoc ");
        	return null;
        } else {
        	cleanup(entry);
        	System.out.println("Title : " + entry.getField("title"));
    		System.out.println("Authors : " + entry.getField("author"));
    		System.out.println("Year : " + entry.getField("year"));
    		System.out.println("Abstract : " + entry.getField("abstract"));
    		System.out.println("Publisher : " + entry.getField("sourceField"));
    		System.out.println("Doi : " + entry.getField("doi"));
    		
    		number ++;
			resultFetch.setRowNumber(number);
			resultFetch.setTitle(entry.getField("title"));
			resultFetch.setAuthor(entry.getField("author"));
			resultFetch.setYear(entry.getField("year"));
			resultFetch.setAbstract(entry.getField("abstract"));
			resultFetch.setPublisher(entry.getField("publisher"));
			
			resultFetch.getResultsJTable();
			//resultFetch.getResultsJScrollPane();
			//resultFetch.updateTable();
            return entry;
        }
    }
  
    /**
     * Find out how many hits were found.
     * @param page
     */
    private static int getNumberOfHits(String page, String marker, Pattern pattern) throws IOException {
    	int ind = page.indexOf(marker);
        if (ind < 0) {
        	System.out.println(page);
            throw new IOException(Globals.lang("Could not parse number of hits"));
        }
        String substring = page.substring(ind, page.length());
        Matcher m = pattern.matcher(substring);
        if (m.find())
            return Integer.parseInt(m.group(1));
        else
        	throw new IOException(Globals.lang("Could not parse number of hits"));
    }

    /**
     * Download the URL and return contents as a String.
     * @param source
     * @return
     * @throws IOException
     */
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
