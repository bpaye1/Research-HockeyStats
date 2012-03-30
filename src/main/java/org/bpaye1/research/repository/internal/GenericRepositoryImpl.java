package org.bpaye1.research.repository.internal;

import org.bpaye1.research.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericRepositoryImpl<T, ID> implements GenericRepository<T, ID> {
 
	@PersistenceContext
	public EntityManager em;
	
	public T add(T object) {
		em.persist(object);
		em.flush();
		em.clear();
		return object;
	}

	public void update(T object) {
		em.merge(object);
		em.flush();
		em.clear();
	}

    public void remove(T object) {
        em.remove(object);
        em.flush();
        em.clear();
    }

    public abstract List<T> findAll();
	public abstract T find(ID id);
}
