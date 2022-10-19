package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.DomainEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T extends DomainEntity<ID>, ID> {
    void deleteById(ID id);

    boolean existsById(ID id);

    Optional<T> findById(ID id);

    T save(T entity);

    Collection<T> findAll();
}
