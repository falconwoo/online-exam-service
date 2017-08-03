package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class RegisteredEmailException extends BaseResponseException {
    public RegisteredEmailException() {
        super(Response.Status.BAD_REQUEST);
    }
}
