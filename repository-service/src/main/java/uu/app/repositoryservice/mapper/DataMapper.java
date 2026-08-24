package uu.app.repositoryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uu.app.repositoryservice.dto.DataDto;
import uu.app.repositoryservice.entity.DataEntity;

@Mapper(componentModel = "spring")
public interface DataMapper {

    DataDto map(DataEntity entity);

    @Mapping(target = "id",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DataEntity map(DataDto dto);

}
