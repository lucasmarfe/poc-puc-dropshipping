package com.store.rest;

import java.io.IOException;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.store.Business.POCBusiness;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";


    private boolean isTokenBasedAuthentication(List<String> authHeader) {
        return authHeader != null && !authHeader.isEmpty() && authHeader.get(0).toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(String message) {
    	throw new WebApplicationException(
                new Exception("Token not recognized!"),
                Response.status(Response.Status.UNAUTHORIZED)
                        .type("text/plain")
                        .entity(message)
                        .build());
    }

    private void validateToken(String token) throws Exception {
        String errorMsg = POCBusiness.validateToken(token);
    	if (errorMsg != null) {
        	throw new Exception(errorMsg);
        }
    }
	
	public ContainerRequest filter(ContainerRequest containerRequest) {
		List<String> authHeader = containerRequest.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (!isTokenBasedAuthentication(authHeader)) {
            abortWithUnauthorized("Not bearer token based authentication!");
        }

        try {
        	String token = authHeader.get(0)
                    .substring(AUTHENTICATION_SCHEME.length()).trim();
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(e.getMessage());
        }
        return containerRequest;
    }
    public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
        // Do something with the outgoing response here
        return containerResponse;
    }

	@Override
	public com.sun.jersey.spi.container.ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public com.sun.jersey.spi.container.ContainerResponseFilter getResponseFilter() {
		String authHeader = "";
		return this;
	}
}
