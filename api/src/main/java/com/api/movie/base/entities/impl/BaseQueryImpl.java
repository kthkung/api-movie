package com.api.movie.base.entities.impl;

import org.springframework.lang.Nullable;
import com.api.movie.base.entities.BaseQuery;
import com.api.movie.base.entities.Query;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseQueryImpl<T extends Query<?, ?, ?>, U, V extends U> implements Query<T, U, V>, BaseQuery<T> {
    private final Class<V> entityType;

    @PersistenceContext
    protected EntityManager entityManager;

    private int firstResult = 0;
    private int maxResults = 10;

    public BaseQueryImpl(Class<V> entityType) {
        this.entityType = entityType;
    }

    protected Class<V> getEntityType() {
        return this.entityType;
    }

    protected abstract CriteriaQuery toQuery(CriteriaBuilder cb, CriteriaQuery query, Root queryRoot);

    protected CriteriaQuery<U> toQuery(CriteriaBuilder cb) {
        var query = cb.createQuery(entityType);
        var queryRoot = query.from(entityType);

        return toQuery(cb, query, queryRoot);
    }

    protected CriteriaQuery<Long> toCountQuery(CriteriaBuilder cb) {
        var query = cb.createQuery(Long.class);
        var queryRoot = query.from(entityType);

        return toQuery(cb, query, queryRoot).select(cb.count(queryRoot));
    }

    @Override
    public U singleResult() {
        var cb = entityManager.getCriteriaBuilder();
        var query = toQuery(cb);

        try {
            return entityManager.createQuery(query)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<U> listResult() {
        var cb = entityManager.getCriteriaBuilder();
        var query = toQuery(cb);

        return entityManager.createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public long countResult() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        var query = toCountQuery(cb);

        return entityManager.createQuery(query)
                .getSingleResult();
    }

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public T setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return (T)this;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public T setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return (T)this;
    }
}
