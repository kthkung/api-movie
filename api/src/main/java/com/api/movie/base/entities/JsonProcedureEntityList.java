package com.api.movie.base.entities;

import java.util.List;

public interface JsonProcedureEntityList<T> extends JsonProcedureEntity<T> {
    public Integer getRowCount();
    public void setRowCount(Integer rowCount);

    public List<T> getData();
    public void setData(List<T> data);
}
