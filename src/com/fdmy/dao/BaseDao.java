package com.fdmy.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDao<T> {
	private SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public void add(T obj) {
		sessionTemplate.insert(obj.getClass().getName() + ".add", obj);
	}

	public void update(T obj) {
		sessionTemplate.update(obj.getClass().getName() + ".update", obj);
	}

	public void delete(Class<T> clz, String code) {
		sessionTemplate.delete(clz.getName() + ".delete", code);
	}

	public void delete(Class<T> clz, int code) {
		sessionTemplate.delete(clz.getName() + ".delete", code);
	}

	public T load(Class<T> clz, String code) {
		return sessionTemplate.selectOne(clz.getName() + ".load", code);
	}

	public T load(Class<T> clz, int code) {
		return sessionTemplate.selectOne(clz.getName() + ".load", code);
	}

	public T load(String sqlID, Object param) {
		return sessionTemplate.selectOne(sqlID, param);
	}

	public List<T> query(Class<T> clz, Object param) {
		return sessionTemplate.selectList(clz.getName() + ".query", param);
	}

	public List<T> query(String sqlID, Object param) {
		return sessionTemplate.selectList(sqlID, param);
	}

}
