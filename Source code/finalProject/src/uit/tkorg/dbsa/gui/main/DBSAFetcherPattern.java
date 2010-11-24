package uit.tkorg.dbsa.gui.main;

import java.util.ArrayList;

import uit.tkorg.dbsa.properties.files.FileLoadder;

public class DBSAFetcherPattern {

	private static ArrayList<String> patternNameList = new ArrayList<String>();
	
	public DBSAFetcherPattern(){
		
		patternNameList = FileLoadder.loadTextFile("src\\uit\\tkorg\\dbsa\\properties\\files\\DBSA_Define_Pattern");
		
	}
	
	public String getPattern(String patternName){
		
		String patternContent = null;
		
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, patternName.length()).equals(patternName)){
				
				patternContent = patternNameList.get(i).substring(patternName.length() + 1);
				System.out.println(patternContent + patternContent.length());
				
			}				
		}

		return patternContent;
	}
}
