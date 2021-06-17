package service;

import java.util.List;

public interface IGeneralService <T>{
    List<T> showAll();
    T findByID(Long id);
    void save(T t);
    void remove(Long id);

}
