package ua.com.conductor.service;

import java.util.List;

public interface GenericService<T, I> {
    T add(T value);

    List<T> getAll();
}
