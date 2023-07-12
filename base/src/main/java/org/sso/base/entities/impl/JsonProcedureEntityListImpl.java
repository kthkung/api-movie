package org.sso.base.entities.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.sso.base.entities.JsonProcedureEntityList;

import java.util.List;

@Data
public class JsonProcedureEntityListImpl<T> implements JsonProcedureEntityList<T> {
    protected Integer outCode;
    protected String outMessage;
    protected String outResult;

    @JsonProperty("rowcount")
    protected Integer rowCount;

    protected List<T> data;
}
