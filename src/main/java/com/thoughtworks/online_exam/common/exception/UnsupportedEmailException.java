package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class UnsupportedEmailException extends BaseResponseException {
    public UnsupportedEmailException() {
        super(Response.Status.BAD_REQUEST);
    }
}
