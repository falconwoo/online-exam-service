package com.thoughtworks.online_exam.common.exception.handler;

import com.thoughtworks.online_exam.common.exception.BaseRespException;
import com.thoughtworks.online_exam.common.exception.BaseResponseException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Provider
public class RespExceptionMapper implements ExceptionMapper<BaseRespException> {
    private static final Logger LOG = Logger.getLogger(RespExceptionMapper.class);

    @Override
    public Response toResponse(BaseRespException exception) {
        String fullStackTrace = ExceptionUtils.getFullStackTrace(exception);
        LOG.warn("ResponseException with status: " + exception.getStatus());
        LOG.warn(fullStackTrace);

        return Response.status(Response.Status.OK)
                .entity(new RespEntity(exception.getErrorCode(),
                        exception.getErrorMessage()).toJson())
                .type(APPLICATION_JSON_TYPE)
                .build();
    }
}
