package dao;

import java.util.List;

public interface IGenericDAO<T> {
    T insert(T t);
    T update(T t);
    void delete(Long id);
    List<T> getByKeyword(String word);
    T getById(Long id);
    List<T> getAll();
}
