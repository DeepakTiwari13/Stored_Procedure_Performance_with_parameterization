package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ErrorUtil {
	
	/*This class is responsible for adding the errors in basket
	 * 
	 */

	// Object of log4j api has been created
	static Logger App_log = Logger.getLogger("ErrorUtil");
	private static Map<ITestResult, List> verificationFailuresMap = new HashMap<ITestResult, List>();

	public static void addVerificationFailure(Throwable e) {
		App_log.info("*************addVerificationFailure******************");
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(),
				verificationFailures);
		verificationFailures.add(e);
	}

	public static List getVerificationFailures() {
		App_log.info("*************getVerificationFailures******************");
		List verificationFailures = verificationFailuresMap.get(Reporter
				.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList()
				: verificationFailures;
	}

}