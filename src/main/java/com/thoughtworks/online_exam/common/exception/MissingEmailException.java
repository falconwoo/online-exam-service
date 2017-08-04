package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class MissingEmailException extends BaseRespException {
    public MissingEmailException() {
        super(Response.Status.BAD_REQUEST, ErrorCode.MISSING_EMAIL, "缺少邮箱信息");
    }
}
