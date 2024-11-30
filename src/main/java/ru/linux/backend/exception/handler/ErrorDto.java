package ru.linux.backend.exception.handler;

import java.util.List;

record ErrorDto(
        String message,
        List<String> errors
) {
}
