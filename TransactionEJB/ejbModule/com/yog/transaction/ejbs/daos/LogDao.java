package com.yog.transaction.ejbs.daos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.yog.transaction.ejbs.annotations.JwdTransaction;
import com.yog.transaction.ejbs.domains.Log;

@Stateless
@Local
public class LogDao implements Serializable {

    private static final long serialVersionUID = 7393813264239969249L;

    @PersistenceContext(unitName = "JSF_LOGIN_PU")
    private EntityManager em;

    private Class<Log> entityClass;

    public LogDao() {
	entityClass = Log.class;
    }

    @JwdTransaction
    public void save(Log entity) {
	em.persist(entity);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Log> findAll() {
	CriteriaQuery<Log> cq = (CriteriaQuery) em.getCriteriaBuilder()
		.createQuery();
	cq.select(cq.from(entityClass));
	return em.createQuery(cq).getResultList();
    }

    public TypedQuery<Log> createNamedQuery(String query) {
	return em.createNamedQuery(query, entityClass);
    }

    public Query createNativeQuery(String query) {
	return em.createNativeQuery(query, entityClass);
    }

}
