package com.kimi.forum.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDao<T> {
	private Class<T> entityClass;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class)params[0];
	}
	public T load (Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}
	
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}
	
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public List find(String hql,Object... objects) {
		return getHibernateTemplate().find(hql, objects);
	}
	
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
