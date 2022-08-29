package org.richard.neo.university;

import java.util.Optional;

public interface Service<T extends Entity & Nameable> {

    Iterable<T> findAll();

    T find(Long id);

    // MATCH (n:T {name: 'UNKNOWN'})
    //DELETE n
    void delete(Long id);

    // MATCH (n:T {name: $name}) RETURN n
    Optional<T> findByName(String name);

    T createOrUpdate(T object);

}
