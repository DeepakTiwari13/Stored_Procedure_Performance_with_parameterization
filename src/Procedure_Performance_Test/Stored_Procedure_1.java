package Procedure_Performance_Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.ErrorUtil;
import Utils.Filewrite;
import Utils.Getprocedurestat;
import Connection_DB.Connection_Parameter;
import Filereader.Propertyfilereader;
import Filereader.Xls_Reader;

public class Stored_Procedure_1 extends Connection_Parameter {

	SoftAssert Reportfailure = new SoftAssert();
	// Object of soft assert has been created
	static Xls_Reader datatable = null;
	Logger App_log = Logger.getLogger("Stored_Procedure_1");
	// Object of log4j api has been created
	static HSSFRow row;
	static HSSFCell cell;
	static int i, j, type;

	/*
	 * Function "Activitybeforetest" is responsible for making the connection
	 * with database Function Activitybeforetest also loads parameter from
	 * "RuntimeVariable.xlsx" like which database to connect
	 */

	@BeforeTest
	public void Activitybeforetest() throws SQLException, IOException {

		App_log.info("Running before test activities");
		App_log.info("Initialising test parameters");
		App_log.info("Loading property file before test");
		Propertyfilereader pfr = new Propertyfilereader();
		pfr.Loadpropertyfile();
		App_log.info("Loading data table");
		datatable = new Xls_Reader(System.getProperty("user.dir")
				+ "\\src\\Datatable\\RuntimeVariable.xlsx");
		App_log.info("Loading database values");
		String servername = Propertyfilereader.loadfile
				.getProperty("servernamevalue");
		String username = Propertyfilereader.loadfile
				.getProperty("usernamedata");
		String password = Propertyfilereader.loadfile.getProperty("password");
		
	        SearchTestCase stc = new SearchTestCase();
		if (stc.Checktestcasewithrunmode("Stored_Procedure_1", datatable)) {

			String databasetoconnect = stc.dbtoconnect;

			Connection_Parameter cp = new Connection_Parameter();
			cp.Createconnectionwithdatabase(servername, databasetoconnect,
					username, password);
		} else {
			throw new SkipException(
					"Skipping test case.Please check RuntimeVariable.xlsx under package Datatable ");

		}
	
	}

	/*
	 * Test Stored_Procedure_1_Execution uses runmode from
	 * RuntimeVariable.xlsx file Test
	 * Stored_Procedure_1_Execution uses data provider
	 * "getdata_US80737" to load the data from xls file Test
	 * Stored_Procedure_1_Execution uses invocationCount to
	 * run test multiple times/specified times Test
	 * Stored_Procedure_1_Execution runs
	 * Stored_Procedure_1 stored procedure Test
	 * Stored_Procedure_1_Execution accept following
	 * parameters 
	 * Param1 CompanyId,Param2 StartDate,Param3 EndDate,Param4 ClientId,Param5 CaregiverId,Param6 RemoteOfficeId,Param7 CaseManagerId
	 * Param8 xmldata1
	 * Clientpayer
	 */

	@Test(dataProvider = "getdata_US80737", invocationCount = 5, threadPoolSize = 1, timeOut = 60000, priority = 1, enabled = true)
	public void Stored_Procedure_1_Execution(
			String CompanyID, String RemoteOfficeId, String InvoiceIds,
			String Clientpayer) throws SQLException, ParseException,
			IOException, InterruptedException {

		String GrossCharge = null, AmountToPay = null, TotalPayment = null, TotalFixed = null;

		if (datatable.getCellData("Datatable", "Run mode", 3).equals(
				"N"))
			throw new SkipException(
					"Skipping test because of flag is set to N [Test is set to be skipped]");

		App_log.info("Calling stored procedure [Stored_Procedure_1]");

		int CompanyIDInt = Integer.parseInt(CompanyID);
		int RemoteOfficeIdInt = Integer.parseInt(RemoteOfficeId);
		int ClientpayerInt = Integer.parseInt(Clientpayer);

		// Need to load SP from xlsx file
		// String originalSP= (datatable.getCellData("datatable",
		// "Run mode", 3).equals("N"));
		String US80737 = "{call Stored_Procedure_1 (?,?,?,?,?,?,?,?)}";

		cs = conn.prepareCall(US80737);
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(60);
		cs.setInt(1, CompanyIDInt);
		cs.setInt(2, RemoteOfficeIdInt);
		cs.setString(3, InvoiceIds);
		cs.setInt(4, ClientpayerInt);
		cs.registerOutParameter(5, java.sql.Types.DECIMAL);
		cs.registerOutParameter(6, java.sql.Types.DECIMAL);
		cs.registerOutParameter(7, java.sql.Types.DECIMAL);
		cs.registerOutParameter(8, java.sql.Types.DECIMAL);

		try {
			App_log.info("Executing stored procedure [Stored_Procedure_1]");
			rs = cs.executeQuery();

		} catch (Throwable t) {

			App_log.info("No result set for [Stored_Procedure_1] "
					+ t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket Errors will appear in report if test is
			 * run using testng.xml
			 */
		} finally {
			GrossCharge = cs.getString(5);
			App_log.info("GrossCharge " + GrossCharge+" SP_OUTPUT");
			AmountToPay = cs.getString(6);
			App_log.info("AmountToPay " + AmountToPay +" SP_OUTPUT");
			TotalPayment = cs.getString(7);
			App_log.info("TotalPayment " + TotalPayment +" SP_OUTPUT");
			TotalFixed = cs.getString(8);
			App_log.info("TotalFixed " + TotalFixed +" SP_OUTPUT");

		}

		/*
		 * Object of "Filewrite" class is created to write the supplied dataset
		 * in log file
		 */
		Filewrite Forwardheaderdata = new Filewrite();
		Forwardheaderdata.Write_US80737_Params_tofile(
				datatable.getCellData("Datatable", "Stored procedure",
						3).trim(), CompanyIDInt, RemoteOfficeIdInt,
				InvoiceIds, ClientpayerInt, GrossCharge, AmountToPay,
				TotalPayment, TotalFixed);

		/*
		 * Object of "Getprocedurestat" class is created to write the statistics
		 * of this stored procedure
		 */
		Getprocedurestat gps = new Getprocedurestat();
		gps.get_ssp_Statistics(datatable.getCellData("Datatable",
				"Stored procedure",3));

		/*
		 * Object of "Filewrite" class is created to write the output of stored
		 * procedure in log file
		 */
		Forwardheaderdata.Write_SP_US80737_output_paramater_to_file(datatable
				.getCellData("Datatable", "Stored procedure", 3),
				GrossCharge, AmountToPay, TotalPayment, TotalFixed);
	}

	/*
	 * Function "getdata_US80737" is a dataprovider for test
	 * Stored_Procedure_1_Execution Function
	 * "getdata_US80737" loads the data for test from
	 * Stored_Procedure_1_dataprovider.xls file
	 */
	@DataProvider
	public Object[][] getdata_US80737() throws IOException {
		App_log.info("Inside data provider");
		App_log.info("In side xls reader utility");

		FileInputStream file = new FileInputStream(
				new File(
						System.getProperty("user.dir")
								+ "\\src\\Data_Providers\\ssp_Stored_Procedure_with_inputs_1_dataprovider.xls"));
		App_log.info("Test data provider file is located");
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);
		int numofrows = sheet.getLastRowNum() + 1;
		App_log.info("Printing number of rows " + numofrows);
		int numofcolumns = sheet.getRow(0).getLastCellNum();
		App_log.info("Printing number of column " + numofcolumns);
		Object data[][] = new Object[numofrows - 1][numofcolumns];

		for (i = 1; i < numofrows; i++) {
			row = sheet.getRow(i);
			for (j = 0; j < numofcolumns; j++) {
				cell = row.getCell(j);

				int type = cell.getCellType();

				if (type == HSSFCell.CELL_TYPE_STRING) {
					App_log.info("[" + cell.getRowIndex() + ", "
							+ cell.getColumnIndex() + "] = STRING; Value = "
							+ cell.getRichStringCellValue().toString());
					data[i - 1][j] = cell.getRichStringCellValue().toString()
							.trim();
					App_log.info(data[i - 1][j]);
				} else if (type == HSSFCell.CELL_TYPE_NUMERIC) {
					App_log.info("[" + cell.getRowIndex() + ", "
							+ cell.getColumnIndex() + "] = NUMERIC; Value = "
							+ String.valueOf(cell.getNumericCellValue()));
					data[i - 1][j] = String.valueOf(new DataFormatter()
							.formatCellValue(cell)); // Passing actual cell
														// value to data
														// provider after
														// formatting
					App_log.info(data[i - 1][j]);
				} else if (type == HSSFCell.CELL_TYPE_BOOLEAN) {
					App_log.info("[" + cell.getRowIndex() + ", "
							+ cell.getColumnIndex() + "] = BOOLEAN; Value = "
							+ cell.getBooleanCellValue());
				} else if (type == HSSFCell.CELL_TYPE_BLANK) {
					App_log.info("[" + cell.getRowIndex() + ", "
							+ cell.getColumnIndex() + "] = BLANK CELL");
				} else if (DateUtil.isCellDateFormatted(cell)) {
					data[i - 1][j] = cell.getDateCellValue();
				}
			}
		}
		return data;

	}

	/*
	 * Function "Activityaftertest" is responsible for closing the connection
	 * with database
	 */

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
