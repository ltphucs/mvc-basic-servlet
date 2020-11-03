package com.phucle.blog.service;

import java.util.List;

public interface BaseService<T> {
	List<T> findAll();
	T findById(int id);
	void save(T object);
	void update(T object);
	void delete(int id);
}
