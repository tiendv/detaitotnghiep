package uit.tkorg.dbsa.cores.fetchers;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.GUIGlobals;
import net.sf.jabref.Globals;
import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ScienceDirectFetcher {

    protected static int MAX_PAGES_TO_LOAD = 8;
    protected static final String WEBSITE_URL = "http://www.sciencedirect.com";
    protected static final String SEARCH_URL = WEBSITE_URL +"/science/quicksearch?query=";

    protected static final String linkPrefix = "http://www.sciencedirect.com/science?_ob=ArticleURL&" ;
    protected static final Pattern linkPattern = Pattern.compile("<a href=\""+linkPrefix.replaceAll("\\?", "\\\\?")+"([^\"]+)\"\"");

    protected static final Pattern nextPagePattern = Pattern.compile("<a href=\"(.*)\">Next &gt;");
    protected static boolean stopFetching = false;
    protected boolean noAccessFound = false;

    public String getHelpPage() {
        return "ScienceDirect.html";
    }

    public URL getIcon() {
        return GUIGlobals.getIconUrl("www");
    }

    public String getKeyName() {
        return "Search ScienceDirect";
    }

    public JPanel getOptionsPanel() {
        // No Options panel
        return null;
    }

    public String getTitle() {
        return Globals.menuTitle("Search ScienceDirect");
    }

    public void stopFetching() {
        stopFetching = true;
        noAccessFound = false;
    }

    public static void processQuery1(String query) {
        stopFetching = false;
        try {
            List<String> citations = getCitations(query);
            if (citations == null)
                return ;
            if (citations.size() == 0){
                System.out.printf("No entries found for the search string");    
                return;
            }

            for (String cit : citations) {
                if (stopFetching)
                    break;
                BibtexEntry entry = BibsonomyScraper.getEntry(cit);
                System.out.println("Title : " + entry.getField("title"));
        		System.out.println("Authors : " + entry.getField("author"));
        		System.out.println("Year : " + entry.getField("year"));
        		System.out.println("Abstract : " + entry.getField("abstract"));
        		System.out.println("Publisher : " + entry.getField("sourceField"));
        		System.out.println("Doi : " + entry.getField("doi"));
               
            }


        } catch (IOException e) {
        	System.out.printf("Error while fetching from ScienceDirect");
        }
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
            urlQuery = SEARCH_URL + URLEncoder.encode(query, "UTF-8");
            urlQuery= "http://www.sciencedirect.com/science?_ob=ArticleListURL&_method=list&_ArticleListID=1505850694&_sort=r&_st=13&view=c&_acct=C000050221&_version=1&_urlVersion=0&_userid=10&md5=4a2f616cca9bd223083116db70647bb2&searchtype=a";
            System.out.printf(urlQuery);
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
        //String entirePage = cont;
        Matcher m = linkPattern.matcher(cont);
        if (m.find()) {
            while (m.find()) {
                ids.add(linkPrefix+m.group(1));
                cont = cont.substring(m.end());
                m = linkPattern.matcher(cont);
            }
        }

        else {
            return null;
        }
        /*m = nextPagePattern.matcher(entirePage);
        if (m.find()) {
            String newQuery = WEBSITE_URL +m.group(1);
            return newQuery;
        }
        else*/
            return null;
    }

}