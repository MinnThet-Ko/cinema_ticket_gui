package com.cinema.dao;

public abstract class UpdateBehavior<T> extends AbstractDao<T>{
	public abstract void updateEntity(T entity);
	
}
