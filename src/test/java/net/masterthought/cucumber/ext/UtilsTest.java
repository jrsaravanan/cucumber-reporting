package net.masterthought.cucumber.ext;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilsTest.class);

	@Test
	 public void getDate() {
		 
		DateTime dateTime = new DateTime();
		String date = Utils.formatDate(dateTime);
		LOGGER.info("formatted date : " + date);
	 }
	 
}
