package Utils;

import org.apache.log4j.Logger;

import Filereader.Xls_Reader;

public class SearchTestCase {
	
	/* This class is responsible for reading the stored procedure from RuntimeVariable.xlsx file with runmode */

	Logger App_log = Logger.getLogger("SearchTestCase");
	// Object of log4j api has been created
	public String dbtoconnect;
	
	/* Function Checktestcasewithrunmode accept the stored procedure name and object of Xls_Reader class
	 * Function Checktestcasewithrunmode checks for the stored procedure with run mode.
	 * If run mode is yes then it reads the database to connect.
	 * 
	 */

	public boolean Checktestcasewithrunmode(String testcase, Xls_Reader xls) {
		App_log.info("Inside Check Test Case With Runmode function ");

		for (int rowNum = 2; rowNum <= xls.getRowCount("Kinnser procedure"); rowNum++) {

			if (xls.getCellData("Kinnser procedure", "Stored procedure", rowNum)
					.equals(testcase)) {

				if (xls.getCellData("Kinnser procedure", "Run mode", rowNum)
						.equals("Y")) {
					
					dbtoconnect =xls.getCellData("Kinnser procedure", "Database name", rowNum);
					
					App_log.info("Run mode is Yes .Test run is approved ");
					return true;

				} else {
					App_log.info("Run mode is No .Test run is declined ");
					return false;
				}
			}

		}
		App_log.info("No test case match is found ");
		return false;
	}
}
