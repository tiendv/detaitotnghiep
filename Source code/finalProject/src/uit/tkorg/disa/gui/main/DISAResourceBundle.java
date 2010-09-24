package uit.tkorg.disa.gui.main;

/**
*
* @author Nguyen Phuoc Cuong
*/

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DISAResourceBundle {

	public static ResourceBundle res = null;
	
	private DISAResourceBundle(){
	}
	
	public static ResourceBundle getInstance(){
		if(res == null){
			try{
				
				res =  initResources();
			}catch(MissingResourceException ex){
				ex.printStackTrace();
				System.exit(0);
			}
		}
		
		return res;
	}
	
	public static ResourceBundle initResources(){
		try{
			if(Locale.getDefault().equals(Locale.US)){
				res = ResourceBundle.getBundle("uit/tkorg/disa/properties/files/DISA_Resources_EN");
			}else{
				res = ResourceBundle.getBundle("uit/tkorg/disa/properties/files/DISA_Resources_VN");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
}
