package net.masterthought.cucumber.ext.domain.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GenericRepositoryImpl<T extends Serializable, ID extends Serializable>
			extends AbstractRepository  implements GenericRepository<T,ID> {

	protected Class<T> persistentClass;
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
    public GenericRepositoryImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entityManager = getEntityManagerFactory().createEntityManager();
    }
	
	@Override
	public T save(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	@Override
	public List<T> findAll(){
		 
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<T> rootEntry = criteriaQuery.from(persistentClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
	
	@Override
	public T get(Long id) {
		
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
//        Root<T> rootEntry = criteriaQuery.from(persistentClass);
//        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
//        Predicate predicate = criteriaBuilder.equal(rootEntry.get("id"),id);
//        all.where(predicate);
//        TypedQuery<T> allQuery = entityManager.createQuery(all);
		
	    //entityManager.remove(rootEntity);
		T entity = entityManager.getReference(persistentClass, id);
        return entity;
        
	}
	
	
	@Override
	public List<T> find(String projectName) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
		Root<T> rootEntry = criteriaQuery.from(persistentClass);
		CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
		Predicate predicate = criteriaBuilder.equal(rootEntry.get("projectName"), projectName);
		all.where(predicate);
		TypedQuery<T> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}
	
	
	@Override
	public void delete(Long id) {
		entityManager.getTransaction().begin();
		T entity = get(id);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
	}
	
	@Override
	public int deleteAll() {
		entityManager.getTransaction().begin();
		String nameClass = persistentClass.getSimpleName();
		Query query = entityManager.createQuery ("DELETE FROM " + nameClass);
		int result  = query.executeUpdate ();
		entityManager.getTransaction().commit();
        return result;
	}

	@Override
	public void close() {
		entityManager.close();
		super.close();
	}

	
//	CriteriaBuilder qb = em.getCriteriaBuilder();
//	CriteriaQuery<Number> cq = qb.createQuery(Number.class);
//	Root<Event> root = cq.from(Event.class);
//	cq.select(qb.max(root.get("dateProcessed")));
//	cq.where(qb.equal(Event.get("org"), qb.parameter(MyOrgType.class, "myOrg")));
//	em.createQuery(cq).setParameter("myOrg", myOrg).getSingleResult();
	
	
	
}
