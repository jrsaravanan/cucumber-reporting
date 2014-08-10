package net.masterthought.cucumber.ext.domain.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * Generic DAO pattern 
 * @author Saravanan Renganthan
 * 
 * https://community.jboss.org/wiki/GenericDataAccessObjects
 * 
 * The document has implementation in hibernate and 
 * EJB 3.0 the same has been implementation JPA 2.0
 *
 * @param <T Entity class>
 * @param <ID type of the entity class>
 */
public interface GenericRepository<T extends Serializable, ID extends Serializable> {

	/**
	 * delete all the records in entity 
	 * @return count of deleted rectods
	 */
	//TODO: This is not working as expected for Entity has relation collection
	int deleteAll();

	/**
	 * delete an entity 
	 * @param id
	 */
	void delete(Long id);
	
	/** 
	 * 
	 * @param projectName
	 * @return List<T>
	 */
	List<T> find(String projectName);

	/**
	 * retrive all record
	 * @return
	 */
	List<T> findAll();

	/**
	 * get one entity based on entity id
	 */
	T get(Long id);

	/**
	 * close {@link EntityManager}
	 */
	void close();

	/**
	 * save an entity 
	 * @param entity
	 * @return 
	 */
	T save(T entity);



}
