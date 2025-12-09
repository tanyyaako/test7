package org.example.Repository;

import org.example.Domain.ServiceModel;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GeneralRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    List<T> findAll();
    Iterable<T> findAllById(Iterable<ID> ids);
    Optional<T> findById(ID id);
    Long count();
    void deleteById(ID id);
    List<ServiceModel> findByActive(Boolean isActive);
}
