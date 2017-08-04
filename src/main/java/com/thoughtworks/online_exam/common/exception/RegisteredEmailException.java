package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class RegisteredEmailException extends BaseRespException {
    public RegisteredEmailException() {
        super(Response.Status.BAD_REQUEST, ErrorCode.REGISTERED_EMAIL, "该邮箱已注册");
    }
}
