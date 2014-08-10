package net.masterthought.cucumber.ext.domain;

import java.util.List;

import net.masterthought.cucumber.ext.domain.entity.ElementEntity;
import net.masterthought.cucumber.ext.domain.entity.FeatureEntity;
import net.masterthought.cucumber.ext.domain.entity.StepEntity;
import net.masterthought.cucumber.ext.domain.repository.GenericRepository;
import net.masterthought.cucumber.ext.domain.repository.GenericRepositoryImpl;
import net.masterthought.cucumber.util.Util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Common DAO class
 * @author Saravanan Renganathan
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class FeatureRepositoryTest {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(FeatureRepositoryTest.class);
	private GenericRepository<FeatureEntity, Long> genericRepository;
	
	@Before
	public void init() {
		genericRepository = new GenericRepositoryImpl<FeatureEntity,Long>() {
		};
	}
	
	public void method1_saveFeature() {
		
		FeatureEntity entity = getEntity();
		genericRepository.save(entity);
		Assert.assertNotNull(entity.getId());
		
	}

	@Test
	public void method2_findFeature() {
		
		 List<FeatureEntity> list = genericRepository.find("cucumber-jvm");
		 
		 for (FeatureEntity entity : list) {
			 LOGGER.info("Feature Name - {}" , entity.getName());
			 LOGGER.info("Feature Project Name - {}" , entity.getProjectName());
		 }
		
	}
	
	private FeatureEntity getEntity() {
	
		FeatureEntity entity = new FeatureEntity();
		entity.setName("Test Feature");
		entity.setDescription("test descriptiom");
		entity.setProjectName("TEST");
		
		ElementEntity element = new ElementEntity("TEST Secnario" , "Description" , entity);
		StepEntity step = new StepEntity("TEST Step", "Step description", Util.Status.PASSED.toString(), null, 10L, element);
		element.getSteps().add(step);
		entity.getElements().add(element);
		return entity;
	}
	
	@After
	public void tearDown() {
		genericRepository.close();
	}

}
