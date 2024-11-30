package ru.linux.backend.domain.person;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.linux.backend.domain.person.dto.CreatePersonDto;
import ru.linux.backend.domain.person.dto.PersonDto;
import ru.linux.backend.domain.person.dto.UpdatePersonDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(PersonEntity entity);

    List<PersonDto> toDto(List<PersonEntity> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    PersonEntity toEntity(CreatePersonDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget PersonEntity updatable, UpdatePersonDto source);
}
