package org.sso.base.entities;

public interface RawInputDto<T> {
  void validateCreate();
  void validateUpdate();
}
