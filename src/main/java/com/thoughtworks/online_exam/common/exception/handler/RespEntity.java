package com.thoughtworks.online_exam.common.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.online_exam.common.exception.BadRequestException;
import com.thoughtworks.online_exam.common.exception.ErrorCode;

import java.util.Map;

public class RespEntity {
    @JsonProperty(value="status_code")
    private ErrorCode errorCode;
    @JsonProperty(value="error_msg")
    private String message;

    public RespEntity(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        }
    }
}
