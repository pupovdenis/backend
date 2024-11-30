package ru.linux.backend.domain.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

import static ru.linux.backend.utils.Constants.DATE_FORMAT;


public record UpdatePersonDto(
        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @NotBlank
        String biography
) {


    @Builder
    public UpdatePersonDto {
    }
}
