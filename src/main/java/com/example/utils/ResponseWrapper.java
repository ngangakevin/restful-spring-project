package com.example.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int responseCode;
    private String message;
    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private long totalPages;
    private long timestamp;
}
