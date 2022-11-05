package com.nttdata.afp.microservice.mapper;

import com.nttdata.afp.microservice.model.Withdrawal;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toModel(E entity);
    List<E> toEntity(List<D> dtoList);
    List<D> toModel(List<E> entityList);
    default String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    TypeMapper getType();
}
