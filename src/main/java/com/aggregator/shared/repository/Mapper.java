package com.aggregator.shared.repository;

public interface Mapper<D, E> {
    E toEntity(D domain);

    D toDomain(E entity);
}
