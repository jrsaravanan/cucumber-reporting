package net.masterthought.cucumber.ext.domain.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.masterthought.cucumber.ext.exception.DurableReportGeneratorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract DAO 
 * Base class helps to open  {@link EntityManagerFactory} connection 
 * @author Saravanan Renganathan
 *
 */
public abstract class AbstractRepository<T extends Serializable, ID extends Serializable> {
	
	
	
	private static final String DATABASE_APP_PASSWORD = "database.app.password";
	private static final String DATABASE_APP_USERNAME = "database.app.username";
	private static final String DATABASE_URL = "database.url";
	private static final String DATABASE_DRIVER = "database.driver";
	private static final String REPORTS_PROPERTIES = "/config/reports.db.properties";
	private static final String DATABASE_DIALECT = "database.dialect";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);
	private String driver;
	private String url;
	private String user;
	private String password;
	private String dialect;
	protected EntityManagerFactory entityManagerFactory;
	
	/**
	 * Set DB poperties and create EntityManagerFactory
	 * @return {@link EntityManagerFactory}
	 */
	private EntityManagerFactory setupEntityManagerFactory() {
		LOGGER.debug(">> Initalized DB/JPA ..........");
		Map<String, String> properties = new HashMap<String, String>();
		loadProperties(REPORTS_PROPERTIES);
		properties.put("hibernate.connection.driver_class", driver);
		properties.put("hibernate.connection.url", url);
		properties.put("hibernate.connection.username", user);
		properties.put("hibernate.connection.password", password);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show-sql", "true");
		return Persistence.createEntityManagerFactory("report",properties);
	}
	
	/**
	 * init {@link EntityManagerFactory}
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		
		if (entityManagerFactory == null) {
			entityManagerFactory = setupEntityManagerFactory();
		}
		return entityManagerFactory;
	}
	
	/***
	 * close the {@link EntityManagerFactory}
	 */
	public void close() {
		entityManagerFactory.close();
	}
	
	/**
	 * Load System DB 
	 * @return {@link Properties}
	 */
	public Properties loadProperties(final String fileName) {
		
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		Properties properties = new Properties();
		
		try {
			properties.load(inputStream);
			LOGGER.debug("properties value {}" , properties.toString());
			driver = properties.getProperty(DATABASE_DRIVER);
			url = properties.getProperty(DATABASE_URL);
			user = properties.getProperty(DATABASE_APP_USERNAME);
			password = properties.getProperty(DATABASE_APP_PASSWORD);
			dialect = properties.getProperty(DATABASE_DIALECT);

		} catch (IOException e) {
			throw new DurableReportGeneratorException("unable to load property file " + fileName , e);
		} catch (Exception e) {
			throw new DurableReportGeneratorException("unable to load property file " + fileName , e);
		}
		
		return properties;
		
	}

}
