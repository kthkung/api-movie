package org.sso.base.entities.impl;

import lombok.Getter;
import org.camunda.bpm.engine.rest.dto.CamundaQueryParam;
import org.camunda.bpm.engine.rest.dto.converter.BooleanConverter;
import org.camunda.bpm.engine.rest.dto.converter.IntegerConverter;
import org.springframework.context.ApplicationContext;
import org.sso.base.entities.BaseQuery;
import org.sso.base.entities.BaseQueryDto;

@Getter
public abstract class BaseQueryDtoImpl<T extends BaseQuery> implements BaseQueryDto<T> {

    protected int size = 10;
    protected int page = 0;
    protected boolean count = true;

    @Override
    public T toQuery(ApplicationContext applicationContext) {

         System.out.println("list: " + createNewQuery(applicationContext));
        var query = createNewQuery(applicationContext);
        query.setFirstResult(page*size);
        query.setMaxResults(size);
        return query;
    }

    @CamundaQueryParam(value = "size", converter = IntegerConverter.class)
    public void setSize(int size) {
        this.size = size;
    }

    @CamundaQueryParam(value = "page", converter = IntegerConverter.class)
    public void setPage(int page) {
        this.page = page;
    }

    @CamundaQueryParam(value = "count", converter = BooleanConverter.class)
    public void setCount(boolean count) {
        this.count = count;
    }
}
