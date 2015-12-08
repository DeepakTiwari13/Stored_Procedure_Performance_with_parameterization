package Utils;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import Connection_DB.Connection_Parameter;

public class DBFunction extends Connection_Parameter {
	
	/* This class is responsible for fetching various details from database like Database Version,
	 * Number of connections per database,Number of databases, Database list
	*/

	Logger App_log = Logger.getLogger("DBFunction");
	// Object of log4j api has been created
	
	/* Function "Getdbversion" is a boolean function
	 * Function "Getdbversion" returns the SQL server version in case of true else it returns false 
	 * 
	 */

	public boolean Getdbversion() throws SQLException {

		try {
			String Dbversion = null;
			App_log.info("Fetching server details .....");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT @@VERSION AS Version");
			while (rs.next()) {
				Dbversion = rs.getString("Version");
				App_log.info("Printing SQL Server version info--" + Dbversion);
			}
			return true;
		} catch (Throwable t) {
			App_log.info("Failed to get Dbversion" + t.fillInStackTrace());
		}
		return false;
	}
	
	/* Function "Getnumberofconnectionsperdb" is a boolean function
	 * Function "Getnumberofconnectionsperdb" return number of connection per database in case of true else 
	 * it returns false
	 */

	public boolean Getnumberofconnectionsperdb() throws SQLException {

		try {
			App_log.info("Fetching number of connections per database details .....");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT DB_NAME(dbid) as DBName" + ","
					+ "COUNT(dbid) as NumberOfConnections" + ","
					+ "loginame as LoginName " + "FROM " + "sys" + "."
					+ "sysprocesses " + "WHERE " + "dbid > 0" + "GROUP BY dbid"
					+ "," + "loginame");

			while (rs.next()) {
				String Databasename = rs.getString("DBName");
				String noofconnections = rs.getString("NumberOfConnections");
				String Loginname = rs.getString("LoginName");
				App_log.info("DB---" + Databasename
						+ "  Numberofconnections---" + noofconnections
						+ "  Loginname---" + Loginname);
			}
			return true;
		} catch (Throwable t) {
			App_log.info("Failed to get number of connections per database"
					+ t.fillInStackTrace());
		}
		return false;
	}
	
	/* Function "Getnumberofdatabases" is a boolean function
	 * Function "Getnumberofdatabases" return number of count of database in case of true else 
	 * it returns false
	 */

	public boolean Getnumberofdatabases() throws SQLException {

		try {
			String Totaldbs = null;
			App_log.info("Fetching number of databases.....");
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select  COUNT(*) AS Totaldb from sys.databases");
			while (rs.next()) {
				Totaldbs = rs.getString("Totaldb");
				App_log.info("Printing number of database info--" + Totaldbs);
			}
			return true;
		} catch (Throwable t) {
			App_log.info("Failed to get number of databases"
					+ t.fillInStackTrace());
		}
		return false;

	}
	
	/* Function "Getdblist" is a boolean function
	 * Function "Getdblist" return list of database in case of true else 
	 * it returns false
	 */

	public boolean Getdblist() throws SQLException {

		try {

			String Dbpresent = null;
			App_log.info("Fetching databases list.....");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select name AS DbName from Sys.Databases");
			while (rs.next()) {
				Dbpresent = rs.getString("DbName");
				App_log.info("Printing database list info--" + Dbpresent);
			}
			return true;
		} catch (Throwable t) {
			App_log.info("Failed to get Database list" + t.fillInStackTrace());
		}
		return false;

	}
}
