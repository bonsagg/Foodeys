package eu.jugcologne.foodeys.services;

import eu.jugcologne.foodeys.persistence.model.AbstractEntity;
import eu.jugcologne.foodeys.services.api.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract Service which provides all basic functions like CRUD, etc.
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */
public abstract class AbstractService implements Serializable, Service {
    private static final long serialVersionUID = 989016797578684211L;
    protected EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected <T extends AbstractEntity> long count(final Class<T> type) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(type)));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException nre) {
            return 0;
        }
    }

    @Override
    public <T extends AbstractEntity> void delete(final T entity) {
        em.remove(entity);
    }

    protected <T extends AbstractEntity> T deleteById(final Class<T> type, final Long id) {
        T object = findById(type, id);
        delete(object);

        return object;
    }

    protected <T extends AbstractEntity> List<T> findAll(final Class<T> type) {
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(type);
        query.from(type);

        return em.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected <T extends AbstractEntity> T findById(final Class<T> type, final Long id) {
        Class<?> clazz = getObjectClass(type);

        return (T) em.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    protected <T extends AbstractEntity> List<T> findByNamedQuery(final String namedQueryName) {
        return em.createNamedQuery(namedQueryName).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected <T extends AbstractEntity> List<T> findByNamedQuery(final String namedQueryName, final Object... params) {
        Query query = em.createNamedQuery(namedQueryName);
        int i = 1;

        for (Object p : params) {
            query.setParameter(i++, p);
        }

        return query.getResultList();
    }

    protected Class<?> getObjectClass(final Object type) throws IllegalArgumentException {
        Class<?> clazz = null;

        if (type == null) {
            throw new IllegalArgumentException("Null has no type. You must pass an Object");
        } else if (type instanceof Class<?>) {
            clazz = (Class<?>) type;
        } else {
            clazz = type.getClass();
        }

        return clazz;
    }

    @Override
    public <T extends AbstractEntity> void save(T t) {
        if (t.isPersistent()) {
            em.merge(t);
        } else {
            em.persist(t);
        }

        em.flush();
    }
}
