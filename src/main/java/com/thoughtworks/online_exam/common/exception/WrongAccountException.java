package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class WrongAccountException extends BaseResponseException {
    public WrongAccountException() {
        super(Response.Status.BAD_REQUEST);
    }
}
