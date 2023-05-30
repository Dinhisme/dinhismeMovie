package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.model.Favorite;
import com.poly.utils.JpaUtils;

public class FavoriteDao {
	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	public Favorite create(Favorite entity) {
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

	public Favorite update(Favorite entity) {
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

	public Favorite remove(Long id) {
		Favorite entity = this.findByid(id);
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

	public Favorite findByid(Long id) {
		Favorite entity = em.find(Favorite.class, id);
		return entity;
	}

	public Long getLikes(int id) {
		TypedQuery<Long> query = em.createNamedQuery("Favorite.getLikes", Long.class);
		query.setParameter("id", id);
		Long result = query.getSingleResult();
		return result;
	}

	public Long getIdFvToUnLike(String usid, int id) {
		TypedQuery<Long> query = em.createNamedQuery("Favorite.unLike", Long.class);
		query.setParameter("usid", usid);
		query.setParameter("id", id);
		Long result = query.getSingleResult();
		return result;
	}

}
