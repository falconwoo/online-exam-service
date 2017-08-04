package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class InvalidEmailException extends BaseRespException {
    public InvalidEmailException() {
        super(Response.Status.BAD_REQUEST,ErrorCode.INVALID_EMAIL, "无效的邮箱地址");
    }
}
