package com.thoughtworks.online_exam.common.exception;


import javax.ws.rs.core.Response;

public class MissingPasswordException extends BaseRespException {
    public MissingPasswordException() {
        super(Response.Status.BAD_REQUEST, ErrorCode.MISSING_PASSWORD, "缺少密码信息");
    }
}
