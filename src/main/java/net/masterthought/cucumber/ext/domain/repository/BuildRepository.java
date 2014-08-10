package net.masterthought.cucumber.ext.domain.repository;

import java.util.List;

import javax.persistence.Query;

import net.masterthought.cucumber.ext.domain.entity.BuildEntity;

public class BuildRepository extends GenericRepositoryImpl<BuildEntity, Long> {
 
	public List<BuildEntity>  findAllBuilds() {

		Query query = entityManager.createQuery(" select e from BuildEntity e where  "
				+ "e.id in ( select max(s.id) "
				+ "from BuildEntity s group by s.projectName)");
		 return query.getResultList();
	}
 
}
