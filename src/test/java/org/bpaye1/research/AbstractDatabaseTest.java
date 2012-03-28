package org.bpaye1.research;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration({"classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractDatabaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}
	
	public void persist(Object entity){
		em.persist(entity);
		em.flush();
		em.clear();
	}
}
