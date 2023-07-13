package com.api.movie.base.entities;

import org.springframework.context.ApplicationContext;

public interface BaseQueryDto<T extends BaseQuery> {
    T createNewQuery(ApplicationContext applicationContext);
    T toQuery(ApplicationContext applicationContext);
}