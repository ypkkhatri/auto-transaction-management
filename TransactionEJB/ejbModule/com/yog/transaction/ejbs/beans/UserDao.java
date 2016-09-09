package com.yog.transaction.ejbs.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.yog.transaction.ejbs.annotations.JwdTransaction;
import com.yog.transaction.ejbs.domains.User;
import com.yog.transaction.ejbs.interceptors.LoggingIntercepter;
import com.yog.transaction.ejbs.interceptors.TransactionIntercepter;

@Stateless
@Local
@TransactionManagement(TransactionManagementType.BEAN)
@Interceptors({ TransactionIntercepter.class })
public class UserDao implements Serializable {

    private static final long serialVersionUID = 7393813264239969249L;

    @PersistenceContext(unitName = "JSF_LOGIN_PU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    private Class<User> entityClass;

    public UserDao() {
	entityClass = User.class;
    }

    @JwdTransaction
    public void save(User entity) {
	em.persist(entity);
    }

    @JwdTransaction
    public void delete(Object id) throws RuntimeException {
	em.remove(em.getReference(entityClass, id));
    }

    @JwdTransaction
    public User update(User entity) {
	return em.merge(entity);
    }

    public User findById(int entityId) {
	return em.find(entityClass, entityId);
    }

    @Interceptors({ LoggingIntercepter.class })
    public User findByUsername(String username) {
	User user = null;
	try {
	    user = createNamedQuery("User.findByUsername").setParameter(
		    "username", username).getSingleResult();
	} catch (NoResultException ex) {
	}
	return user;
    }

    @Interceptors({ LoggingIntercepter.class })
    public User findByEmail(String email) {
	User user = null;
	try {
	    user = createNamedQuery("User.findByEmail").setParameter("email",
		    email).getSingleResult();
	} catch (NoResultException ex) {
	}
	return user;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<User> findAll() {
	CriteriaQuery<User> cq = (CriteriaQuery) em.getCriteriaBuilder()
		.createQuery();
	cq.select(cq.from(entityClass));
	return em.createQuery(cq).getResultList();
    }

    public TypedQuery<User> createNamedQuery(String query) {
	return em.createNamedQuery(query, entityClass);
    }

    public Query createNativeQuery(String query) {
	return em.createNativeQuery(query, entityClass);
    }

}
