package org.bpaye1.research.repository.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericRepositoryImpl<T> {

	@PersistenceContext
	public EntityManager em;
	
	public T add(T object) {
		em.persist(object);
		em.flush();
		em.clear();
		return object;
	}

	public T update(T object) {
		em.merge(object);
		em.flush();
		em.clear();
		return object;
	}

	public abstract List<T> list();
	public abstract T find(Long Id);
}
