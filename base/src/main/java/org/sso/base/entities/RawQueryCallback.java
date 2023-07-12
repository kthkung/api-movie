package org.sso.base.entities;

import javax.persistence.Query;

public interface RawQueryCallback {
    Query applyRawQuery(Query query);
}
