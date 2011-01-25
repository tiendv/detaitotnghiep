package uit.tkorg.dbsa.gui.main;

import java.util.ArrayList;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.FileLoadder;

public class AuthorsNameList {

	private static ArrayList<String> authorNameList = new ArrayList<String>();
	
	public AuthorsNameList(){
		authorNameList = FileLoadder.loadTextFile(DBSAApplicationConst.AUTHOR_NAME_LIST_LINK);	
	}
	
	public static ArrayList<String> getAuthorNameList(){
		return authorNameList;
	}
}
