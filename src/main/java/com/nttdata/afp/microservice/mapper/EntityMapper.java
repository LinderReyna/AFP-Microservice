package com.nttdata.afp.microservice.mapper;

public interface EntityMapper<D, E> {
    E toDomain(D dto);
    D toDto(E entity);
}
