package com.davidcamelo.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String message;
    private Integer errorCode;
    private String errorMessage;
    private String controller;
    private String method;
    private String path;
    private Object object;
}
