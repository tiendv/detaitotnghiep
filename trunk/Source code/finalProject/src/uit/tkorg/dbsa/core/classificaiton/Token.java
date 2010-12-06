package uit.tkorg.dbsa.core.classificaiton;
import java.util.ArrayList;

import uit.tkorg.dbsa.properties.files.FileLoadder;
/**
 * @author tiendv 
 * Class Token of ArrayString
 */
public class Token  {
	public String s;
	public Token() {
		this.s = null;
	}

	public Token(String s) {
		this.s = s;
	}	
	/**
	 * Compare Token with StopWord
	 * @param stopWord : Stop Word
	 * @return : true if String is stop word
	 */
	
	public boolean compareStopWord(String stopWord){
		if(this.s.toLowerCase().trim().equals(stopWord))
			return true;
		return false;
	}
	
	/**
	 * Compare two token
	 * @param tk : token for compare
	 * @return true if two token similar
	 */
	
	public boolean compareTwoToken(Token tk){
		if(this.s.toLowerCase().trim().equals(tk.s.toLowerCase()))
			return true;
		return false;
	}
	
	/**
	 * Remove Stop Words From ArrayList
	 * @param arrt : Arraylist Token
	 * @return  Arraylist Token without Stopword
	 */
	
	public  ArrayList<Token> removeStopwordsFromArrayListToken(final ArrayList<Token> arrt) {
		ArrayList<Token> result = (ArrayList<Token>) arrt.clone();
		ArrayList<String > listStopWord = new FileLoadder().loadTextFile("src\\uit\\tkorg\\dbsa\\properties\\files\\ListStopWords.txt");
		for(int i=0;i<arrt.size();i++) {
			for(int j=0;j<listStopWord.size();j++) {
				if (arrt.get(i).compareStopWord(listStopWord.get(j))) {	
					result.remove(arrt.get(i));					
				}
			}				
		}
		return result;
	}

}
	