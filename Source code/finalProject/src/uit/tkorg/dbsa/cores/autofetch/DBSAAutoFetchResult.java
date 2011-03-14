package uit.tkorg.dbsa.cores.autofetch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.MyFileWriter;

public class DBSAAutoFetchResult {

	private static int rowNumber = 0;
	private static String title = "";
	private static String author = "";
	private static String link = "";
	private static int year = 0;
	private static String abstracts = "";
	private static String publisher = "";
	private static URL hyperlink = null;
	
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
								
				Object [] data = {resultJTable.getRowCount() + 1, getPubTitle(), getAuthor(), getLink(), year, getAbstract(), getPublisher()};
				model.insertRow(resultJTable.getRowCount(), data );
				
			}
		}
		
	}
	
	public static void saveDataToFile(JTable table){
		
		if(table != null){
			if(table.getRowCount() > 0){
				String temp = "";
				for(int i = 0; i < table.getRowCount(); i++){
					temp += table.getValueAt(i, 0) + " -|- ";
					temp += table.getValueAt(i, 1) + " -|- ";
					temp += table.getValueAt(i, 2) + " -|- ";
					temp += table.getValueAt(i, 3) + " -|- ";
					temp += table.getValueAt(i, 4) + " -|- ";
					temp += table.getValueAt(i, 5) + " -|- ";
					temp += table.getValueAt(i, 6) + " -|- \n";
				}
				
				MyFileWriter.writeToFile(DBSAApplicationConst.AUTO_FETCHER_RESULTS_LINK, temp);
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

}
