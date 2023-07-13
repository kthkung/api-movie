package com.api.movie.dto.input;

import com.api.movie.base.entities.impl.RawQueryDtoImpl;
import com.api.movie.camunda.bpm.engine.rest.dto.CamundaQueryParam;
import com.api.movie.camunda.bpm.engine.rest.dto.converter.LongConverter;

import lombok.Data;

@Data
public class UserInputDto extends RawQueryDtoImpl {

    private Long id;

    @CamundaQueryParam(value = "id", converter = LongConverter.class)
    public void setId(Long id) {
        this.id = id;
    }


}