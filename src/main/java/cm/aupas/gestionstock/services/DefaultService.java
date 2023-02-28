package cm.aupas.gestionstock.services;

import java.util.List;

public interface DefaultService<T,L> {

 public    T save(T t);
 public T findById(L id);
 public List<T> findAll();
 public void delete(L id);

 public T update(T t);



}
