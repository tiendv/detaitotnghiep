package uit.tkorg.dbsa.cores.autofetch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import uit.tkorg.dbsa.actions.autorun.ACMFetcherAutoRun;
import uit.tkorg.dbsa.actions.autorun.IEEEXploreFetcherAutoRun;
import uit.tkorg.dbsa.actions.database.LoadAuthor;
import uit.tkorg.dbsa.gui.autofetch.DBSAAutoFetchResultPanel;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.gui.main.DBSAResourceBundle;
import uit.tkorg.dbsa.model.Author;
import uit.tkorg.dbsa.model.DBSAPublication;
import uit.tkorg.dbsa.model.Publication;
import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.FileLoadder;
import uit.tkorg.dbsa.properties.files.MyFileWriter;

public class DBSAConfigAutoFetch {
	private static ArrayList<String> parameterItems;
	public DBSAConfigAutoFetch() {
		
	}
	public static void loadParameterFile(){
		parameterItems = FileLoadder.loadTextFile(DBSAApplicationConst.AUTO_FETCHER_PARAMETERS_LIST_LINK);
	}
	
	//get danh sach keyword tu file tu khoa do nguoi dung luu
	public static DefaultListModel addItemToRightKeywordList(DefaultListModel listModel){
		
		ArrayList<String> items = FileLoadder.loadTextFile(DBSAApplicationConst.KEYWORD_LIST_LINK);
		
		if(items != null){
			for(int i = 0; i < items.size(); i++)
				listModel.addElement(items.get(i));
		}else{
			//khong co tu khoa trong csdl
		}
		
		return listModel;
	}
	
	//get danh sach keyword tu database
	public  static DefaultListModel addItemToLeftKeyworList(DefaultListModel listModel, int maxItems){
		ArrayList<Author> authorList = LoadAuthor.getAuthor(maxItems);
		
		if(authorList == null){
			//khong co tu khoa trong csdl
		}else{					
			for(int i = 0; i < authorList.size(); i++ ){
				listModel.addElement(authorList.get(i).getAuthor());
			}
			
		}
		
		return listModel;
	}
	
	//them tu khoa moi vao danh sach
	public static void addNewKeyword(DefaultListModel listModel, String keyword){
		
		if(keyword != "" && keyword != null){
			listModel.addElement(keyword);
		}
	}
	
	//xoa tu khoa khoi danh sach
	public static void deleteKeyword(DefaultListModel listModel, int itemsIsSelected[]){
		if(listModel != null){
			if(itemsIsSelected.length >= 0 && itemsIsSelected.length <= listModel.getSize()){
				for(int i = itemsIsSelected.length - 1; i >= 0; i--){
					
					listModel.remove(itemsIsSelected[i]);
				}			
			}else{
				JOptionPane.showMessageDialog(null, "Vui long chon danh sach tu khoa muon xoa");
			}
		}
	}
	
	//xoa tat ca tu khoa khoi danh sach
	public static void deleteAllKeyword(DefaultListModel listModel){
		if(listModel.size() > 0){
			for(int i = listModel.size() - 1; i >= 0; i--){
				listModel.remove(i);
			}
		}
			
	}
	
	//Move item left col to right col
	public static void moveLeftToRightKeyword(DefaultListModel leftListModel, DefaultListModel rightListModel, int itemIsSelected[]){
		
		if(itemIsSelected.length >= 0 && itemIsSelected.length <= leftListModel.getSize()){
			for(int i = itemIsSelected.length - 1; i >= 0; i--){
				rightListModel.addElement(leftListModel.getElementAt(itemIsSelected[i]));
				leftListModel.remove(itemIsSelected[i]);
			}			
		}else{
			JOptionPane.showMessageDialog(null, "Vui long chon danh sach tu khoa muon di chuyen");
		}
	}
	
	
	//Move item right col to left col
	public static void moveRightToKeyword(DefaultListModel leftListModel, DefaultListModel rightListModel, int itemIsSelected[]){
		
		if(itemIsSelected.length >= 0 && itemIsSelected.length <= rightListModel.getSize()){
			for(int i = itemIsSelected.length - 1; i >= 0; i--){
				leftListModel.addElement(rightListModel.getElementAt(itemIsSelected[i]));
				rightListModel.remove(itemIsSelected[i]);
			}			
		}else{
			JOptionPane.showMessageDialog(null, "Vui long chon danh sach tu khoa muon di chuyen");
		}
	}
	
	//luu danh sach tu khoa 
	public static void saveKeyword(DefaultListModel leftListModel){
		
		if(leftListModel != null){
			String temp = "";
			if(leftListModel.size() != 0){
				for(int i = 0; i < leftListModel.size(); i++){
					temp += leftListModel.get(i).toString();
					temp += "\n";
				}
				
				MyFileWriter.writeToFile(DBSAApplicationConst.KEYWORD_LIST_LINK, temp);
				
				JOptionPane.showMessageDialog(null, "Ban da luu thanh cong!");
			}else{
				JOptionPane.showMessageDialog(null, "khong co tu khoa nao trong danh sach. Vui long them tu khoa hoac chon them tu khoa tu ben cot ben trai.");
			}
		}
	}
	
	//lay lua chon thoi gian
	public static int getTimerSelected(){
		int timer = 0;

		String parameterName= "";
		String parameterValue = "";
		
		for(int i = 0; i < parameterItems.size(); i++){
			//tim vi tri cua chuoi "###" 
			int endIndex = parameterItems.get(i).indexOf("###");
			
			parameterName = parameterItems.get(i).substring(0,endIndex);			
			
			if(parameterName.equals(DBSAApplicationConst.TIMER)){	
				parameterValue = parameterItems.get(i).substring(endIndex + 3);
				timer = Integer.parseInt(parameterValue);				
			}
		}
		
		return timer;
	}	
	
	//lay lua chon thoi gian
	public static String getDate(){
		
		String parameterName= "";
		String parameterValue = "";
		if(parameterItems != null)
		for(int i = 0; i < parameterItems.size(); i++){
			//tim vi tri cua chuoi "###" 
			int endIndex = parameterItems.get(i).indexOf("###");
			
			parameterName = parameterItems.get(i).substring(0, endIndex);
			if(parameterName.equals(DBSAApplicationConst.DATEBEGIN)){	
				parameterValue = parameterItems.get(i).substring(endIndex + 3);						
			}
		}
		
		return parameterValue;
	}
	
	//lay trang thai acm digital library checkbox
	public static boolean getAcmDLCheckboxStatus(){
		
		boolean acmDl = false;
		
		String parameterName= "";
		String parameterValue = "";
		if(parameterItems != null){
			for(int i = 0; i < parameterItems.size(); i++){
				//tim vi tri cua chuoi "###" 
				int endIndex = parameterItems.get(i).indexOf("###");
				
				parameterName = parameterItems.get(i).substring(0, endIndex);
								
				if(parameterName.equals(DBSAApplicationConst.ACM)){
					parameterValue = parameterItems.get(i).substring(endIndex + 3);
					if(parameterValue.equals(DBSAApplicationConst.TRUE))
						acmDl = true;
					else
						acmDl = false;
				}
			}
		}
		
		return acmDl;
	}
	
	//lay trang thai citeseer digital library checkbox
	public static boolean getCiteseerDLCheckboxStatus(){
		
		boolean citeseerDl = false;
		
		String parameterName= "";
		String parameterValue = "";
		if(parameterItems != null){
			for(int i = 0; i < parameterItems.size(); i++){
				//tim vi tri cua chuoi "###" 
				int endIndex = parameterItems.get(i).indexOf("###");
				
				parameterName = parameterItems.get(i).substring(0,endIndex);
				
				if(parameterName.equals(DBSAApplicationConst.CITESEER)){
					parameterValue = parameterItems.get(i).substring(endIndex + 3);
					
					if(parameterValue.equals(DBSAApplicationConst.TRUE))
						citeseerDl = true;
					else
						citeseerDl = false;
				}
			}
		}
		
		return citeseerDl;
	}
	
	//lay trang thai ieee digital library checkbox
	public static boolean getIeeeDLCheckboxStatus(){
		
		boolean ieeeDL = false;
		
		String parameterName= "";
		String parameterValue = "";
		if(parameterItems != null){
			for(int i = 0; i < parameterItems.size(); i++){
				//tim vi tri cua chuoi "###" 
				int endIndex = parameterItems.get(i).indexOf("###");
				
				parameterName = parameterItems.get(i).substring(0, endIndex);
								
				if(parameterName.equals(DBSAApplicationConst.IEEE)){	
					parameterValue = parameterItems.get(i).substring(endIndex + 3);
					if(parameterValue.equals(DBSAApplicationConst.TRUE)){						
						ieeeDL = true;						
					}					
				}
			}
		}
		
		return ieeeDL;
	}
	
	//xet trang thai digital library checkbox
	public static void setDLCheckboxStatus(int timer, boolean acmDl, boolean citeseerDl, boolean ieeeDL){
		String temp = "";
		
		temp += DBSAApplicationConst.TIMER + "###" + timer + "\n";
		temp += DBSAApplicationConst.DATEBEGIN + "###" + getDateNow() + "\n";
		temp += DBSAApplicationConst.ACM + "###" + acmDl + "\n";
		temp += DBSAApplicationConst.CITESEER + "###" + citeseerDl + "\n";
		temp += DBSAApplicationConst.IEEE + "###" + ieeeDL + "\n";				
		
		MyFileWriter.writeToFile(DBSAApplicationConst.AUTO_FETCHER_PARAMETERS_LIST_LINK, temp);
		
	}
	
	public static String getDateNow() {
		String DATE_FORMAT_NOW = "yyyyMMdd";
		
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    
	    String date = sdf.format(cal.getTime());
	    
	    return date;
	  }

	public static void checkToFetch(){		
		
		loadParameterFile();
		
		String dateNow = DBSAConfigAutoFetch.getDateNow();
		String dateInFile = DBSAConfigAutoFetch.getDate();
		
		int dayNow = Integer.parseInt(dateNow.substring(6));
		int dayInFile = Integer.parseInt(dateInFile.substring(6));
		
		int monthNow = Integer.parseInt(dateNow.substring(4, 6));
		int monthInFile = Integer.parseInt(dateInFile.substring(4, 6));
		
		int yearNow = Integer.parseInt(dateNow.substring(0, 4));
		int yearInFile = Integer.parseInt(dateInFile.substring(0, 4));
		
		boolean year = false, month = false;
		
		System.out.println( dayNow + " - " + monthNow + " - " + yearNow );
		System.out.println( dayInFile + " - " + monthInFile + " - " + yearInFile );
		
		if(yearNow - yearInFile > 0){
			year = true;
		}
		
		if(monthNow - monthInFile > 0){
			month = true;
		}
		
		int dayNumber = dayNow - dayInFile;
		
		if(getTimerSelected() > 0 && getTimerSelected() < 10){
			if(year == true || month == true){
				pendMessageAutoFetch();
			}

			if(getTimerSelected() == 1){
				if(dayNumber >= 1){
					pendMessageAutoFetch();			
				}
			}else if(getTimerSelected() == 2){
				if(dayNumber >= 2){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 3){
				if(dayNumber >= 3){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 4){
				if(dayNumber >= 4){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 5){
				if(dayNumber >= 5){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 6){
				if(dayNumber >= 6){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 7){
				if(dayNumber >= 7){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 8){
				if(dayNumber >= 14){
					pendMessageAutoFetch();
				}
			}else if(getTimerSelected() == 9){
				if(dayNumber >= 21){
					pendMessageAutoFetch();
				}
			}
		}else if(getTimerSelected() >= 10){
			int monthTemp = (monthNow + 12) - monthInFile;
			if(year == true){
				if(getTimerSelected() == 10){
					if(monthTemp >= 2){
						pendMessageAutoFetch();
					}else if(monthTemp >= 1 && dayNumber >= 0){
						pendMessageAutoFetch();
					}
				}else if(getTimerSelected() == 11){
					if(monthTemp >= 3){
						pendMessageAutoFetch();
					}else if(monthTemp >= 2 && dayNumber >= 0){
						pendMessageAutoFetch();
					}
				}if(getTimerSelected() == 12){
					if(monthTemp >= 4){
						pendMessageAutoFetch();
					}else if(monthTemp >= 3 && dayNumber >= 0){
						pendMessageAutoFetch();
					}
				}
			}else{			
				if(getTimerSelected() == 10){
					if(monthNow - monthInFile >= 2){
						pendMessageAutoFetch();			
					}else if(monthNow - monthInFile >= 1 && dayNumber >= 0){					
						pendMessageAutoFetch();
					}
				}else if(getTimerSelected() == 11){
					if(monthNow - monthInFile >= 3){
						pendMessageAutoFetch();			
					}else if(monthNow - monthInFile >= 2 && dayNumber >= 0){					
						pendMessageAutoFetch();
					}
				}else if(getTimerSelected() == 12){
					if(monthNow - monthInFile >= 4){
						pendMessageAutoFetch();			
					}else if(monthNow - monthInFile >= 3 && dayNumber >= 0){					
						pendMessageAutoFetch();
					}
				}	
			}
		}				
	}
	/***
	 * 
	 * @return
	 */
	public static boolean pendMessageAutoFetch(){
		boolean accept = false;
		int n = JOptionPane.showConfirmDialog(
	    DBSAApplication.dbsaJFrame, "He thong tu dong thu thap da duoc khoi dong. \nBan co muon thu thap ngay bay gio ko?",
	    "An Question", JOptionPane.YES_NO_OPTION);
	
		if(n == JOptionPane.YES_OPTION){
			accept = true;
			autoFetchResult();
		}else if(n == JOptionPane.NO_OPTION){
			accept = false;
		}
		return accept;
	}
	
	/***
	 * 
	 */
	public static ArrayList<DBSAPublication> autoFetchResult(){
		
		ArrayList<DBSAPublication> resultList = new ArrayList<DBSAPublication>();
		
		if(getAcmDLCheckboxStatus() == true){
			ArrayList<DBSAPublication> temp = new ArrayList<DBSAPublication>();
			temp = ACMFetcherAutoRun.getNewDBSAPubicationNew(getKeywordList());
			
			for(int i = 0; i < temp.size(); i++){
				resultList.add(temp.get(i));
			}
		}
		
		if(getCiteseerDLCheckboxStatus() == true){
			
		}
		
		if(getIeeeDLCheckboxStatus() == true){
			ArrayList<DBSAPublication> temp = new ArrayList<DBSAPublication>();
			temp = IEEEXploreFetcherAutoRun.getNewDBSAPubicationNew(getKeywordList());
			
			if(temp != null){
				for(int i = 0; i < temp.size(); i++){
					resultList.add(temp.get(i));
				}
			}
		}
		
		DBSAApplication.showResultDialog();
		
		return resultList;
	}
	
	//lay danh sach tu khoa
	public static ArrayList<String> getKeywordList(){
		
		ArrayList<String> keywordList = FileLoadder.loadTextFile(DBSAApplicationConst.KEYWORD_LIST_LINK);
		
		return keywordList;
	}
	
	//Lay cac tham so de tu dong thu thap
	public static ArrayList<String> getAutoFetcherParameter(DefaultListModel listModel){
		
		ArrayList<String> parameters = FileLoadder.loadTextFile(DBSAApplicationConst.AUTO_FETCHER_PARAMETERS_LIST_LINK);
		
		return parameters;
	}

}
