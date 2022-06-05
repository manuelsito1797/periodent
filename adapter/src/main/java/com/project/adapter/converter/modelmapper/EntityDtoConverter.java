package com.project.adapter.converter.modelmapper;

import com.project.domain.mapper.Mapper;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author djimenez on 29/3/2022
 * periodental
 */
public class EntityDtoConverter implements Mapper {

    private final ModelMapper modelMapper;

    public EntityDtoConverter() {
        modelMapper = new ModelMapper();
    }

    @Override
    public <E,D> D convertEntityToDto(E entity, Class<D> clazz) {
        return modelMapper.map(entity, clazz);
    }

    @Override
    public <E,D> List<D> convertEntitiesToDto(List<E> entities, Class<D> clazz) {
        return entities.stream().map(mapper -> convertEntityToDto(mapper, clazz))
                .collect(Collectors.toList());
    }
}
