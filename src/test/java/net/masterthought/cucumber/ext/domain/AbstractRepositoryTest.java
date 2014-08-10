package net.masterthought.cucumber.ext.domain;

import javax.persistence.EntityManagerFactory;

import net.masterthought.cucumber.ext.domain.entity.FeatureDetialsEntity;
import net.masterthought.cucumber.ext.domain.repository.AbstractRepository;
import net.masterthought.cucumber.ext.exception.DurableReportGeneratorException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Common DAO class
 * @author Saravanan Renganathan
 *
 */
public class AbstractRepositoryTest {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepositoryTest.class);
	
	AbstractRepository repository = new AbstractRepository() {
	};

	private EntityManagerFactory entityManagerFactory;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void init() {
		 entityManagerFactory = repository.getEntityManagerFactory();
	}
	
	@Test
	 public void  getEntityManager() {
		 Assert.assertNotNull(entityManagerFactory);
		 boolean loaded =  entityManagerFactory.getPersistenceUnitUtil().isLoaded(FeatureDetialsEntity.class);
		 LOGGER.debug("Persitance class Report loaded {}", loaded);
		 Assert.assertTrue(loaded);
	}
	
	@Test
	public void loadProperties() {
		thrown.expect(DurableReportGeneratorException.class);
		thrown.expectMessage("unable to load property file");
		repository.loadProperties("/test/path");
	}
	
	@After
	 public void  closeEntityManager() {
		 entityManagerFactory.close();
	}
}
