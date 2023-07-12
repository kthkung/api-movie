package org.sso.base.entities.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.sso.base.entities.BaseDto;
import org.sso.base.entities.BaseEntity;

import java.time.OffsetDateTime;

@Data
public abstract class BaseDtoImpl<T extends BaseDto<?, ?>, U extends BaseEntity> implements BaseDto<T, U> {
//    @JsonProperty("status")
//    protected String status;

    @JsonProperty("createUser")
    protected String createUser;

    @JsonProperty("createDateTime")
    protected OffsetDateTime createDateTime;

    @JsonProperty("updateUser")
    protected String updateUser;

    @JsonProperty("updateDateTime")
    protected OffsetDateTime updateDateTime;

    @Override
    public T fromBaseEntity(U entity) {
//        setStatus(entity.getStatus());
        setCreateUser(entity.getCreateBy());
        setCreateDateTime(entity.getCreateDateTime());
        setUpdateUser(entity.getUpdateBy());
        setUpdateDateTime(entity.getUpdateDateTime());

        return (T)this;
    }
}
