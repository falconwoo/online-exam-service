package com.thoughtworks.online_exam.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.online_exam.common.exception.BadRequestException;

public class AuthResult {
    @JsonProperty(value="status_code")
    private String statusCode;
    private String role;
    private String token;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
