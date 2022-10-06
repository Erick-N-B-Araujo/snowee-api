package com.snoweegamecorp.backend.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError  implements Serializable {
    private static final long serialVerionUID = 1L;
    private Integer status;
    private String message;
    private String error;
    private String path;
    private Instant timeStamp;

    public StandardError(){
    }
    public Instant getTimeStamp() {
        return timeStamp;
    }
}