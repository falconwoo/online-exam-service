package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class MissingEmailException extends BaseResponseException {
    public MissingEmailException() {
        super(Response.Status.BAD_REQUEST);
    }
}
