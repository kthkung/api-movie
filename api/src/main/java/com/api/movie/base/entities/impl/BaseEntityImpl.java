package com.api.movie.base.entities.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.api.movie.base.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntityImpl implements BaseEntity {
//    @Column(name = "STATUS")
//    private String status;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_DATETIME")
    private OffsetDateTime createDateTime;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name = "UPDATE_DATETIME")
    private OffsetDateTime updateDateTime;
}
