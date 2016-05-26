package Procedure_Performance_Test;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.DBFunction;
import Connection_DB.Connection_Parameter;
import Filereader.Propertyfilereader;
import Filereader.Xls_Reader;

public class SSP_Demo_System_Test extends Connection_Parameter {
	
	/* This class is responsible for running the basic system test */

	SoftAssert Reportfailure = new SoftAssert();
	// Object of soft assert has been created
	static Xls_Reader datatable = null;
	Logger App_log = Logger.getLogger("SP_Demo_System_Test");
	// Object of log4j api has been created
	
	
	/* Function "Activitybeforetest" is responsible for making the connection with database */

	@BeforeTest
	public void Activitybeforetest() throws SQLException, IOException {

		App_log.info("Running before test activities");
		App_log.info("Initialising test parameters");
		App_log.info("Loading property file before test");
		Propertyfilereader pfr = new Propertyfilereader();
		pfr.Loadpropertyfile();
		App_log.info("Loading database values");
		String servername = Propertyfilereader.loadfile
				.getProperty("servernamevalue");
		String username = Propertyfilereader.loadfile
				.getProperty("usernamedata");
		String password = Propertyfilereader.loadfile.getProperty("password");
		String databasetoconnect = Propertyfilereader.loadfile
				.getProperty("dbtoconnect");
		Connection_Parameter cp = new Connection_Parameter();
		cp.Createconnectionwithdatabase(servername, databasetoconnect,
				username, password);
	}

	/* Function "Basic_DB_Test" loads the various parameters from RuntimeVariable.xlsx file like which database to
	 * connect and runmode for test
	 * Function "Basic_DB_Test" checks for various parameters mentioned below
	 * 1. Database version
	 * 2. Number of connection per database
	 * 3. Number of databases
	 * 4. Database list
	 */
	@Test(invocationCount = 1, threadPoolSize = 1, timeOut = 10000 ,priority = 1, enabled = true)
	public void Basic_DB_Test() throws SQLException {

		App_log.info("Loading data table");
		datatable = new Xls_Reader(System.getProperty("user.dir")
				+ "\\src\\Datatable\\RuntimeVariable.xlsx");

		if (datatable.getCellData("Datatable", "Run mode", 2).equals(
				"N"))
			throw new SkipException(
					"Skipping test because of flag is set to N [Test is set to be skipped]");

		App_log.info("Running Basic_DB_Test");
		DBFunction dbf = new DBFunction();
		Reportfailure.assertEquals(dbf.Getdbversion(), true,
				"Basic_DB_Test_Get DB version test failed");
		Reportfailure.assertEquals(dbf.Getnumberofconnectionsperdb(), true,
				"Basic_DB_Test_Get number of connections per DB test failed");
		Reportfailure.assertEquals(dbf.Getnumberofdatabases(), true,
				"Basic_DB_Test_Get number of databases test failed");
		Reportfailure.assertEquals(dbf.Getdblist(), true,
				"Basic_DB_Test_Get DB list test failed");
		Reportfailure.assertAll();
	}

	/* Function "Activityaftertest" is responsible for closing the connection with database */
	@AfterTest
	public void Activityaftertest() throws IOException, SQLException {
		App_log.info("Running after test activities");
		Connection_Parameter cp = new Connection_Parameter();
		cp.Closeconnectionwithdatabase();
		App_log.info("Running after test object cleanup activity");
		Propertyfilereader.loadfile = null;
		Propertyfilereader.Fis = null;
		datatable = null;
		App_log.info(" <<<<< Success!! >>>>>");
	}
}
