package com.snoweegamecorp.backend.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError  implements Serializable {
    private Integer status;
    private String message;
    private String error;
    private String path;
    private Instant timeStamp;
}