package org.sso.base.entities;

import java.util.List;

public interface Query<T extends Query<?, ?, ?>, U, V extends U> {
    U singleResult();
    List<U> listResult();
    long countResult();
    U create(U entity, String creatorUser);
    int update(U entity, String updaterUser);
}
