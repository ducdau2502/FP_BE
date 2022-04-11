package gethigh.fp_be.service;

import java.util.Optional;

public interface _IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
