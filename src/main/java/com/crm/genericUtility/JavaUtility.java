package com.crm.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * This class contains all java specific common methods
 * @author r pc
 *
 */

public class JavaUtility {
	/**
	 * This method is used to generate Random Numbers
	 * @param limit
	 * @return
	 */

	public static int generateRandomNumber(int limit) {
		Random ran=new Random();
		int randomNumber=ran.nextInt(limit);
		return randomNumber;
	}
	
	/**
	 * This method is used to get the current date and time in required format
	 * @return
	 */
	public static String getCurrentTimeAndDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date=new Date();
		String requiredFormatDate=sdf.format(date);
		return requiredFormatDate;
	}
	
	public static long convertStringToLong(String value) {
		long data=Long.parseLong(value);
		return data;
	}

}
