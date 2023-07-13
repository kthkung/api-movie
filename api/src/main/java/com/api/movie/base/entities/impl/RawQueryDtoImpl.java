package com.api.movie.base.entities.impl;

import lombok.Getter;
import com.api.movie.camunda.bpm.engine.rest.dto.CamundaQueryParam;
import com.api.movie.camunda.bpm.engine.rest.dto.converter.BooleanConverter;
import com.api.movie.camunda.bpm.engine.rest.dto.converter.IntegerConverter;
import com.api.movie.base.entities.RawQuery;
import com.api.movie.base.entities.RawQueryDto;

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
