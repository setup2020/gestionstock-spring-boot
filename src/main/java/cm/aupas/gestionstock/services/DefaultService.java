package cm.aupas.gestionstock.services;

import java.util.List;

public interface DefaultService<T,L> {

     T save(T t);
  T findById(L id);
  List<T> findAll();
  void delete(L id);

  abstract T update(T t);



}
