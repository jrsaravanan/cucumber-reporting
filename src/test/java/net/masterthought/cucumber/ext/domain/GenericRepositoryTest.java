package net.masterthought.cucumber.ext.domain;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

 



import net.masterthought.cucumber.ext.domain.entity.FeatureDetialsEntity;
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
public class GenericRepositoryTest {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(GenericRepositoryTest.class);
	private GenericRepository<FeatureDetialsEntity, Long> genericRepository;
	
	
	
	@Before
	public void init() {
		genericRepository = new GenericRepositoryImpl<FeatureDetialsEntity,Long>() {
		};
	}
	
	@Test
	public void method1_saveFeature() {
		
		FeatureDetialsEntity entity = getEntity();
		genericRepository.save(entity);
		Assert.assertNotNull(entity.getId());
		
	}

	private FeatureDetialsEntity getEntity() {
		FeatureDetialsEntity entity = new FeatureDetialsEntity();
		entity.setName("Test Feature");
		entity.setScenarioFailed(1);
		entity.setScenarioPassed(2);
		entity.setStepFailed(0);
		entity.setStepPassed(6);
		entity.setDuration("10 ms");
		entity.setStatus(Util.Status.PASSED.toString());
		return entity;
	}
	
	
	@Test
	public void method2_findById() {
		FeatureDetialsEntity entity = getEntity();
		genericRepository.save(entity);
		FeatureDetialsEntity findEntity = genericRepository.get(entity.getId());
		Assert.assertEquals(findEntity.getName(), findEntity.getName());
		LOGGER.debug(" feature name - {} " , entity.getName());
		
	}
	

	@Test
	public void method3_findAll() {
		List<FeatureDetialsEntity> list = genericRepository.findAll();
		Assert.assertNotNull(list);
		LOGGER.debug(" count - {} " , list.size());
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test 
	public void method4_delete() {
		
		try {
			FeatureDetialsEntity entity = getEntity();
			genericRepository.save(entity);
			genericRepository.delete(entity.getId());
			FeatureDetialsEntity deleteEntity = genericRepository.get(entity.getId());
			Assert.assertNull(deleteEntity);
			 
		} catch (NoResultException noResultException) {
			
		} catch (EntityNotFoundException	 e) {

		}
		 
	}
	
	@Test
	public void method5_deleteAll() {
		int rows = genericRepository.deleteAll();
		LOGGER.debug(" number of rows- {} " , rows);
		List<FeatureDetialsEntity> list = genericRepository.findAll();
		Assert.assertTrue(list.size() == 0);
	}
	
	@After
	public void tearDown() {
		genericRepository.close();
	}

}
