package org.sso.base.entities.impl;

import lombok.Data;
import org.sso.base.entities.JsonProcedureEntityAdd;

@Data
public class JsonProcedureEntityAddImpl<S, T> implements JsonProcedureEntityAdd<S, T> {
    protected Integer outCode;
    protected String outMessage;
    protected String outResult;
    protected S outId;
    protected T data;
}
