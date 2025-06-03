package com.example.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String error;
    private String message;
    private String path;
    private long timestamp;
}
