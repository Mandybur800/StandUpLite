package ua.com.conductor.dao;

import java.util.List;

public interface GenericDao<T, I> {
    T add(T value);

    List<T> getAll();
}
