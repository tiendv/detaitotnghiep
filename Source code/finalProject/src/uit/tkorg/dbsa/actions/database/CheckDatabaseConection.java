/**
 * 
 */
package uit.tkorg.dbsa.actions.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * @author tiendv
 *  To check database connection
 */
public class CheckDatabaseConection {
	
	 public static boolean testConnection() {
		 
		 	String driver = "org.gjt.mm.mysql.Driver";
		    String url = "jdbc:mysql://localhost/dbsa";
		    String username = "root";
		    String password = "root";
		    String query = "Select * from dbsa_sbj";
		    try {
				Class.forName(driver);
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} // load MySQL driver
		    Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    ResultSet rs = null;
		    Statement stmt = null;
		    try {
		      stmt = conn.createStatement();
		      if (stmt == null) {
		        return false;
		      }

		      rs = stmt.executeQuery(query);
		      if (rs == null) {
		        return false;
		      }

		      // connection object is valid: you were able to
		      // connect to the database and return something useful.
		      if (rs.next()) {
		        return true;
		      }

		      // there is no hope any more for the validity
		      // of the Connection object
		      return false;
		    } catch (Exception e) {
		      // something went wrong: connection is bad
		      return false;
		    } finally {
		      // close database resources
		      try {
		    	  if(rs !=null)
		        rs.close();
		        stmt.close();        
		      } catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
		  }
	/* public static void main(String[] args) {
		Boolean test = testConnection();
		if(test == true)
		System.out.printf("ok");
		else
			System.out.printf("die");	
	 }
*/
}
