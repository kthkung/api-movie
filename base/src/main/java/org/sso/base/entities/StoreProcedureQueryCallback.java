package org.sso.base.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.StoredProcedureQuery;

public interface StoreProcedureQueryCallback {
    StoredProcedureQuery applyStoreProcedureQuery(StoredProcedureQuery query, ObjectMapper mapper) throws JsonProcessingException;
}
