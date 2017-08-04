package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class UnsupportedEmailException extends BaseRespException {
    public UnsupportedEmailException() {
        super(Response.Status.BAD_REQUEST,ErrorCode.UNSUPPORTED_EMAIL,"不支持的邮箱地址");
    }
}
