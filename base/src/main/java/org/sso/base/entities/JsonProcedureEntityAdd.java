package org.sso.base.entities;

import java.util.List;

public interface JsonProcedureEntityAdd<S, T> extends JsonProcedureEntity<T> {
    public S getOutId();
    public void setOutId(S outCode);

    public T getData();
    public void setData(T data);
}
