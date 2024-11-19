package gr.knowledge.induction.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class GenericModelMapper<E,D> {
    private final ModelMapper modelMapper;
    private final Class<D> dtoClass;
    private final Class<E> entityClass;

    public GenericModelMapper(ModelMapper modelMapper,Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    /**
     * Converts an entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    public D toDTO(E entity) {
        D dto = modelMapper.map(entity, dtoClass);
        return dto;
    }

    /**
     * Converts a DTO to Entity.
     *
     * @param dto the dto to convert
     * @return the Entity
     */
    public E toEntity(D dto) {
        E entity = modelMapper.map(dto, entityClass);
        return entity;
    }

    /**
     * Converts an entity List to a DTO list.
     *
     * @param entitylist the entity list to convert
     * @return the DTO list
     */
    public List<D> toDTO(List<E> entitylist) {
        List<D> dto = entitylist.stream() 
            .map(entity -> modelMapper.map(entity, dtoClass)) 
            .collect(Collectors.toList());
        return dto;
    }

    /**
     * Converts a DTO List to an Entity list.
     *
     * @param dtolist the dto list to convert
     * @return the entity list
     */
    public List<E> toEntity(List<D> dtolist) {
        List<E> entity = dtolist.stream()
            .map(dto -> modelMapper.map(dto, entityClass))
            .collect(Collectors.toList());
        return entity;
    }

    
}
