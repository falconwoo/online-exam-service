package com.thoughtworks.online_exam.common.exception;

import javax.ws.rs.core.Response;

public abstract class BaseRespException extends RuntimeException {
    private final Response.Status status;
    private final ErrorCode errorCode;
    private final String errorMessage;

    protected BaseRespException(final Response.Status status) {
        this(status, ErrorCode.UNKNOWN, "");
    }

    protected BaseRespException(final Response.Status status, ErrorCode errorCode) {
        this(status, errorCode, "");
    }

    protected BaseRespException(final Response.Status status,
                                final ErrorCode errorCode,
                                final String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public final Response.Status getStatus() {
        return status;
    }

    public final ErrorCode getErrorCode() {
        return errorCode;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }
}
