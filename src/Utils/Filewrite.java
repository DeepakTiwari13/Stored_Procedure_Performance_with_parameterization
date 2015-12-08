package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

public class Filewrite {
	
	/* This class is responsible for generating the log files for each stored procedure
	 * 
	 */

	// Object of log4j api has been created
	Logger App_log = Logger.getLogger("Filewrite");
	File file = null;
	// rename function to procedure parameters
	
	/* Function "Write_US58861_Params_tofile" writes parameters of USUS58861 to a file
	 * Function "Write_US58861_Params_tofile" accept following parameters
	 * Param1 name,Param2 scheduleidInt,Param3 CompanyIDInt,Param4 ClientIdInt,Param5 CaregiverIdInt
	 * Param6 Date,Param7 Date,Param8 Action
	 */
	public void Write_US58861_Params_tofile(String name, int scheduleidInt,
			int CompanyIDInt, int ClientIdInt, int CaregiverIdInt,
			Date startdate, Date enddate, String action) throws IOException {
		App_log.info("Inside data set writer_US58861 utility");

		try {
			file = new File(System.getProperty("user.dir")
					+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
					+ ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty("line.separator"));

			bufferWritter.write("Schedule ID " + scheduleidInt);
			bufferWritter.write("\t");
			bufferWritter.write("Company ID " + CompanyIDInt);
			bufferWritter.write("\t");
			bufferWritter.write("Client Id " + ClientIdInt);
			bufferWritter.write("\t");
			bufferWritter.write("Caregiver Id " + CaregiverIdInt);
			bufferWritter.write("\t");
			bufferWritter.write("Start Date " + startdate);
			bufferWritter.write("\t");
			bufferWritter.write("End Date " + enddate);
			bufferWritter.write("\t");
			bufferWritter.write("Action " + action);
			bufferWritter.close();
			App_log.info("Completed data set writing for _US58861");
		} catch (Throwable t) {
			App_log.info("Error in writing data set output for _US58861 " + t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}
	}
	
	/* Function "Write_US58862_Params_tofile" writes parameters of US58862 to a file
	 * Function "Write_US58862_Params_tofile" accept following parameters
	 * Param1 name,Param2 scheduleidInt,Param3 UpdateCase,Param4 Date
	 */
	public void Write_US58862_Params_tofile(String name, int scheduleidInt,
			String UpdateCase,Date CompanyDataTime)	throws IOException {
		App_log.info("Inside data set writer_US58862 utility");

		try {
			file = new File(System.getProperty("user.dir")
					+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
					+ ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty("line.separator"));

			bufferWritter.write("Schedule ID " + scheduleidInt);
			bufferWritter.write("\t");
			bufferWritter.write("Update Case " + UpdateCase);
			bufferWritter.write("\t");
			bufferWritter.write("companydatatime " + CompanyDataTime);
			bufferWritter.close();
			App_log.info("Completed data set writing for _US58862");
		} catch (Throwable t) {
			App_log.info("Error in writing data set output for _US58862" + t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}
	}
	/* Function "Write_US58863_Params_tofile" writes parameters of US58863 to a file
	 * Function "Write_US58863_Params_tofile" accept following parameters
	 * Param1 name,Param2 entityidInt,Param3 companyidInt,Param4 remoteofficeidInt
	 */
		public void Write_US58863_Params_tofile(String name, int entityidInt,
				int companyidInt,int remoteofficeidInt)	throws IOException {
			App_log.info("Inside data set writer_US58863 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Entity ID " + entityidInt);
				bufferWritter.write("\t");
				bufferWritter.write("Company ID " + companyidInt);
				bufferWritter.write("\t");
				bufferWritter.write("RemoteOffice ID " + remoteofficeidInt);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US58863");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US58863" + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
	}
		
		/* Function "Write_US71860_Params_tofile" writes parameters of US71860 to a file
		 * Function "Write_US71860_Params_tofile" accept following parameters
		 * Param1 name,Param2 CompanyIdInt,Param3 Date,Param4 Date,Param5 ClientIdInt,Param6 CaregiverIdInt
		 * Param7 RemoteOfficeIdInt,Param8 CaseManagerIdInt,Param9 xmldata1
		 */
		public void Write_US71860_Params_tofile(String name, int CompanyIdInt,Date StartDate, Date EndDate,
				int ClientIdInt,int CaregiverIdInt,int RemoteOfficeIdInt,int CaseManagerIdInt ,String xmldata1)	throws IOException {
			App_log.info("Inside data set writer_US71860 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Company ID " + CompanyIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Start Date " + StartDate);
				bufferWritter.write("\t");
				bufferWritter.write("End Date " + EndDate);
				bufferWritter.write("\t");
				bufferWritter.write("Client ID " + ClientIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Care Giver ID " + CaregiverIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Remote Office ID " + RemoteOfficeIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Case Manager ID " + CaseManagerIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("XML Data 1 " + xmldata1);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US71860");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US71860" + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
		}
		
		/* Function "Write_US58864_Params_tofile" writes parameters of US58864 to a file
		 * Function "Write_US58864_Params_tofile" accept following parameters
		 * Param1 name,Param2 AuthorizationidInt,Param3 Date,Param4 Date,Param5 SelectedDays,Param6 ClientHrs
		 * Param7 ScheduleIdInt
		 */
		
		public void Write_US58864_Params_tofile(String name, int AuthorizationidInt,
				Date startdate, Date enddate, String SelectedDays,String ClientHrs,int ScheduleIdInt) throws IOException {
			App_log.info("Inside data set writer_US58864 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Authorization ID " + AuthorizationidInt);
				bufferWritter.write("\t");
				bufferWritter.write("Start Date " + startdate);
				bufferWritter.write("\t");
				bufferWritter.write("End Date Id " + enddate);
				bufferWritter.write("\t");
				bufferWritter.write("Selected Days " + SelectedDays);
				bufferWritter.write("\t");
				bufferWritter.write("Client Hrs " + ClientHrs);
				bufferWritter.write("\t");
				bufferWritter.write("Schedule ID " + ScheduleIdInt);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US58864");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US58864 " + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
		}
		/* Function "Write_US80735_Params_tofile" writes parameters of US80735 to a file
		 * Function "Write_US80735_Params_tofile" accept following parameters
		 * Param1 Companyid,Param2 Clientid,Param3 RemoteOfficeid
		 */
		public void Write_US80735_Params_tofile(String name, int companyidInt,
				int clientidInt,int remoteofficeidInt)	throws IOException {
			App_log.info("Inside data set writer_US80735 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Company ID " + companyidInt);
				bufferWritter.write("\t");
				bufferWritter.write("Client ID " + clientidInt);
				bufferWritter.write("\t");
				bufferWritter.write("Remote Office ID " + remoteofficeidInt);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US80735");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US80735" + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
		}
		/* Function "Write_US80738_Params_tofile" writes parameters of US80735 to a file
		 * Function "Write_US80738_Params_tofile" accept following parameters
		 * Param1 Companyid,Param2 Clientid,Param3 RemoteOfficeid
		 */
		public void Write_US80738_Params_tofile(String name,Date BillingStartDate,Date BillingEndDate,int BillingPeriodInt,int CompanyIDInt,int RemoteOfficeIdInt,String SelectedCaremanagers,String UserCode,String SelectedClients,String SelectedCallTypes)	throws IOException {
			App_log.info("Inside data set writer_US80738 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Billing Start Date " + BillingStartDate);
				bufferWritter.write("\t");
				bufferWritter.write("Billing End Date " + BillingEndDate);
				bufferWritter.write("\t");
				bufferWritter.write("Billing Period " + BillingPeriodInt);
				bufferWritter.write("\t");
				bufferWritter.write("Remote Office ID " + RemoteOfficeIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Selected Care Managers " + SelectedCaremanagers);
				bufferWritter.write("\t");
				bufferWritter.write("User Code " + UserCode);
				bufferWritter.write("\t");
				bufferWritter.write("Selected Clients " + SelectedClients);
				bufferWritter.write("\t");
				bufferWritter.write("Selected Call Types " + SelectedCallTypes);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US80738");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US80738" + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
		}
		
		/* Function "Write_US80737_Params_tofile" writes parameters of US80737 to a file
		 * Function "Write_US80737_Params_tofile" accept following parameters
		 * Param1 name,Param2 Companyid,Param3 RemoteOfficeid,Param4 InvoiceIds,Param5 ClientpayerInt
		 */
		public void Write_US80737_Params_tofile(String name,int CompanyIDInt,int RemoteOfficeIdInt,String InvoiceIds,int ClientpayerInt,String GrossCharge,String AmountToPay,String TotalPayment,String TotalFixed)	throws IOException {
			App_log.info("Inside data set writer_US80737 utility");

			try {
				file = new File(System.getProperty("user.dir")
						+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
						+ ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWritter = new FileWriter(file, true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(System.getProperty("line.separator"));

				bufferWritter.write("Company ID " + CompanyIDInt);
				bufferWritter.write("\t");
				bufferWritter.write("Remote Office ID " + RemoteOfficeIdInt);
				bufferWritter.write("\t");
				bufferWritter.write("Invoice ID " + InvoiceIds);
				bufferWritter.write("\t");
				bufferWritter.write("Client Payer ID " + ClientpayerInt);
				bufferWritter.write("\t");
				bufferWritter.write("Gross Charge " + GrossCharge);
				bufferWritter.write("\t");
				bufferWritter.write("Amount To Pay " + AmountToPay);
				bufferWritter.write("\t");
				bufferWritter.write("Total Payment " + TotalPayment);
				bufferWritter.write("\t");
				bufferWritter.write("Total Fixed " + TotalFixed);
				bufferWritter.close();
				App_log.info("Completed data set writing for _US80737");
			} catch (Throwable t) {
				App_log.info("Error in writing data set output for _US80737" + t.fillInStackTrace());
				ErrorUtil.addVerificationFailure(t);
				/*
				 * Adding errors to basket
				 * Errors will appear in report if test is run using testng.xml
				 */
			}
		}

		
		/* Function "Writestatisticstofile" writes various performance parameters to a file
		 * Function "Writestatisticstofile" accept following parameters
		 * Param1 name,Param2 Averageelapsedtime,Param3 Lastelapsedtime,Param4 LastExecutionTime,Param5 ExecutionCount
		 * 
		 */
	public void Writestatisticstofile(String name, String Averageelapsedtime,
			String Lastelapsedtime, String LastExecutionTime,
			String ExecutionCount) throws IOException {
		App_log.info("Inside  statistics writing utility");

		try {
			file = new File(System.getProperty("user.dir")
					+ "\\src\\Performance_Test_Generated_Data\\" + name+"_Statistics_with_data_set"
					+ ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty("line.separator"));

			bufferWritter.write("ElapsedTimeAvg: " + Averageelapsedtime);
			bufferWritter.write("\t");
			bufferWritter.write("ElapsedTimeLast: " + Lastelapsedtime);
			bufferWritter.write("\t");
			bufferWritter.write("ExecutionTimeLast: " + LastExecutionTime);
			bufferWritter.write("\t");
			bufferWritter.write("ExecutionCount: " + ExecutionCount);
			bufferWritter.write("\t");
			bufferWritter.close();
			App_log.info("Completed statistics writing");
		} catch (Throwable t) {
			App_log.info("Error in writing statistics " + t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}

	}
	
	/* Function "Writeonlytimeandcounttofile" writes two performance parameters to a file
	 * Function "Writestatisticstofile" accept following parameters
	 * Param1 name,Param2 Lastelapsedtime,Param3 ExecutionCount
	 * 
	 */
	public void Writeonlytimeandcounttofile(String name,String Lastelapsedtime,String ExecutionCount) throws IOException {
		App_log.info("Inside time and count output writer utility");

		try {
			file = new File(System.getProperty("user.dir")
					+ "\\src\\Performance_Test_Generated_Data\\" + name +"_Time vs Count"
					+ ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty("line.separator"));
			bufferWritter.write("ElapsedTimeLast: " + Lastelapsedtime);
			bufferWritter.write("\t");
			bufferWritter.write("ExecutionCount: " + ExecutionCount);
			bufferWritter.write("\t");
			bufferWritter.close();
			App_log.info("Completed maintaining time and count");
		} catch (Throwable t) {
			App_log.info("Error in writing time and count output " + t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}

	}
	/* Function "Write_SP_US80737_output_paramater_to_file" output of stored procedure to file
	 * Function "Write_SP_US80737_output_paramater_to_file" accept following parameters
	 * Param1 name,Param2 GrossCharge,Param3 AmountToPay,Param4 TotalPayment,Param5 TotalFixed
	 * 
	 */
	public void Write_SP_US80737_output_paramater_to_file(String name,String GrossCharge,String AmountToPay,String TotalPayment,String TotalFixed) throws IOException {
		App_log.info("Inside SP_US80737 output writer utility");

		try {
			file = new File(System.getProperty("user.dir")
					+ "\\src\\Performance_Test_Generated_Data\\" + name +"_SP_OUTPUT"
					+ ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty("line.separator"));
			bufferWritter.write("GrossCharge: "+GrossCharge);
			bufferWritter.write("\t");
			bufferWritter.write("AmountToPay: "+AmountToPay);
			bufferWritter.write("\t");
			bufferWritter.write("TotalPayment: "+TotalPayment);
			bufferWritter.write("\t");
			bufferWritter.write("TotalFixed: "+TotalFixed);
			bufferWritter.close();
			App_log.info("Completed maintaining SP_US80737 output");
		} catch (Throwable t) {
			App_log.info("Error in writing SP_US80737 output " + t.fillInStackTrace());
			ErrorUtil.addVerificationFailure(t);
			/*
			 * Adding errors to basket
			 * Errors will appear in report if test is run using testng.xml
			 */
		}

	}
}
