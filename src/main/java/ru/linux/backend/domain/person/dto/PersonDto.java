package ru.linux.backend.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

import static ru.linux.backend.utils.Constants.DATE_FORMAT;

@Builder
public record PersonDto(
        Long id,

        String firstname,

        String lastname,

        String biography
) {
}
