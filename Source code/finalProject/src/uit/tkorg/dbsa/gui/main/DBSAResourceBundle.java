package uit.tkorg.dbsa.gui.main;

/**
*
* @author Nguyen Phuoc Cuong
*/

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import uit.tkorg.dbsa.properties.files.DBSAApplicationConst;

public class DBSAResourceBundle {

	public static ResourceBundle res = null;
	public static ResourceBundle swingRes = null;
	private DBSAResourceBundle(){
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
				res = ResourceBundle.getBundle(DBSAApplicationConst.EN_RESOURCE_LINK);
			}else{
				res = ResourceBundle.getBundle(DBSAApplicationConst.VN_RESOURCE_LINK);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
	
	/*
	 * 
	 */
	public static ResourceBundle getSwingInstance(){
		if(swingRes == null){
			try{
				
				swingRes =  initSwingResources();
			}catch(MissingResourceException ex){
				ex.printStackTrace();
				System.exit(0);
			}
		}
		
		return swingRes;
	}
	
	public static ResourceBundle initSwingResources(){
		try{
			
				swingRes = ResourceBundle.getBundle(DBSAApplicationConst.SWING_RESOURCE_LINK);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return swingRes;
	}
}
