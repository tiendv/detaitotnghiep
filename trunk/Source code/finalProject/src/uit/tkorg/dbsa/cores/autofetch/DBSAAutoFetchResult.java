package uit.tkorg.dbsa.cores.autofetch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uit.tkorg.dbsa.model.DBSAPublication;

public class DBSAAutoFetchResult {

	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static String link = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String publisher = "";
	private static boolean mark = false;
	private static URL hyperlink = null;
	private static boolean isDuplicate = false;
	public static String digitalLibrary;
	public static int id;
	
	public DBSAAutoFetchResult(){
		
	}
	
	public static void insertToJTable(JTable resultJTable, DefaultTableModel model){
		ArrayList<DBSAPublication> resultList = DBSAConfigAutoFetch.autoFetchResult();
		
		if(resultList != null){
			for(int i = 0; i < resultList.size(); i++){
				if(resultList.get(i).getTitle() != null)
					setPubTitle(resultList.get(i).getTitle());
				else
					setPubTitle("");
				
				if(resultList.get(i).getAuthors() != null)
					setAuthor(resultList.get(i).getAuthors());
				else
					setAuthor("");
				
				if(resultList.get(i).getLinks() != null)
					setLink(resultList.get(i).getLinks());
				else
					setLink("");
				
				String year;
				if(resultList.get(i).getYear() != 0){
					setYear(resultList.get(i).getYear());
					year = getYear() + "";
				}
				else{
					setYear(0);
					year = "";
				}
				
				if(resultList.get(i).getAbstractPub() != null)
					setAbstract(resultList.get(i).getAbstractPub());
				else
					setAbstract("");
				
				if(resultList.get(i).getPublisher() != null)
					setPublisher(resultList.get(i).getPublisher());
				else
					setPublisher("");
				
				setID(resultList.get(i).getId());
								
				Object [] data = {resultJTable.getRowCount() + 1, getPubTitle(), getAuthor(), getLink(), year, getAbstract(), getPublisher(), getMark(), getIsDuplicate(), getID()};
				model.insertRow(resultJTable.getRowCount(), data );
				
			}
		}
		
	}
		
	/*
	 * 
	 * set & get paper bibliography 
	 */
	
	public static void setRowNumber(int number){
		rowNumber = number;
	}
	
	public  static int getRowNumber(){
		return rowNumber;
	}
	
	public static  void setPubTitle(String titleString){
		title = titleString;
	}

	public static String getPubTitle(){
		return title;
	}
	
	public static  void setAuthor(String authorString){
		author = authorString;
	}

	public static  String getAuthor(){
		return author;
	}
	
	public static void setLink(String linkString){
		link = linkString;
	}
	
	public static String getLink(){
		return link;
	}	
	
	public static  void setYear(int _year){
		year = _year;
	}

	public static  int getYear(){
		return year;
	}
	
	public static  void setAbstract(String abstractString){
		abstracts = abstractString;
	}

	public static  String getAbstract(){
		return abstracts;
	}
	
	public static  void setPublisher(String publisherString){
		publisher = publisherString;
	}

	public static  String getPublisher(){
		return publisher;
	}
	
	public static void setMark(boolean isMark){
		mark = isMark;
	}
	
	public static  boolean getMark(){
		return mark;
	}
	
	public static void setHyperLink(String _hyperLink){
		try {
			hyperlink = new URL(_hyperLink);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static URL getHyperLink(){
		return hyperlink;
	}
	
	public static void setIsDuplicate(boolean duplicate){
		isDuplicate = duplicate;
	}
	
	public static boolean getIsDuplicate(){
		return isDuplicate;
	}
	
	public static void setDigitalLibrary(String dl){
		digitalLibrary = dl;
	}
	
	public static String getDigitalLibrary(){
		return digitalLibrary;
	}
	public static   int getID(){
		return id;
	}
	
	public static  void setID(int iD){
		id = iD;
	}
}
