package uit.tkorg.dbsa.cores.fetchers;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uit.tkorg.dbsa.gui.fetcher.FetcherPanel;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.OutputPrinter;
import net.sf.jabref.Util;
import net.sf.jabref.imports.HTMLConverter;

public class IEEEXploreFetcher {
	
	public static ArrayList< DBSAPublication> dbsaPublicationResultList = new ArrayList<DBSAPublication>();
	
	public static ArrayList<DBSAPublication> getDbsaPublicationResultList() {
		return dbsaPublicationResultList;
	}
	public static void setDbsaPublicationResultList(
			ArrayList<DBSAPublication> dbsaPublicationResultList) {
		IEEEXploreFetcher.dbsaPublicationResultList = dbsaPublicationResultList;
	}
	OutputPrinter status;
    final static HTMLConverter htmlConverter = new HTMLConverter();  
    private static final int MAX_FETCH = 1000;//100
    private static int perPage = MAX_FETCH;
	static int hits = 0;
	static int unparseable = 0;
	static int parsed = 0;
    private static int piv = 0;
    private static boolean shouldContinue = false;
    private static boolean includeAbstract = true;
    // parameter for autorun
    
    public static boolean flagAutorun = false;
    public static boolean flagReportDone = false;
	public static int numberOfResult =0;
	 //Cac chuoi tao cau query chua tu khoa can tim kiem
	
	private static String terms;
    private final static String startUrl = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_START_URL);
    private final static String endUrl1 = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_END_URL_1) + Integer.toString(perPage);
    private final static String endUrl2 = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_END_URL_2);
    private static String searchUrl;
    /**
     * Kieu cau truy van sau khi duoc thanh lap:
     * http://ieeexplore.ieee.org/search/freesearchresult.jsp?newsearch=true&queryText=database&x=0&y=0
     */
    //Cac pattern tim kiem cac thong tin ve so ket qua tra ve, ket qua lon nhat, file bibtex,abtract cua bai bao va referent cua bai bao
    // Tim kiem so ket qua tra ve tu page
    private final static Pattern hitsPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_HITS_PATTERN));
    // Tim kiem loai cua tai lieu
    private final static Pattern typePattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_TYPE_PATTERN));
    // Tim kiem abstract cua tai lieu
    private final static Pattern absPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_ABS_PATTERN));
    // Map chua string ket qua tra ve va cac pattern de tim kiem cac truong thong tin tai lieu
    private static HashMap<String, String> fieldPatterns = new HashMap<String, String>();

    Pattern stdEntryPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_STD_ENTRY_PATTERN));
    static Pattern publicationPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_PUBLICATION_PATTERN));
    static Pattern proceedingPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_PROCEEDING_PATTERN));
    Pattern abstractLinkPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_ABSTRACT_LINK_PATTREN));
    static String abrvPattern = DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_ABRV_PATTERN);
	
    //private static FetcherResultPanel resultFetch = new FetcherResultPanel(1);
    
    Pattern ieeeArticleNumberPattern = Pattern.compile(DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_ARTICLE_NUMBER_PATTERN));
    
	public IEEEXploreFetcher() {
    	super();    
   }
    public static ArrayList<DBSAPublication> getResultList(String authorName) {
    	processQuery(authorName);
		return dbsaPublicationResultList;
    }
    public static void processQuery(String query){
        terms = query;
        piv = 0;
        shouldContinue = true;
        parsed = 0;
        unparseable = 0;
        int pageNumber = 1;
        searchUrl = makeUrl(pageNumber);//start at page 1
        // System.out.println(searchUrl);
        
        try {
        	URL url = new URL(searchUrl);
        	//System.out.println(searchUrl);
        	String page = getResults(url);
        	
            /**
             * Xu ly cac su kien khong nhan duoc ket qua tra ve tu page
             * 
             * Dua thong bao ra man hinh.
             */
        	
            if (page.indexOf(DBSAResourceBundle.res.getString("search.fail.message")) >= 0) {
            	System.out.print(DBSAResourceBundle.res.getString("search.fail.message"));
                return ;
            }
            
            if (page.indexOf(DBSAResourceBundle.res.getString("bad.request")) >= 0) {
            	System.out.print(DBSAResourceBundle.res.getString("bad.request"));
            	return ;
            }
            
            if (page.indexOf(DBSAResourceBundle.res.getString("no.results.found")) >= 0) {
            	System.out.print(DBSAResourceBundle.res.getString("no.entries.found"));
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
            if(isFlagAutorun() == false) {
            	
            	while (shouldContinue && firstEntry < hits) {
                	pageNumber++;
                    searchUrl = makeUrl(pageNumber);
                    page = getResults(new URL(searchUrl));
                    System.out.printf("Co Chay den phan nay  ");
                    /** Thoat khoi vong lap neu nhan duoc lenh tam dung tu nguoi dung
                     * if (!shouldContinue)
                     * break;
                     */
                    parse(page, 0, firstEntry + 1);
                    firstEntry += perPage;
                }
            	
            }
            else {
            	while (shouldContinue) {
	            	pageNumber++;
	                searchUrl = makeUrl(pageNumber);
	                page = getResults(new URL(searchUrl));
	   
	                System.out.printf("Co Chay den phan nay  ");
	                /** Thoat khoi vong lap neu nhan duoc lenh tam dung tu nguoi dung
	                 * if (!shouldContinue)
	                 * break;
	                 */
	                parse(page, 0, firstEntry + 1);
	                firstEntry += perPage; 	
            	}
            }
            
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
        	// Dua ra thong bao loi ket noi internet cua chuong trinh.
        	System.out.print(DBSAResourceBundle.res.getString("connection.fail.to.ieee.dls"));
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
        sb.append(endUrl1);
        sb.append(endUrl2);
        sb.append(String.valueOf(startIndex));
        System.out.println(sb.toString());
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
     	int entryNumber = firstEntryNumber;
        
        if (isFlagAutorun()==false)
        {
        	int fetcherNumber = FetcherPanel.getIeeeResultNumber() + firstEntryNumber;
        
        //	int entryNumber = firstEntryNumber;

        	while ( shouldContinue) {
        		if (entryNumber >= fetcherNumber) {
        			shouldContinue = false;
        			setFlagReportDone(true);
        			break;
        		}
        		if(shouldContinue){
        			parseNextEntry(text, piv);
        			entryNumber++;
        		}
        	}
	        }   
        else {
        	numberOfResult = hits;
        	while ( shouldContinue) {
        		if (entryNumber >= numberOfResult) {
        			shouldContinue = false;
        			setFlagReportDone(true);
        			break;
        		}
        		if(shouldContinue){
        			parseNextEntry(text, piv);
        			entryNumber++;
        		}
        	}
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
    	String author = (String)entry.getField(DBSAApplicationConst.AUTHOR);
    	if (author != null) {
	    	author = author.replaceAll("\\.", ". ");
	    	author = author.replaceAll("  ", " ");
	    	author = author.replaceAll("\\. -", ".-");
	    	author = author.replaceAll("; ", " and ");
	    	author = author.replaceAll("[,;]$", "");
	    	entry.setField(DBSAApplicationConst.AUTHOR, author);
    	}
    	
    	String title = (String)entry.getField(DBSAApplicationConst.TITLE);
    	if (title != null) {
    		title = title.replaceAll("span class='snippet'>","");
	    	entry.setField(DBSAApplicationConst.TITLE, title);
    	}
    	// clean up publication field
    	BibtexEntryType type = entry.getType();
    	String sourceField = "";
		if (type.getName() == DBSAApplicationConst.ARTICLE) {
        	sourceField = DBSAApplicationConst.JOURNAL;
			entry.clearField(DBSAApplicationConst.BOOKTITLE);
		} else if (type.getName() == DBSAApplicationConst.INPROCEEDINGS){
            sourceField = DBSAApplicationConst.BOOKTITLE;
		}
        String fullName = entry.getField(sourceField);
        if (fullName != null) {
	        if (type.getName() == DBSAApplicationConst.ARTICLE) {
	        	int ind = fullName.indexOf(DBSAResourceBundle.res.getString("accepted.for.future.publication"));
				if (ind > 0) {
					fullName = fullName.substring(0, ind);
					entry.setField(DBSAApplicationConst.YEAR, DBSAResourceBundle.res.getString("to.be.publised"));
					entry.clearField(DBSAApplicationConst.MOTH);
					entry.clearField(DBSAApplicationConst.PAGES);
				}
		        String[] parts = fullName.split("[\\[\\]]"); //[see also...], [legacy...]
		        fullName = parts[0];
		        if (parts.length == 3) {
					fullName += parts[2];
				}
	        } else {
	        	fullName = fullName.replace(DBSAApplicationConst.CONF_PROCEEDINGS, DBSAApplicationConst.PROCEEDINGS).
	        	replace(DBSAApplicationConst.PROCEEDINGS_OF, DBSAApplicationConst.PROCEEDINGS).
	        	replace(DBSAApplicationConst.PROCEEDINGS_OF, DBSAApplicationConst.PROCEEDINGS);
	        	fullName = fullName.replaceAll(DBSAApplicationConst.INTERNATIONAL, DBSAApplicationConst.INT);
	        	fullName = fullName.replaceAll(DBSAApplicationConst.SYMPOSIUM, DBSAApplicationConst.SYMP);
	        	fullName = fullName.replaceAll(DBSAApplicationConst.CONFERENCE, DBSAApplicationConst.CONF);
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
			if (type.getName() == DBSAApplicationConst.INPROCEEDINGS) {
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
				String year = entry.getField(DBSAApplicationConst.YEAR);
				fullName = fullName.replaceAll(", " + year + "\\.?", "");
				
	        	if (fullName.contains(DBSAApplicationConst.ABSTRACT) == false && fullName.contains(DBSAApplicationConst.SUMMARIES) == false && fullName.contains(DBSAApplicationConst.CONFERENCE_RECORD) == false)
	        		fullName = DBSAApplicationConst.PROC + fullName;
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
        
        fieldPatterns.put(DBSAApplicationConst.TITLE, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_TITLE));
        fieldPatterns.put(DBSAApplicationConst.AUTHOR, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_AUTHOR));
        fieldPatterns.put(DBSAApplicationConst.VOLUME, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_VOLUME));
        fieldPatterns.put(DBSAApplicationConst.NUMBER, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_ISSUE));
        fieldPatterns.put(DBSAApplicationConst.YEAR, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_YEAR));
        fieldPatterns.put(DBSAApplicationConst.PAGES, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_PAGE));
        fieldPatterns.put(DBSAApplicationConst.DOI, DBSAApplication.dbsaFetcherPattern.getPattern(DBSAApplicationConst.IEEE_DOI));
        
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
	                type = BibtexEntryType.getType(DBSAApplicationConst.ARTICLE);
	                sourceField = DBSAApplicationConst.JOURNAL;
	            } else if (typeName.equalsIgnoreCase("IEEE Conferences") || typeName.equalsIgnoreCase("IET Conferences")) {
	                type = BibtexEntryType.getType(DBSAApplicationConst.INPROCEEDINGS);
	                sourceField = DBSAApplicationConst.BOOKTITLE;
		        } else if (typeName.equalsIgnoreCase("IEEE Standards")) {
	                type = BibtexEntryType.getType(DBSAApplicationConst.STANDARD);
	                sourceField = DBSAApplicationConst.NUMBER;
		        } else if (typeName.equalsIgnoreCase("IEEE Educational Courses")) {
		        	type = BibtexEntryType.getType(DBSAApplicationConst.ELECTRONIC);
		        	sourceField = DBSAApplicationConst.NOTE;
		        } else if (typeName.equalsIgnoreCase("IEEE Book Chapter")) {
		        	type = BibtexEntryType.getType(DBSAApplicationConst.INCOLLECTION);
		        	sourceField = DBSAApplicationConst.BOOKTITLE;
		        }
            }
            
            if (type == null) {
            	type = BibtexEntryType.getType(DBSAApplicationConst.MISC);
            	sourceField = DBSAApplicationConst.NOTE;
                System.err.println(DBSAResourceBundle.res.getString("type.detection.failed"));
                unparseable++;
                System.err.println(text);
            }
        
            entry = new BibtexEntry(Util.createNeutralId(), type);
            
            if (typeName.equalsIgnoreCase("IEEE Standards")) {
            	entry.setField(DBSAApplicationConst.ORGANIZATION, DBSAApplicationConst.IEEE);
            }
            
            if (typeName.equalsIgnoreCase("IEEE Book Chapter")) {
            	entry.setField(DBSAApplicationConst.PUBLISHER, DBSAApplicationConst.IEEE);
            }
            
            if (typeName.equalsIgnoreCase("IEEE Early Access")) {
            	entry.setField(DBSAApplicationConst.NOTE, DBSAApplicationConst.EARLYACCESS);
            }
       
            Set<String> fields = fieldPatterns.keySet();
            for (String field: fields) {
            	Matcher fieldMatcher = Pattern.compile(fieldPatterns.get(field)).matcher(text);
            	if (fieldMatcher.find()) {
            		entry.setField(field, htmlConverter.format(fieldMatcher.group(1)));
            		if (field.equals(DBSAApplicationConst.TITLE) && fieldMatcher.find()) {
            			String sec_title = htmlConverter.format(fieldMatcher.group(1));
            			if (entry.getType() == BibtexEntryType.getStandardType(DBSAApplicationConst.STANDARD)) {
            				sec_title = sec_title.replaceAll("IEEE Std", "");
            			}
            			entry.setField(sourceField, sec_title);
            		}
            		if (field.equals(DBSAApplicationConst.PAGES) && fieldMatcher.groupCount() == 2) {
            			entry.setField(field, fieldMatcher.group(1) + "-" + fieldMatcher.group(2));
            		}
            	}
            }
           if (entry.getType() == BibtexEntryType.getStandardType(DBSAApplicationConst.INPROCEEDINGS) && entry.getField(DBSAApplicationConst.AUTHOR).equals("")) {
            	entry.setType(BibtexEntryType.getStandardType(DBSAApplicationConst.PROCEEDINGS));
            }
        
            if (includeAbstract) {
            	index = allText.indexOf("<div class=\"abstract RevealContent", piv);
	            if (index >= 0) {
	            	endIndex = allText.indexOf("</div>", index) + 6;
		            piv = endIndex;
	            	text = allText.substring(index, endIndex);
	            	Matcher absMatcher = absPattern.matcher(text);
	            	if (absMatcher.find()) {
	            		entry.setField(DBSAApplicationConst.ABSTRACT, absMatcher.group(1).replaceAll("\\<.*?>",""));
	            	}
	            }
            }	
        }
        
        if (entry == null) {
        	System.out.printf(DBSAResourceBundle.res.getString("parser.failed"));
        	return null;
        } else {
        	cleanup(entry);
        	
    		 DBSAPublication resultDBSAPublicaiton = new DBSAPublication();//them
    		number ++;
			DBSAApplication.fetcherResultPanel.setRowNumber(number);
			if(entry.getField(DBSAApplicationConst.TITLE) == null){
				if(isFlagAutorun() == false)
					entry.setField(DBSAApplicationConst.TITLE, "");
				else
					resultDBSAPublicaiton.setTitle(" ");//them
			}
			if(isFlagAutorun() == false)
				DBSAApplication.fetcherResultPanel.setTitle(entry.getField(DBSAApplicationConst.TITLE));
			else
				resultDBSAPublicaiton.setTitle(entry.getField(DBSAApplicationConst.TITLE));//them
			
			if(entry.getField(DBSAApplicationConst.AUTHOR) == null){
				if(isFlagAutorun() == false)
				entry.setField(DBSAApplicationConst.AUTHOR, "");
				else
				resultDBSAPublicaiton.setAuthors("");//them
				
			}
			if(isFlagAutorun() == false)
				DBSAApplication.fetcherResultPanel.setAuthor(entry.getField(DBSAApplicationConst.AUTHOR));
			else
				resultDBSAPublicaiton.setAuthors(entry.getField(DBSAApplicationConst.AUTHOR));//them
			
			if(entry.getField(DBSAApplicationConst.DOI) == null){
				if(isFlagAutorun() == false)
					entry.setField(DBSAApplicationConst.DOI, "");
				else
					resultDBSAPublicaiton.setLinks("");
			}
			if(isFlagAutorun() == false)
				DBSAApplication.fetcherResultPanel.setLink(DBSAApplicationConst.ARTICLE_IEEE_URL + entry.getField(DBSAApplicationConst.DOI));
			else
				resultDBSAPublicaiton.setLinks(DBSAApplicationConst.ARTICLE_IEEE_URL + entry.getField(DBSAApplicationConst.DOI));//them
			
			
			if(entry.getField(DBSAApplicationConst.YEAR) == null){
				if(isFlagAutorun() == false)
					entry.setField(DBSAApplicationConst.YEAR, "");
				else
					resultDBSAPublicaiton.setYear((Integer) null);//them
			}
			if(isFlagAutorun() == false)
				DBSAApplication.fetcherResultPanel.setYear(Integer.parseInt(entry.getField(DBSAApplicationConst.YEAR)));
			else
				resultDBSAPublicaiton.setYear(Integer.parseInt(entry.getField(DBSAApplicationConst.YEAR)));//them
			
			if(entry.getField(DBSAApplicationConst.ABSTRACT) == null){
				if(isFlagAutorun() == false)
					entry.setField(DBSAApplicationConst.ABSTRACT, "");
				else
					resultDBSAPublicaiton.setAbstractPub(""); //them
			}
			if(isFlagAutorun() == false)
				DBSAApplication.fetcherResultPanel.setAbstract(entry.getField(DBSAApplicationConst.ABSTRACT));
			else
				resultDBSAPublicaiton.setAbstractPub(entry.getField(DBSAApplicationConst.ABSTRACT));//them
			
			if(entry.getField(DBSAApplicationConst.PUBLISHER) != null) {
				if(isFlagAutorun() == false)
					DBSAApplication.fetcherResultPanel.setPublisher(entry.getField(DBSAApplicationConst.PUBLISHER));
				else
					resultDBSAPublicaiton.setPublisher(entry.getField(DBSAApplicationConst.PUBLISHER));
			}
				
			else {
				if(isFlagAutorun() == false)
					DBSAApplication.fetcherResultPanel.setPublisher(DBSAApplicationConst.IEEE);
				else
					resultDBSAPublicaiton.setPublisher(DBSAApplicationConst.IEEE);
			}
			if(isFlagAutorun() == true) {
				dbsaPublicationResultList.add(resultDBSAPublicaiton);
				
				System.out.print("====================== \n");
			}
			else
			{
				DBSAApplication.fetcherResultPanel.setDigitalLibrary("IEEE");
				DBSAApplication.fetcherResultPanel.getResultsJTable();
			}
			
            return entry;
        }
    }
  
    /**
     * Find out how many hits were found.
     * @param page
     */
    private static int getNumberOfHits(String page, String marker, Pattern pattern) throws IOException {
    	int ind = page.indexOf(marker);
    	//System.out.println(page);
        if (ind < 0) {
        	//System.out.println(page);
            throw new IOException(DBSAResourceBundle.res.getString("could.not.pare.number.of.hits"));
        }
        //System.out.println(ind);
        String substring = page.substring(ind, page.length());
        Matcher m = pattern.matcher(substring);
        if (m.find())
            return Integer.parseInt(m.group(1));
        else
        	throw new IOException(DBSAResourceBundle.res.getString("could.not.pare.number.of.hits"));
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
    // Get and set for Autorun 
    
    public static boolean isFlagAutorun() {
		return flagAutorun;
	}
    
	public static void setFlagAutorun(boolean flagAutorun) {
		IEEEXploreFetcher.flagAutorun = flagAutorun;
	}
	
	public static boolean isFlagReportDone() {
			return flagReportDone;
		}
	
	public static void setFlagReportDone(boolean flagReportDone) {
		IEEEXploreFetcher.flagReportDone = flagReportDone;
	}
	
	public static int getNumberOfResult() {
		return numberOfResult;
	}
	
	public static void setNumberOfResult(int numberOfResult) {
		IEEEXploreFetcher.numberOfResult = numberOfResult;
	}
}
