package com.senla.app;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<Weather, Integer> extends Repository<Weather, Integer> {
    <S extends Weather> S save(S entity);
    <S extends Weather> Iterable<S> saveAll(Iterable<S> entities);
    Optional<Weather> findById(Integer id);
    //@Query("FROM Weather ORDER BY id DESC LIMIT 1")
    @Query("FROM Weather WHERE localDate IS NOT NULL AND localTime IS NOT NULL ORDER BY localDate DESC, localTime DESC LIMIT 1")
    Optional<Weather> findLastRecord();
    @Query("SELECT AVG(tempC) FROM Weather WHERE localDate BETWEEN ?1 AND ?2")
    Optional<Double> findAverageTemp(LocalDate dateFrom, LocalDate dateTo);
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
