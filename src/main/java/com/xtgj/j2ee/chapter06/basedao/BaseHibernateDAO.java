package com.xtgj.j2ee.chapter06.basedao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.xtgj.j2ee.chapter06.session.HibernateSessionFactory;



public abstract class BaseHibernateDAO {

	private Session session = null;

	public void setSession(Session session) {
		this.session = session;
	}

	protected Session getSession() {
		this.session = HibernateSessionFactory.getSession();
		return this.session;
	}

	protected void closeSession() {
		this.session = null;
		HibernateSessionFactory.closeSession();
	}

	protected void add(Object item) {

		Transaction tx = null;
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			session.save(item);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}

	protected Object get(Class clz, Serializable id) {

		Object item = null;
		Session session = getSession();
		try {
			item = session.get(clz, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return item;
	}

	protected void delete(Class clz, Serializable id) {
		Transaction tx = null;
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			getSession().delete(this.get(clz, id));
			tx.commit();
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}

	protected void update(Object item) {
		Transaction tx = null;
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			session.update(item);
			tx.commit();
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}

	protected List search(Class clazz, Object condition) {
		try {
			List results = getSession().createCriteria(clazz).add(
					Example.create(condition)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
}
