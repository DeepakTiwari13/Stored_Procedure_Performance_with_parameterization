package Connection_DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/* This class is responsible for making the connection with SQL Server database
 * Each test extends this class to inherit it's properties
*/

public class Connection_Parameter {

	Logger App_log = Logger.getLogger("Connection_Parameter");
	// Object of log4j api has been created

	public static Connection conn;
	public static Statement stmt;
	public static CallableStatement  cs;
	public static  ResultSet rs;
	public static boolean ResultSetBoolean;
	
/*	Function "Createconnectionwithdatabase" is a boolean function
 *  If connection with database is successful then it return true else it returns false
 *  Function "Createconnectionwithdatabase" accepts following parameters
 *  Param1 Servername  example :- *****************\***************
 *  Param2 Databasename example:- master
 *  Param3 Username 	example:- ******************
 *  Param4 Password
 *  Function "Createconnectionwithdatabase" uses SQL server connection mechanism to connect with database
 */

	public boolean Createconnectionwithdatabase(String servername, String DataBaseName,
			String username, String password) throws SQLException

	{
		String url = "jdbc:sqlserver://" + servername + ";"
				+ "databaseName"+"="+DataBaseName+ ";"; // "integratedSecurity=true" for windows authentication
		App_log.info("Printing url constructed===="+ url);
		App_log.info("Printing SQLSERVER selected database to connect===="+ DataBaseName);
		App_log.info("Printing SQLSERVER Name===="+ servername);
		App_log.info("Printing SQLSERVER User Name===="+username);
		App_log.info("Establishing connection with database....");
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			App_log.info("Initialising driver instance ....");
			conn = DriverManager.getConnection(url, username, password);
			App_log.info("Connection established....");

			return true;
		} catch (Throwable t) {
			App_log.info("Error while connecting to db" + t.fillInStackTrace());
			App_log.info("Connection could not be established....");
			conn.close();
		}
		return false;

	}

	/* Function "Closeconnectionwithdatabase" is responsible for closing the connection with database
	 * It checks if statement is not null then close it
	 * It checks if result set is not null then close it
	 * It checks if callable statement is not null then close it
	 * It checks if connection is not null then close it
	 * Function "Closeconnectionwithdatabase" is a boolean function if everything is closed then return true else
	 * return false
	 */
	public boolean Closeconnectionwithdatabase() throws SQLException {

		try {
			if (stmt != null) {

				try {
					stmt.close();
					App_log.info("Closing statement explicitly");
				} catch (Throwable t) {
					App_log.info("Failed in closing statement "
							+ t.fillInStackTrace());
				}
			}

			if (rs != null) {
				try {
					rs.close();
					App_log.info("Closing result set explicitly");
				} catch (Throwable t) {
					App_log.info("Failed in closing result set "
							+ t.fillInStackTrace());
				}

			}

			if (cs != null) {
				try {
					cs.close();
					App_log.info("Closing callable statement explicitly");
				} catch (Throwable t) {
					App_log.info("Failed in closing callable statement "
							+ t.fillInStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
					App_log.info("Closing connection explicitly");
				} catch (Throwable t) {
					App_log.info("Failed in closing connection "
							+ t.fillInStackTrace());
				}
			}
			return true;
		} catch (Throwable t) {
			App_log.info("Failed in function [Closeconnectionwithdatabase].Please check the Name.log ");
		}
		return false;
	}

}
