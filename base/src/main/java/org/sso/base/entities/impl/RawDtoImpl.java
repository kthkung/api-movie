package org.sso.base.entities.impl;

import lombok.Data;
import org.sso.base.entities.RawDto;

@Data
public abstract class RawDtoImpl<T extends RawDto<?, ?>, U> implements RawDto<T, U> {
    @Override
    public T fromBaseEntity(U entity) {
        return (T)this;
    }
}
