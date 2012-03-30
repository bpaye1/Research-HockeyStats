package org.bpaye1.research.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
	T find(ID id);
	T add(T object);
	void update(T object);
	void remove(T object);
    List<T> findAll();
}

