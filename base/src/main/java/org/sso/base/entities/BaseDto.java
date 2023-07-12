package org.sso.base.entities;

public interface BaseDto<T extends BaseDto<?, ?>, U extends BaseEntity> {
    T fromBaseEntity(U entity);
}
