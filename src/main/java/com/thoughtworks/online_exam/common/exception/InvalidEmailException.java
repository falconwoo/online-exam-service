package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class InvalidEmailException extends BaseResponseException {
    public InvalidEmailException() {
        super(Response.Status.BAD_REQUEST);
    }
}
