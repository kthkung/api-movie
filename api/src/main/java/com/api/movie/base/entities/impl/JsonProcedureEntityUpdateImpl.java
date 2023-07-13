package com.api.movie.base.entities.impl;

import lombok.Data;
import com.api.movie.base.entities.JsonProcedureEntityUpdate;

@Data
public class JsonProcedureEntityUpdateImpl<T> implements JsonProcedureEntityUpdate<T> {
    protected Integer outCode;
    protected String outMessage;
    protected String outResult;

    protected T data;
}
