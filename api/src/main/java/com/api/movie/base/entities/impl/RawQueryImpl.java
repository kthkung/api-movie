package com.api.movie.base.entities.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.movie.base.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RawQueryImpl implements RawQuery {
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    @Override
    public <T extends S, S> S singleResult(Class<T> entityType, String namedNativeQuery, RawQueryCallback callback) {
        Query q = entityManager.createNamedQuery(namedNativeQuery);
        q = callback.applyRawQuery(q);
        return (S) q.getSingleResult();
    }

    @Override
    public <T extends S, S> List<S> listResult(Class<T> entityType, String namedNativeQuery, RawQueryCallback callback) {
        var q = entityManager.createNamedQuery(namedNativeQuery);
        q = callback.applyRawQuery(q);
        return (List<S>) q.getResultList();
    }

    @Override
    public <T> JsonProcedureEntityList callJsonProcedureList(Class<T> entityType, String namedStoredProcedureQuery,
                                                         StoreProcedureQueryCallback callback) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var q = entityManager.createNamedStoredProcedureQuery(namedStoredProcedureQuery);
        q = callback.applyStoreProcedureQuery(q, mapper);
        q.execute();

        JsonProcedureEntityList output = new JsonProcedureEntityListImpl<T>();
        output.setOutCode((Integer) q.getOutputParameterValue("out_code"));
        output.setOutMessage((String) q.getOutputParameterValue("out_message"));
        output.setOutResult((String) q.getOutputParameterValue("out_result"));

        if (output.getOutResult() != null) {
            var jsonObj = mapper.readValue(output.getOutResult(), JsonProcedureEntityListImpl.class);
            ArrayList arrayList = new ArrayList();
            if (jsonObj.getData() != null) {
                for (int i=0; i<jsonObj.getData().size(); i++) {
                    arrayList.add(mapper.convertValue(jsonObj.getData().get(i), entityType));
                }
            }
            output.setData(arrayList);
            output.setRowCount(jsonObj.getRowCount());
        }

        return output;
    }

    @Override
    public <S, T> JsonProcedureEntityAdd callJsonProcedureAdd(Class<S> idClass, Class<T> entityType, String namedStoredProcedureQuery, StoreProcedureQueryCallback callback) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var q = entityManager.createNamedStoredProcedureQuery(namedStoredProcedureQuery);
        q = callback.applyStoreProcedureQuery(q, mapper);
        q.execute();

        JsonProcedureEntityAdd output = new JsonProcedureEntityAddImpl<S, T>();
        output.setOutId((S) q.getOutputParameterValue("out_id"));
        output.setOutCode((Integer) q.getOutputParameterValue("out_code"));
        output.setOutMessage((String) q.getOutputParameterValue("out_message"));
        output.setOutResult((String) q.getOutputParameterValue("out_result"));

        if (output.getOutResult() != null) {
            output.setData(mapper.readValue(output.getOutResult(), entityType));
        }

        return output;
    }

    @Override
    public <T> JsonProcedureEntityUpdate callJsonProcedureUpdate(Class<T> entityType, String namedStoredProcedureQuery, StoreProcedureQueryCallback callback) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var q = entityManager.createNamedStoredProcedureQuery(namedStoredProcedureQuery);
        q = callback.applyStoreProcedureQuery(q, mapper);
        q.execute();

        JsonProcedureEntityUpdate output = new JsonProcedureEntityUpdateImpl<T>();
        output.setOutCode((Integer) q.getOutputParameterValue("out_code"));
        output.setOutMessage((String) q.getOutputParameterValue("out_message"));
        if(q.getParameters().contains("out_result")){
            output.setOutResult((String) q.getOutputParameterValue("out_result"));
        }

        if (output.getOutResult() != null) {
            output.setData(mapper.readValue(output.getOutResult(), entityType));
        }

        return output;
    }
    @Override
    public boolean transactionBegin(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return true;
    }
    @Override
    public boolean transactionCommit(){
        entityManager.getTransaction().commit();
        return true;
    }
}
