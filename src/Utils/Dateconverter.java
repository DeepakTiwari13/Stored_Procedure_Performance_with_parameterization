package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class Dateconverter {
	
	/* This class is responsible for generating date in sql server database required format */

	Logger App_log = Logger.getLogger("Dateconverter");
	// Object of log4j api has been created
	
	/* Function "Getdate" accept the string date and returns the SQL date
	 * Function "Getdate" accepts following parameter 
	 * Param1 Stringdatetoparse
	 */

	public Date Getdate(String datetoparse) throws ParseException {
		
		App_log.info("Inside date converter utility");

		String OLD_FORMAT = "MM/dd/yy";
		String NEW_FORMAT = "MM/dd/yyyy";

		String oldDateString = datetoparse;
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(oldDateString);
		sdf.applyPattern(NEW_FORMAT);
		newDateString = sdf.format(d);
		App_log.info("Printing string date " + newDateString);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = formatter.parse(newDateString);
		App_log.info("Printing converted date from string date " + date);
		App_log.info("Printing formatted date " + formatter.format(date));

		String NEW_FORMAT_ = "yyyyMMdd";

		Date d1 = sdf.parse(formatter.format(date));
		sdf.applyPattern(NEW_FORMAT_);
		String simpledate = sdf.format(d1);
		App_log.info("Printing date with removed [-]" + simpledate);

		SimpleDateFormat formatdatetogetsqldate = new SimpleDateFormat(
				"yyyyMMdd");
		java.util.Date Javautildate = formatdatetogetsqldate.parse(simpledate);
		App_log.info("Printing java util date from simple date " + Javautildate);

		java.util.Date utilDate = Javautildate;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		App_log.info("Printing final sql date " + sqlDate);
		return sqlDate;
				        
	}

}
