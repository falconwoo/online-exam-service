package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public class WrongAccountException extends BaseRespException {
    public WrongAccountException() {
        super(Response.Status.BAD_REQUEST,ErrorCode.WRONG_ACCOUNT,"邮箱或密码错误");
    }
}
