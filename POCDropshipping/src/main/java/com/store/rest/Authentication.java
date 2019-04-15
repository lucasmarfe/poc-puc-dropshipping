package com.store.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.store.Business.POCBusiness;
import com.store.Model.Client;
import com.store.Model.Provider;
import com.store.Model.Token;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ws.rs.*;

@Path("/auth")
public class Authentication {
	
	
    @POST
    @Path("/tokenClient")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // application/x-www-form-urlencoded username=admin&password=123456
    public Response authenticateUser(@FormParam("email") String email, 
                                     @FormParam("password") String password) {

        try {
        	Client client = validateClient(email, password);
            String token = issueTokenClient(client);
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }
    
    @POST
    @Path("/tokenProvider")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // application/x-www-form-urlencoded username=admin&password=123456
    public Response authenticateProvider(@FormParam("cnpj") String cnpj, 
                                     @FormParam("password") String password) {

        try {
        	Provider provider = validateProvider(cnpj, password);
            String token = issueTokenProvider(provider);
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private Client validateClient(String email, String password) throws Exception {
        Client client = POCBusiness.getClientByNameAndPassword(email,password);
		if(client == null)
        {
        	throw new Exception("User or password not correct!");
        }
		return client;
    }
    
    private Provider validateProvider(String cnpj, String password) throws Exception {
        Provider provider = POCBusiness.getProviderByCnpjAndPassword(cnpj,password);
		if(provider == null)
        {
        	throw new Exception("User or password not correct!");
        }
		return provider;
    }

    private String issueTokenClient(Client client) throws Exception {
    	SecureRandom random = new SecureRandom();
    	 String tokenString = new BigInteger(130, random).toString(32);
    	 Token token = new Token(tokenString);
    	 token.setClientID(client.getId());
    	 POCBusiness.saveToken(token);
    	 return tokenString;
    }
    private String issueTokenProvider(Provider provider) throws Exception {
    	SecureRandom random = new SecureRandom();
    	 String tokenString = new BigInteger(130, random).toString(32);
    	 Token token = new Token(tokenString);
    	 token.setProviderID(provider.getId());
    	 POCBusiness.saveToken(token);
    	 return tokenString;
    }
}
