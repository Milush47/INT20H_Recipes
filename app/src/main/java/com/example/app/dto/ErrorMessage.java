package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/*
    ErrorMessage is used for wrap error message in easy way for frontend
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorMessage {
    private int     statusCode;
    private Date    timeStamp;
    private String  message;
    private String  description;
}
