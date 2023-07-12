package org.sso.base.entities;

import java.time.OffsetDateTime;

public interface BaseEntity extends Entity {
//    public String getStatus();
//    public void setStatus(String status);

    public String getCreateBy();
    public void setCreateBy(String createBy);

    public OffsetDateTime getCreateDateTime();
    public void setCreateDateTime(OffsetDateTime createDateTime);

    public String getUpdateBy();
    public void setUpdateBy(String updateBy);

    public OffsetDateTime getUpdateDateTime();
    public void setUpdateDateTime(OffsetDateTime updateDateTime);
}
