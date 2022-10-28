package com.nttdata.afp.microservice.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toModel(E entity);
}
