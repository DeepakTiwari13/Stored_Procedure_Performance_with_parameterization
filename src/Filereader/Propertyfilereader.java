package Filereader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Propertyfilereader {
	
	/* This class is responsible for reading the data from property file
	 * Property file is "config.properties" in this case
	*/
	

	Logger App_log = Logger.getLogger("Propertyfilereader");
	// Object of log4j api has been created
	
	public static Properties loadfile = null;
	public static FileInputStream Fis = null;

	public void Loadpropertyfile() throws IOException {
		App_log.info("Inside property file reader");
		loadfile = new Properties();
		Fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\Config_files\\config.properties");
		loadfile.load(Fis);

	}

}
