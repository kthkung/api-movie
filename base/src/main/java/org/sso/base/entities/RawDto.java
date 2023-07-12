package org.sso.base.entities;

public interface RawDto<T extends RawDto<?, ?>, U> {
    T fromBaseEntity(U entity);
}
