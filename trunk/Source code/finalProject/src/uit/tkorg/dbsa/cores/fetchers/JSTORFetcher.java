package uit.tkorg.dbsa.cores.fetchers;

import net.sf.jabref.BibtexEntry;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JSTORFetcher{

	public JSTORFetcher() {
	}
    protected static int MAX_PAGES_TO_LOAD = 8;
    protected static final String JSTOR_URL = "http://www.jstor.org";
    protected static final String SEARCH_URL = JSTOR_URL+"/action/doBasicSearch?Query=";
    protected static final String SEARCH_URL_END = "&x=0&y=0&wc=on";
    protected static final String SINGLE_CIT_ENC = "http://www.jstor.org/action/exportSingleCitation?singleCitation=true&suffix=";
            //"http%3A%2F%2Fwww.jstor.org%2Faction%2FexportSingleCitation%3FsingleCitation"
            //+"%3Dtrue%26suffix%3D";
    
    protected static final Pattern idPattern = Pattern.compile("<a class=\"title\" href=\"/stable/(\\d+)\\?");
    protected static final Pattern nextPagePattern = Pattern.compile("<a href=\"(.*)\">Next &gt;");
    protected static final String noAccessIndicator = "We do not recognize you as having access to JSTOR";
    protected static boolean stopFetching = false;
    protected static boolean noAccessFound = false;
    
    public void stopFetching() {
        stopFetching = true;
        noAccessFound = false;
    }

    public static void  processQuery(String query) {
    	
        stopFetching = false;
        try {
            List<String> citations = getCitations(query);
            if (citations == null)
                return ;
            if (citations.size() == 0){
                if (!noAccessFound)
                    System.out.printf("No entries found for the search string");
                else {
                	System.out.printf("No entries found. It looks like you do not have access to search JStor.");
                }
                return ;
            }
            for (String cit : citations) {
                if (stopFetching)
                    break;
                BibtexEntry entry = getSingleCitation(cit);
                System.out.println("Title : " + entry.getField("title"));
        		System.out.println("Authors : " + entry.getField("author"));
        		System.out.println("Year : " + entry.getField("year"));
        		System.out.println("Abstract : " + entry.getField("abstract"));
        		System.out.println("Publisher : " + entry.getField("sourceField"));
        		System.out.println("Doi : " + entry.getField("doi"));
            return ;
            }
            
        } catch (IOException e) {
           System.out.printf("Error while fetching from JSTOR");
        }
        return ;
    }

    /**
     *
     * @param query
     *            The search term to query JStor for.
     * @return a list of IDs
     * @throws java.io.IOException
     */
    protected static List<String> getCitations(String query) throws IOException {
        String urlQuery;
        ArrayList<String> ids = new ArrayList<String>();
        try {
            urlQuery = SEARCH_URL + URLEncoder.encode(query, "UTF-8") + SEARCH_URL_END;
            int count = 1;
            String nextPage = null;
            while (((nextPage = getCitationsFromUrl(urlQuery, ids)) != null)
                    && (count < MAX_PAGES_TO_LOAD)) {
                urlQuery = nextPage;
                count++;
            }
            return ids;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String getCitationsFromUrl(String urlQuery, List<String> ids) throws IOException {
        URL url = new URL(urlQuery);
        URLDownload ud = new URLDownload(url);
        ud.download();

        String cont = ud.getStringContent();
        String entirePage = cont;

        Matcher m = idPattern.matcher(cont);
        if (m.find()) {
            while (m.find()) {
                ids.add(m.group(1));
                cont = cont.substring(m.end());
                m = idPattern.matcher(cont);
            }
        }
        else if (entirePage.indexOf(noAccessIndicator) >= 0) {
            noAccessFound = true;
            return null;
        }
        else {
            return null;
        }
        m = nextPagePattern.matcher(entirePage);
        if (m.find()) {
            String newQuery = JSTOR_URL+m.group(1);
            return newQuery;
        }
        else
            return null;
    }

    protected static BibtexEntry getSingleCitation(String cit) {
        return BibsonomyScraper.getEntry(SINGLE_CIT_ENC+cit);
    }

}