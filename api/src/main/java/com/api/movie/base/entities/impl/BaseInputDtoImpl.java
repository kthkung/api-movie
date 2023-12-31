package com.api.movie.base.entities.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.api.movie.camunda.bpm.engine.rest.exception.RestException;
import org.springframework.http.HttpStatus;
import com.api.movie.base.entities.BaseEntity;
import com.api.movie.base.entities.BaseInputDto;

@Getter
public abstract class BaseInputDtoImpl<T extends BaseEntity> implements BaseInputDto<T> {
    @JsonProperty("status")
    protected String status;

    public T toEntity(T entity) {
//        entity.setStatus(getStatus());
        return entity;
    }

    @Override
    public void validateCreate() {
//        if (status == null)
//            throw new RestException(HttpStatus.BAD_REQUEST, "Status is required");

        return;
    }

    @Override
    public void validateUpdate() {
        return;
    }

    public void setStatus(String status) {
        if (status.compareTo("Y") == 0)
            this.status = "Y";
        else
            this.status = "N";
    }
}
