package net.masterthought.cucumber.ext.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class DurableReportGeneratorException extends RuntimeException {

	public static final Logger LOGGER = LoggerFactory.getLogger(DurableReportGeneratorException.class);
	
	public DurableReportGeneratorException(String message) {
		super(message);
		LOGGER.error(message);
	}
	
	public DurableReportGeneratorException(Exception e) {
		super(e);
		LOGGER.error(e.getMessage() ,e );
		
	}
	
	public DurableReportGeneratorException(String message,Exception e) {
		super(message,e);
		LOGGER.error(message ,e );
		
	}
}
