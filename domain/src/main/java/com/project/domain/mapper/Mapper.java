package com.project.domain.mapper;

import java.util.List;

/**
 * @author dhelarius 4/6/2022
 * periodent
 */
public interface Mapper {

    <E,D> D convertEntityToDto(E entity, Class<D> clazz);

    <E,D> List<D> convertEntitiesToDto(List<E> entities, Class<D> clazz);
}
