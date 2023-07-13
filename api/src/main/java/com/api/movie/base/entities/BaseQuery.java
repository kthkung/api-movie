package com.api.movie.base.entities;

public interface BaseQuery<T> {
    int getFirstResult();
    T setFirstResult(int firstResult);
    int getMaxResults();
    T setMaxResults(int maxResults);
}
