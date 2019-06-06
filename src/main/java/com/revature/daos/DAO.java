package com.revature.daos;

import java.util.List;

import com.revature.utils.Josh4J;

public interface DAO<T> {
	static Josh4J j = Josh4J.getInstance();
	
	List<T> getAll();
	T getById(int id);
	T add(T obj);
	T update(T updatedObj);
	boolean delete(int id);
	
}




