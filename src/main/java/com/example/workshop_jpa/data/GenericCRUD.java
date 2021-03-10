package com.example.workshop_jpa.data;

import java.util.Collection;

public interface GenericCRUD <T, ID>{
    T findById(ID id);
    Collection<T> findAll();
    T create(T t);
    T update(T t);
    void delete(ID id);
}
