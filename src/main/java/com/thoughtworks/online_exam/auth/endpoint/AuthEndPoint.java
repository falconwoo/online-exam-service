package com.thoughtworks.online_exam.auth.endpoint;

import com.thoughtworks.online_exam.auth.entity.AuthInfo;
import com.thoughtworks.online_exam.auth.entity.AuthResult;
import com.thoughtworks.online_exam.auth.service.AuthService;
import com.thoughtworks.online_exam.common.exception.BadRequestException;
import com.thoughtworks.online_exam.product.entity.Product;
import com.thoughtworks.online_exam.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Path("auth")
@Api(value = "auth", description = "Access to auth resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthEndPoint {
    @Autowired
    private AuthService authService;

    @Path("/signup")
    @ApiOperation(value = "Sign up", response = AuthResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sign up successfully")
    })
    @POST
    public Response signup(AuthInfo authInfo) {
        AuthResult authResult = authService.signup(authInfo);
        return Response.ok().entity(authResult).build();
    }

    @Path("/signin")
    @ApiOperation(value = "Sign in", response = AuthResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sign in successfully")
    })
    @POST
    public Response signin(AuthInfo authInfo) {
        AuthResult authResult = authService.signin(authInfo);
        return Response.ok().entity(authResult).build();
    }
}
