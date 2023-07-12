package org.sso.base.entities;

public interface JsonProcedureEntity<T> {
    public Integer getOutCode();
    public void setOutCode(Integer outCode);

    public String getOutMessage();
    public void setOutMessage(String outMessage);

    public String getOutResult();
    public void setOutResult(String outResult);
}
