package com.poly.dao;

import javax.persistence.EntityManager;

import com.poly.model.Share;
import com.poly.utils.JpaUtils;

public class ShareDao {
	private EntityManager em = JpaUtils.getEntityManager();

	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	public Share create(Share entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public Share update(Share entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public Share remove(Long id) {
		Share entity = this.findByid(id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public Share findByid(Long id) {
		Share entity = em.find(Share.class, id);
		return entity;
	}

}
