package uit.tkorg.dbsa.gui.main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;
import uit.tkorg.dbsa.properties.files.FileLoadder;

public class DBSAFetcherPattern {

	private static ArrayList<String> patternNameList = new ArrayList<String>();
	private String patternName;
	private String patternValue;
	private String patternDesciption;
	
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
	/*
	 * ham lay danh sach cac ten pattern cua cac thu vien so
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getPatternName(String digitalLibrary){
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
	
	
	/*
	 * ham lay phan chu thich cua moi pattern
	 */
	public String getPatternDesciption(String patternName){
				
		for(int i = 0; i < patternNameList.size(); i++){
			if(patternNameList.get(i).substring(0, patternName.length()).equals(patternName)){
				
				//tim vi tri cua chuoi "%%%" 
				int endIndex = patternNameList.get(i).indexOf("%%%");

				//lay noi dung gia tri cua mot pattern
				setPatternDesciption(patternNameList.get(i).substring(endIndex + 3));	
			}
		}
				
		return getPatternDesciption();
	}
	
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
	
	public void setPatternDesciption(String mPatternDescription){
		patternDesciption  = mPatternDescription;
	}
	
	public String getPatternDesciption(){
		return patternDesciption;
	}
}
