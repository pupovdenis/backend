package ru.linux.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.linux.backend.domain.person.PersonService;
import ru.linux.backend.domain.person.dto.CreatePersonDto;
import ru.linux.backend.domain.person.dto.PersonDto;
import ru.linux.backend.domain.person.dto.UpdatePersonDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/persons")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons() {
        var result = personService.getPersons();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        var result = personService.getPersonById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<PersonDto> createPerson(@RequestBody @Validated CreatePersonDto dto) {
        var result = personService.createPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody @Validated UpdatePersonDto dto) {
        var result = personService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
