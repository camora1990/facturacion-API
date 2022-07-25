package com.sofka.tiendaonline.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrudServices<T> {

    List<T> getAll();

    T create(T t);

    Optional<T> get(Integer id);

    Optional<T> delete(Integer id);

    T update(T t, Integer id);
}
