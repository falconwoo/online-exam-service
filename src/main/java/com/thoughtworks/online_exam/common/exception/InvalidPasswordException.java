package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class InvalidPasswordException extends BaseRespException {
    public InvalidPasswordException() {
        super(Response.Status.BAD_REQUEST,ErrorCode.INVALID_PASSWORD, "密码位数有误");
    }
}
