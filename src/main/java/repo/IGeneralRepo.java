package repo;

import java.util.List;

public interface IGeneralRepo <T>{
    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void remove(Long id);
}
