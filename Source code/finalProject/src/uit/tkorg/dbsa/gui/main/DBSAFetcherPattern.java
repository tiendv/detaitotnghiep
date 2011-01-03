package uit.tkorg.dbsa.gui.main;

import java.util.ArrayList;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.FileLoadder;
import uit.tkorg.dbsa.properties.files.MyFileWriter;

public class DBSAFetcherPattern {

	private static ArrayList<String> patternNameList = new ArrayList<String>();
	private String patternName;
	private String patternValue;
	private String patternDescription;
	
	/*
	 * mot doi tuong cua PatternNameList co dang nhu sau ACMEndUrl###&coll=Portal&short=0%%%
	 * cac ki tu "###" va "%%%" dung de nhan biet cac thanh phan trong 1 doi tuong tren
	 * phia truoc chuoi "###" la ten cua Pattern
	 * phia sau chuoi "%%%" la chu thich cua pattern
	 * o giua 2 chuoi nay "###" va "%%%" la noi dung cua pattern.
	 */
	public DBSAFetcherPattern(){
		
		patternNameList = FileLoadder.loadTextFile(DBSAApplicationConst.PATTERN_RESOURCE_LINK);	
	}
	
	public String getPattern(String patternName){
		
		String patternContent = null;
		
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, patternName.length()).equals(patternName)){

				//lay vi tri cua chuoi "###"
				int beginIndex = patternNameList.get(i).indexOf("###");
				//lay vi tri cua chuoi "%%%"
				int endIndex = patternNameList.get(i).indexOf("%%%");

				//lay noi dung cua mot pattern
				patternContent = patternNameList.get(i).substring(beginIndex + 3, endIndex);
				
			}			
		}

		return patternContent;
	}
	/***
	 * ham lay danh sach cac ten pattern cua cac thu vien so
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getPatternNameList(String digitalLibrary){
		ArrayList<String> ptName = new ArrayList<String>();;
		
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, digitalLibrary.length()).equals(digitalLibrary)){
				//lay vi tri cua chuoi "###"
				int index = patternNameList.get(i).indexOf("###");
				//lay ten pattern
				ptName.add(patternNameList.get(i).substring(0, index)); 
			}
		}
		
		return ptName;
	}
	
	/***
	 * 
	 * @param number 
	 * @return String patternName
	 */
	
	public String getPatternName(int number){
		String pattName;
		int index = patternNameList.get(number).indexOf("###");
		
		pattName = patternNameList.get(number).substring(0, index);
		
		return pattName;
	}
	
	/***
	 * 
	 * @param pattName
	 * @param pattValue
	 * @param pattDescription
	 */
	
	public void changePatternInfo(String pattName, String pattValue, String pattDescription){
		
		for(int i = 0; i < patternNameList.size(); i++){
			
			if (getPatternName(i).equals(pattName)) {
				patternNameList.set(i, pattName + "###" + pattValue + "%%%" + pattDescription);
				
			}
		}
		
	}

	public void savePatternFile() {
		String temp = "";
		for(int i = 0; i < patternNameList.size(); i++){
			temp += patternNameList.get(i);
			temp += "\n";
			System.out.println(patternNameList.get(i));
		}	
		
		MyFileWriter.writeToFile(DBSAApplicationConst.PATTERN_RESOURCE_LINK, temp);
	}
	
	/*
	 * ham lay gia tri tuong ung cua moi pattern
	 */
	public String getPatternValue(String patternName){
				
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, patternName.length()).equals(patternName)){
				
				//tim vi tri cua chuoi "###" 
				int beginIndex = patternNameList.get(i).indexOf("###");
				//tim vi tri cua chuoi "%%%" 
				int endIndex = patternNameList.get(i).indexOf("%%%");

				//lay noi dung gia tri cua mot pattern
				setPatternValue(patternNameList.get(i).substring(beginIndex + 3, endIndex));
			}
		}
				
		return getPatternValue();
	}
	
	
	/***
	 * ham lay phan chu thich cua moi pattern
	 */
	
	public String getPatternDescription(String patternName){
				
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, patternName.length()).equals(patternName)){
				
				//tim vi tri cua chuoi "%%%" 
				int endIndex = patternNameList.get(i).indexOf("%%%");

				//lay noi dung gia tri cua mot pattern
				setPatternDescription(patternNameList.get(i).substring(endIndex + 3));	
			}
		}
				
		return getPatternDescription();
	}
	
	/***
	 * 
	 */
	
	public void editPattern(String patternName, String patternvalue, String patternDescription){
		
	}
	
	/***
	 * 
	 * @param mPatternName
	 */
	public void setPatternName(String mPatternName){
		patternName = mPatternName;
	}
	
	public String getPatternName(){
		return patternName;
	}
	
	public void setPatternValue(String mPatternValue){
		patternValue = mPatternValue;
	}
	
	public String getPatternValue(){
		return patternValue;
	}
	
	public void setPatternDescription(String mPatternDescription){
		patternDescription  = mPatternDescription;
	}
	
	public String getPatternDescription(){
		return patternDescription;
	}
}
