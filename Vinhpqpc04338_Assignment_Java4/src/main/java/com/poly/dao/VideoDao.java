package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.model.Video;
import com.poly.utils.JpaUtils;

public class VideoDao {
	private EntityManager em = JpaUtils.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	public Video create(Video entity) {
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

	public Video update(Video entity) {
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

	public Video remove(int id) {
		Video entity = this.findByid(id);
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

	public Video findByid(int id) {
		Video entity = em.find(Video.class, id);
		return entity;
	}

	public List<Video> findAll() {
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Video> list = query.getResultList();
		return list;

	}

	public List<Video> findFvById(String username) {
		TypedQuery<Video> query = em.createNamedQuery("Video.findFavById", Video.class);
		query.setParameter("id", username);
		List<Video> list = query.getResultList();
		return list;

	}
}
