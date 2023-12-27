package com.clinic.DTO.mapper;

import org.modelmapper.ModelMapper;

public class EntityMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, T> D mapToDTO(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, T> T mapToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
