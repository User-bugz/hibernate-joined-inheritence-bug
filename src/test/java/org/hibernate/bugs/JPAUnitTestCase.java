package org.hibernate.bugs;

import org.hibernate.bugs.entity.DescendantEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void joinedEntityPersisted() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		DescendantEntity descendantEntity = new DescendantEntity();
		descendantEntity.setName("name");
		descendantEntity.setSurname("surname");

		entityManager.persist(descendantEntity);
		entityManager.flush();

		entityManager.getTransaction().commit();
		entityManager.close();

		EntityManager secondManager = entityManagerFactory.createEntityManager();
		List resultList = secondManager.createNativeQuery("select * from descendant").getResultList();
		assertThat(resultList.size(), equalTo(1));
		secondManager.close();
	}
}
