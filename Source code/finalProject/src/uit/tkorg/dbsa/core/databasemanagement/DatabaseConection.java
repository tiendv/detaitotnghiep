
package uit.tkorg.dbsa.core.databasemanagement;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author TKORG-PC1
 *
 */
public class DatabaseConection {
	
	public static Connection getDatabaseConection() {
		  Connection con = null;
		    try {
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      con = DriverManager.getConnection("jdbc:mysql:///dbsa",
		        "root", "root");
		
		    } catch(Exception e) {
		      System.err.println("Exception: " + e.getMessage());
		    } 
		    return con;
		   
		    
	}
}
