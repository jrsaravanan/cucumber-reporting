package net.masterthought.cucumber.ext;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utils {

	
	private static final String DATE_FORMAT = "dd-MM-yyy-HH-mm-ss";

	public static String formatDate(DateTime dateTime) {
		DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
		return dateTime.toString(format);
	}
}
