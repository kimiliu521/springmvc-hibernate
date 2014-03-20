package com.kimi.forum.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class BaseDao<T> {
	private Class<T> entityClass;
	@Autowired
	private SessionFactory sessionFactory;
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class)params[0];
	}
	public T load (Serializable id) {
		return (T)getSession().load(entityClass, id);
	}
	
	
	public void save(T entity) {
		getSession().save(entity);
	}
	
	public void remove(T entity) {
		getSession().delete(entity);
	}
	public void remove(Serializable id) {
		getSession().delete(get(id));
	}
	public T get(Serializable id) {
        return (T) getSession().get(this.entityClass, id);
    }
	public void update(T entity) {
		getSession().update(entity);
	}
	
	public List<T> find(String hql) {
		return createQuery(hql).list();
	}
	
	public List<T> find(String hql,Object... objects) {
		return createQuery(hql,objects).list();
	}
	
	public Page pagedQuery(String hql,int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >=1 ,"pageNo should start from 1");
		String countQueryString  = "SELECT COUNT(*) " + removeSelect(removeOrders(hql));
		List countList = find(countQueryString, values);
		long totalCount = (Long)countList.get(0);
		if(totalCount < 1) {
			return new Page();
		}
		int startIndex = Page.getStartOfPage(pageNo,pageSize);
		Query query = createQuery(hql,values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	public Session getSession() {
		return sessionFactory.openSession();
	}
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	private String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
}
