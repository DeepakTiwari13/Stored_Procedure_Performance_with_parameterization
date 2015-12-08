package Utils;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import Connection_DB.Connection_Parameter;

public class Getprocedurestat extends Connection_Parameter {
	
	/* This class is responsible for getting the performance statistics for supplied stored procedure
	 * 
	 */

	SoftAssert Reportfailure = new SoftAssert();
	// Object of Soft assert has been created to report multiple failures
	Logger App_log = Logger.getLogger("Getprocedurestat");
	// Object of log4j api has been created
	
	/* Function "get_ssp_Statistics" runs the performance statistics for supplied stored procedure
	 * Function "get_ssp_Statistics" accepts following parameter 
	 * Param1 StoredProcedureName
	 * 
	 */

	public void get_ssp_Statistics(String ssp_name) throws SQLException,
			IOException, InterruptedException {
		ssp_name = ssp_name.trim();
		
		try {
			App_log.info("Fetching Statistics.....");

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT TOP 1 d.object_id" + ","
					+ " d.database_id" + "," + "OBJECT_NAME" + "("
					+ "object_id" + "," + "database_id" + ")" + " '"
					+ "proc name" + "'" + "," + "d.cached_time" + ","
					+ "d.last_execution_time" + "," + " d.total_elapsed_time"
					+ "," + "(" + "d.total_elapsed_time" + "/"
					+ "d.execution_count" + ")" + "/" + "1000 AS " + "["
					+ "avg_elapsed_time" + "]" + "," + "d.last_elapsed_time"
					+ "/" + "1000 as last_elapsed_time" + ","
					+ "d.execution_count" + "," + "*"
					+ "FROM sys.dm_exec_procedure_stats AS d"
					+ " Where OBJECT_NAME" + "(" + "object_id" + ","
					+ "database_id" + ")" + "=" +"'"+ssp_name+"'"+" Order by d.Last_Execution_Time DESC ");

			while (rs.next()) {
				String Averageelapsedtime = rs.getString("avg_elapsed_time");
				String Lastelapsedtime = rs.getString("last_elapsed_time");
				String LastExecutionTime = rs.getString("last_execution_time");
				String ExecutionCount = rs.getString("execution_count");

				App_log.info("******************************************");

				App_log.info(ssp_name + "--Avgelapsedtime --"
						+ Averageelapsedtime + "--Lastelapsedtime --"
						+ Lastelapsedtime + "--LastExecutionTime--"
						+ LastExecutionTime + "--ExecutionCount --"
						+ ExecutionCount);
                /*
                 *  Object of Filewrite class has been created to write data to files 
                 *  File will be created with names of stored procedure
                 *  Data will be appended in existing files
                 *  File will not be created if exist
                 */
				Filewrite Forwarddata = new Filewrite();
				Forwarddata.Writestatisticstofile(ssp_name, Averageelapsedtime,
						Lastelapsedtime, LastExecutionTime, ExecutionCount);

				Forwarddata.Writeonlytimeandcounttofile(ssp_name,
						Lastelapsedtime, ExecutionCount);

				int lastelapsedtimetakenbyprocedure = Integer
						.parseInt(Lastelapsedtime);
				App_log.info("During test checking Lastelapsedtime "
						+ lastelapsedtimetakenbyprocedure);
				
				ssp_name=null;
				
				//Thread.sleep(2000l);
				if ( lastelapsedtimetakenbyprocedure>10) {
					Reportfailure.assertTrue(false,
							"Last elapsed time is more than 10");
					Reportfailure.assertAll();
				}
			}
		} catch (Throwable t) {
			App_log.info("Error in fetching performance statistics for "
					+ ssp_name);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
			ErrorUtil.addVerificationFailure(t);
		}
	}

}
