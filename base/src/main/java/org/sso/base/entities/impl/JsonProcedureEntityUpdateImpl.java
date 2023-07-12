package org.sso.base.entities.impl;

import lombok.Data;
import org.sso.base.entities.JsonProcedureEntityUpdate;

@Data
public class JsonProcedureEntityUpdateImpl<T> implements JsonProcedureEntityUpdate<T> {
    protected Integer outCode;
    protected String outMessage;
    protected String outResult;

    protected T data;
}
