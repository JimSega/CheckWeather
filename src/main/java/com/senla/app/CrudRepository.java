package com.senla.app;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<Weather, Integer> extends Repository<Weather, Integer> {
    <S extends Weather> S save(S entity);
    <S extends Weather> Iterable<S> saveAll(Iterable<S> entities);
    Optional<Weather> findById(Integer id);
    boolean existsById(Integer id);
    Iterable<Weather> findAll();
    Iterable<Weather> findAllById(Iterable<Integer> ids);
    long count();
    void deleteById(Integer id);
    void delete(Weather entity);
    void deleteAllById(Iterable<? extends Integer> ids);
    void deleteAll(Iterable<? extends Integer> entities);
    void deleteAll();
}
