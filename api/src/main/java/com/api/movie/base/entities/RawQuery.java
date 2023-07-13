package com.api.movie.base.entities;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RawQuery {
    <T extends S, S> S singleResult(Class<T> entityType, String namedNativeQuery, RawQueryCallback callback);
    <T extends S, S> List<S> listResult(Class<T> entityType, String namedNativeQuery, RawQueryCallback callback);
    <T> JsonProcedureEntityList callJsonProcedureList(Class<T> entityType, String namedStoredProcedureQuery,
                                                      StoreProcedureQueryCallback callback) throws JsonProcessingException;
    <S, T> JsonProcedureEntityAdd callJsonProcedureAdd(Class<S> idClass, Class<T> entityType, String namedStoredProcedureQuery,
                                                    StoreProcedureQueryCallback callback) throws JsonProcessingException;
    <T> JsonProcedureEntityUpdate callJsonProcedureUpdate(Class<T> entityType, String namedStoredProcedureQuery,
                                                      StoreProcedureQueryCallback callback) throws JsonProcessingException;
    boolean transactionBegin();
    boolean transactionCommit();
}
