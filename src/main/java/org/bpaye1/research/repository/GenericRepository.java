package org.bpaye1.research.repository;

import java.util.List;

public interface GenericRepository<T> {
	T find(Long id);
	List<T> list();
	T add(T object);
	T update(T object);
}
