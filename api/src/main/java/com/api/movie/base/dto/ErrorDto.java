package com.api.movie.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDto {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    public ErrorDto(String message) {
        this.code = 1000;
        this.message = message;
    }

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
