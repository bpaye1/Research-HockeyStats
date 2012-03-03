package org.bpaye1.research;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration({"classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractDatabaseTest {
	
	@PersistenceUnit
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}
}
