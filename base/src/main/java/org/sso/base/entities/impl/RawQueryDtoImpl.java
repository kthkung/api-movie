package org.sso.base.entities.impl;

import lombok.Getter;
import org.camunda.bpm.engine.rest.dto.CamundaQueryParam;
import org.camunda.bpm.engine.rest.dto.converter.BooleanConverter;
import org.camunda.bpm.engine.rest.dto.converter.IntegerConverter;
import org.sso.base.entities.RawQuery;
import org.sso.base.entities.RawQueryDto;

@Getter
public abstract class RawQueryDtoImpl<T extends RawQuery> implements RawQueryDto<T> {

    protected int size = 10;
    protected int page = 0;
    protected boolean count = true;

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
