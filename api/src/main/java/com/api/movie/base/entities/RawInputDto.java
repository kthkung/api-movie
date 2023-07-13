package com.api.movie.base.entities;

public interface RawInputDto<T> {
  void validateCreate();
  void validateUpdate();
}
