package com.api.movie.base.entities.impl;

import lombok.Data;
import com.api.movie.base.entities.JsonProcedureEntityAdd;

@Data
public class JsonProcedureEntityAddImpl<S, T> implements JsonProcedureEntityAdd<S, T> {
    protected Integer outCode;
    protected String outMessage;
    protected String outResult;
    protected S outId;
    protected T data;
}
