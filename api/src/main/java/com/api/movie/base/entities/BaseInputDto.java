package com.api.movie.base.entities;

public interface BaseInputDto<T extends BaseEntity> {
    T toEntity();
    void validateCreate();
    void validateUpdate();
}
