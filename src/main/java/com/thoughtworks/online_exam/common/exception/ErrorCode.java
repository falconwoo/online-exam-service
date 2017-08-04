package com.thoughtworks.online_exam.common.exception;

public enum ErrorCode {
    // 0 ~ 2000, system error
    UNKNOWN(0),
    RESOURCE_NOT_FOUND(1),

    INVALID_EMAIL(100),
    UNSUPPORTED_EMAIL(101),
    REGISTERED_EMAIL(102),
    MISSING_EMAIL(103),
    MISSING_PASSWORD(104),
    WRONG_ACCOUNT(105);

    private final int code;

    ErrorCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
