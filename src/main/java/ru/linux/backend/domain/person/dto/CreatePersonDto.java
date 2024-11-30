package ru.linux.backend.domain.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;


public record CreatePersonDto(

        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @NotBlank
        String biography
) {
    @Builder
    public CreatePersonDto {
    }
}
