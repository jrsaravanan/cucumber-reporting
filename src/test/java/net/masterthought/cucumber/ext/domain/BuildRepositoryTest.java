package net.masterthought.cucumber.ext.domain;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

 




import net.masterthought.cucumber.ext.domain.entity.BuildEntity;
import net.masterthought.cucumber.ext.domain.entity.FeatureDetialsEntity;
import net.masterthought.cucumber.ext.domain.repository.BuildRepository;
import net.masterthought.cucumber.ext.domain.repository.GenericRepository;
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
public class BuildRepositoryTest {
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(BuildRepositoryTest.class);
	private BuildRepository repository;
	
	
	
	@Before
	public void init() {
		repository = new BuildRepository();
	}
	
//	@Test
//	public void getAllBuild() {
//		
//		List<BuildEntity> builds = repository.findBuilds();
//		for (BuildEntity buildEntity : builds) {
//			LOGGER.info("build id {} - name  {} ", buildEntity.getId() , buildEntity.getProjectName() );
//		}
//	}
	
	@Test
	public void findBuild() {
		
		List builds = repository.findAllBuilds();
		for (Object object : builds) {
			BuildEntity buildEntity = (BuildEntity) object;
			LOGGER.info("build id {} - name  {} ", buildEntity.getId() , buildEntity.getProjectName() );
		}
	}
	 
}
