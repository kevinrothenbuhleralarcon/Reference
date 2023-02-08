package com.ltp.contacts.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;


@Getter
@Setter
public class ErrorResponse {
    @JsonFormat(shape = STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private List<String> message;

    public ErrorResponse(final List<String> message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
