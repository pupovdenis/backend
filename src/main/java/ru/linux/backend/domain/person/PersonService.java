package ru.linux.backend.domain.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.linux.backend.domain.person.dto.CreatePersonDto;
import ru.linux.backend.domain.person.dto.PersonDto;
import ru.linux.backend.domain.person.dto.UpdatePersonDto;
import ru.linux.backend.exception.NotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDto> getPersons() {
        var persons = personRepository.findAll();
        return personMapper.toDto(persons);
    }

    public PersonDto getPersonById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> new NotFoundException("person id " + id + " not found"));
    }

    public PersonDto createPerson(CreatePersonDto dto) {
        var entity = personMapper.toEntity(dto);
        var saved = personRepository.save(entity);
        return personMapper.toDto(saved);
    }

    public PersonDto update(Long id, UpdatePersonDto dto) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("person id " + id + " not found"));
        personMapper.update(person, dto);
        var saved = personRepository.save(person);
        return personMapper.toDto(saved);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
