package Procedure_Performance_Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

import Utils.Dateconverter;
import Utils.ErrorUtil;
import Utils.Filewrite;
import Utils.Getprocedurestat;
import Connection_DB.Connection_Parameter;
import Filereader.Propertyfilereader;
import Filereader.Xls_Reader;

public class Stored_Procedure_2 extends
		Connection_Parameter {

	SoftAssert Reportfailure = new SoftAssert();
	// Object of soft assert has been created
	static Xls_Reader datatable = null;
	Logger App_log = Logger
			.getLogger("Stored_Procedure_2");
	// Object of log4j api has been created
	static HSSFRow row;
	static HSSFCell cell;
	static int i, j, type;
	
	/* Function "Activitybeforetest" is responsible for making the connection with database 
	 * Function Activitybeforetest also loads parameter from "RuntimeVariable.xlsx" like which database to connect
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
		
		if (datatable.getCellData("Datatable", "Stored procedure", 4)
				.trim().equals("Stored_Procedure_2")) {
			String databasetoconnect = datatable.getCellData(
					"Datatable", "Database name",4);

			Connection_Parameter cp = new Connection_Parameter();
			cp.Createconnectionwithdatabase(servername, databasetoconnect,
					username, password);
		} else {
			throw new SkipException(
					"Skipping test because wrong stored procedure is defined [Test is set to be skipped]");
		}

	}

	/* Test Stored_Procedure_2_Execution uses runmode from RuntimeVariable.xlsx file
	 * Test Stored_Procedure_2_Execution uses data provider "getdata_US80738" to load the data from xls file
	 * Test Stored_Procedure_2_Execution uses invocationCount to run test multiple times/specified times
	 * Test Stored_Procedure_2_Execution runs Stored_Procedure_2 stored procedure
	 * Test Stored_Procedure_2_Execution accept following parameters
	 * Param1 BillingStartDate,Param2 BillingEndDate,Param3 BillingPeriod,Param4 CompanyID
	 * Param5 RemoteOfficeId,Param6 SelectedCaremanagers,Param7 UserCode,Param8 SelectedClients
	 * Param9 SelectedCallTypes,Param10 SelectedClientsForBillingLineItem,Param11 SelectedCallTypesForBillingLineItem
	 */
	@Test(dataProvider = "getdata_US80738", invocationCount = 5, threadPoolSize = 1, timeOut = 60000, priority = 1, enabled = true)
	public void Stored_Procedure_2_Execution(
			String BillingStartDate, String BillingEndDate, String BillingPeriod,
			String CompanyID, String RemoteOfficeId, String SelectedCaremanagers,
			String UserCode, String SelectedClients,String SelectedCallTypes,String SelectedClientsForBillingLineItem,String SelectedCallTypesForBillingLineItem)
			

	throws SQLException, ParseException, IOException, InterruptedException, SAXException, ParserConfigurationException {

		if (datatable.getCellData("Datatable", "Run mode",4).equals(
				"N"))
			throw new SkipException(
					"Skipping test because of flag is set to N [Test is set to be skipped]");

		App_log.info("Calling stored procedure [Stored_Procedure_2]");

		int BillingPeriodInt = Integer.parseInt(BillingPeriod);
		int CompanyIDInt = Integer.parseInt(CompanyID);
		int RemoteOfficeIdInt = Integer.parseInt(RemoteOfficeId);
		
		
		// Need to load SP from xlsx file,
		// String originalSP= (datatable.getCellData("Datatable",
		// "Run mode", 4).equals("N"));
		String US80738 = "{call Stored_Procedure_2 (?,?,?,?,?,?,?,?,?,?,?)}";

		
				
		Dateconverter dc = new Dateconverter();
		cs = conn.prepareCall(US80738);
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(60);
		cs.setDate(1, (Date) dc.Getdate(BillingStartDate));
		cs.setDate(2,(Date) dc.Getdate(BillingEndDate)); 
		cs.setInt(3,BillingPeriodInt); 
		cs.setInt(4, CompanyIDInt);
		cs.setInt(5, RemoteOfficeIdInt);
		cs.setString(6, SelectedCaremanagers);
		cs.setString(7, UserCode);
		cs.setString(8,SelectedClients);
		cs.setString(9,SelectedCallTypes);
		cs.setString(10,"");
		cs.setString(11,"");
		
		try {
			App_log.info("Executing stored procedure [Stored_Procedure_2]");
			rs = cs.executeQuery();
		} catch (Throwable t) {
			App_log.info("No result set for [Stored_Procedure_2] "
					+ t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}
		
		/* Object of "Filewrite" class is created to write the supplied dataset in log file*/
		Filewrite Forwardheaderdata = new Filewrite();
		Forwardheaderdata.Write_US80738_Params_tofile(datatable.getCellData(
				"Datatable", "Stored procedure",4).trim(), (Date) dc.Getdate(BillingStartDate),(Date)dc.Getdate(BillingEndDate),
				BillingPeriodInt,CompanyIDInt,RemoteOfficeIdInt,SelectedCaremanagers,UserCode,SelectedClients,SelectedCallTypes);

		/* Object of "Getprocedurestat" class is created to write the statistics of this stored procedure*/
		Getprocedurestat gps = new Getprocedurestat();
		gps.get_ssp_Statistics(datatable.getCellData(
				"Datatable", "Stored procedure",4));
		
		
	}

	/* Function "getdata_US80738" is a dataprovider for test Stored_Procedure_2_Execution
	 * Function "getdata_US80738" loads the data for test from Stored_Procedure_2_dataprovider.xls file
	 */
	
	@DataProvider
	public Object[][] getdata_US80738() throws IOException {
		App_log.info("Inside data provider");
		App_log.info("In side xls reader utility");
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\Data_Providers\\ssp_Stored_Procedure_with_inputs_2_dataprovider.xls"));
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
