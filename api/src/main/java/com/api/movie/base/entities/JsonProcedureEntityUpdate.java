package com.api.movie.base.entities;

public interface JsonProcedureEntityUpdate<T> extends JsonProcedureEntity<T>{
    public T getData();
    public void setData(T data);
}
