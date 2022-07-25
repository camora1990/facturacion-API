package com.sofka.tiendaonline.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICrudServices<T> {

    @Transactional(readOnly = true)
    List<T> getAll();

    @Transactional
    T create(T t);

    @Transactional(readOnly = true)
    Optional<T> get(Integer id);

    @Transactional
    Optional<T> delete(Integer id);

    @Transactional
    T update(T t, Integer id);
}
