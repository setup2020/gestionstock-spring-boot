package cm.aupas.gestionstock.services;

import cm.aupas.gestionstock.dto.ResponsePaginationDto;

import java.util.List;

public interface DefaultService2<T,L> {

     T save(T t);
  T findById(L id);
  ResponsePaginationDto<T> findAll(int page, int size, List<String> sort);
  void delete(L id);

  abstract T update(T t);



}
