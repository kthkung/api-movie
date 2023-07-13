package com.api.movie.base.entities.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.api.movie.camunda.bpm.engine.rest.exception.RestException;
import org.springframework.http.HttpStatus;
import com.api.movie.base.entities.RawInputDto;

@Getter
public abstract class RawInputDtoImpl<T> implements RawInputDto<T> {
    @Override
    public void validateCreate() {
        return;
    }

    @Override
    public void validateUpdate() {
        return;
    }

}
