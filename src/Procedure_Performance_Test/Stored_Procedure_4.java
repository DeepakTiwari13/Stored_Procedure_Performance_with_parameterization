package Procedure_Performance_Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
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
import Utils.SearchTestCase;
import Connection_DB.Connection_Parameter;
import Filereader.Propertyfilereader;
import Filereader.Xls_Reader;

public class Stored_Procedure_4 extends Connection_Parameter {

	SoftAssert Reportfailure = new SoftAssert();
	// Object of soft assert has been created
	static Xls_Reader datatable = null;
	Logger App_log = Logger.getLogger("Stored_Procedure_4");
	// Object of log4j api has been created
	static HSSFRow row;
	static HSSFCell cell;
	static int i, j, type;
	public static int returnValue;
	public static boolean IsResultSetExist=false;
	
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
		
		SearchTestCase stc = new SearchTestCase();
		if (stc.Checktestcasewithrunmode("Stored_Procedure_4", datatable)) {

			String databasetoconnect = stc.dbtoconnect;

			Connection_Parameter cp = new Connection_Parameter();
			cp.Createconnectionwithdatabase(servername, databasetoconnect,
					username, password);
		} else {
			throw new SkipException(
					"Skipping test case.Please check RuntimeVariable.xlsx under package Datatable ");

		}

	}

	/* Test Stored_Procedure_4_Execution uses runmode from RuntimeVariable.xlsx file
	 * Test Stored_Procedure_4_Execution uses data provider "getdata_US92875" to load the data from xls file
	 * Test Stored_Procedure_4_Execution runs Stored_Procedure_4 stored procedure
	 * Test Stored_Procedure_4_Execution accept following parameters
	 * Param1 CompanyId,Param2 RemoteOfficeId,Param3 FromDate,Param4 ToDate,Param5 FromInvoiceId,Param6 ToInvoiceId 
	 * Param7 PayerId,Param8 BillingPeriodId,Param9 ClientIdList
	 * 
	 * 
	 * 
	 */
	@Test(dataProvider = "getdata_US92875",invocationCount = 1, threadPoolSize = 1, timeOut = 600000, priority = 1, enabled = true)
	public void Stored_Procedure_4_Execution(String CompanyId,String RemoteOfficeId,String FromDate,String ToDate,
			String FromInvoiceId,String ToInvoiceId,String PayerId,String BillingPeriodId,String ClientIdList)
			

	throws SQLException, ParseException, IOException, InterruptedException, SAXException, ParserConfigurationException {

		App_log.info("Calling stored procedure [Stored_Procedure_4]");

		int CompanyIdInt = Integer.parseInt(CompanyId);
		int RemoteOfficeIdInt = Integer.parseInt(RemoteOfficeId);
		int FromInvoiceIdInt = Integer.parseInt(FromInvoiceId);
		int ToInvoiceIdInt = Integer.parseInt(ToInvoiceId);
		int BillingPeriodIdInt = Integer.parseInt(BillingPeriodId);
					
		String US92875 = "{ ?=call Stored_Procedure_4 (?,?,?,?,?,?,?,?,?)}";
	
		Dateconverter dc = new Dateconverter();
		cs = conn.prepareCall(US92875);
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(600);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, CompanyIdInt);
		cs.setInt(3, RemoteOfficeIdInt);
		cs.setDate(4, (Date) dc.Getdate(FromDate));
		cs.setDate(5, (Date) dc.Getdate(ToDate));
		cs.setInt(6, FromInvoiceIdInt);
		cs.setInt(7, ToInvoiceIdInt);
		cs.setString(8, PayerId);
		cs.setInt(9, BillingPeriodIdInt);
		cs.setString(10, ClientIdList);
		
		String preparedSP = "Stored_Procedure_4 "+" "+CompanyIdInt+" "+RemoteOfficeIdInt+" "+FromDate+" "+ToDate+" "+FromInvoiceIdInt+" "+
				ToInvoiceIdInt+" "+PayerId+" "+BillingPeriodIdInt+" "+ClientIdList;
		App_log.info("Printing prepared SP "+preparedSP);
				
		try {
			App_log.info("Executing stored procedure [Stored_Procedure_4]");
			ResultSetBoolean = cs.execute();
			
			App_log.info("******"+" Testing with boolean result set "+"*******"+ResultSetBoolean+"******");
			
			int C = 0;
			while (ResultSetBoolean) {

				App_log.info("****** In side multiple result set checker ******");
				C++;
				App_log.info("*********** Result set count available ********** "
						+ C);
				
				rs = cs.getResultSet();
				while (rs.next()) {
					int row = rs.getRow();
					App_log.info("******* Number of row available ********"
							+ row);
				}
				rs.close();
				ResultSetBoolean = cs.getMoreResults();
				App_log.info("******* Result set boolean ********"+ResultSetBoolean);
			
			}
			if(C!=0){
				IsResultSetExist=true;
				App_log.info("***** Ready to write data in file ******");
				Getprocedurestat.IsResultSetDataAvailable=IsResultSetExist;
			}

		} catch (Throwable t) {
			App_log.info(" Error in SP is [Stored_Procedure_4] "
					+ t.fillInStackTrace());

			ErrorUtil.addVerificationFailure(t);
			Reportfailure.fail("Issue in SP , Please execute it manually in SSMS ");

			/*
			 * Adding errors to basket Errors will appear in report if test is
			 * run using testng.xml
			 */

		} finally {
			returnValue = cs.getInt(1);
			App_log.info("********" + "Return Value " + returnValue
					+ "********");

		}

		Propertyfilereader pfr = new Propertyfilereader();
		pfr.Loadpropertyfile();
		String ExpectedSPExecutionoutput=Propertyfilereader.loadfile.getProperty("SPOutput");
		int SPE = Integer.parseInt(ExpectedSPExecutionoutput);
		Reportfailure.assertEquals(returnValue, SPE);
		
		
		/* Object of "Filewrite" class is created to write the supplied dataset in log file*/
		Filewrite Forwardheaderdata = new Filewrite();
		Forwardheaderdata.Write_US92875_Params_tofile("Stored_Procedure_4",CompanyIdInt,RemoteOfficeIdInt,dc.Getdate(FromDate),
				dc.Getdate(ToDate),FromInvoiceIdInt,ToInvoiceIdInt,PayerId,BillingPeriodIdInt,ClientIdList);

		/* Object of "Getprocedurestat" class is created to write the statistics of this stored procedure*/
		Getprocedurestat gps = new Getprocedurestat();
		Getprocedurestat.SPReturnParam=SendSPReturnValue();
		gps.get_ssp_Statistics("Stored_Procedure_4");
		Reportfailure.assertAll();
		
	}
	
	public int SendSPReturnValue (){
		return returnValue;
	}
	

	/* Function "getdata_US92875" is a dataprovider for test Stored_Procedure_4_Execution
	 * Function "getdata_US92875" loads the data for test from Stored_Procedure_4_dataprovider.xls file
	 */
	
	@DataProvider
	public Object[][] getdata_US92875() throws IOException {
		App_log.info("Inside data provider");
		App_log.info("In side xls reader utility");
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\Data_Providers\\Stored_Procedure_4_dataprovider.xls"));
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
		IsResultSetExist=false;
		Getprocedurestat.IsResultSetDataAvailable=false;
		datatable = null;
		//Integer a = null;
		//returnValue = a.intValue();
		App_log.info(" <<<<< Success!! >>>>>");

	}

}
