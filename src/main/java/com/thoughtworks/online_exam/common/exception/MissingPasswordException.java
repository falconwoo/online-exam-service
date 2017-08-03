package com.thoughtworks.online_exam.common.exception;


import javax.ws.rs.core.Response;

public class MissingPasswordException extends BaseResponseException {
    public MissingPasswordException() {
        super(Response.Status.BAD_REQUEST);
    }
}
