package com.store.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.store.Business.POCBusiness;
import com.store.DTO.DeliveryUpdateDTO;
import com.store.DTO.ResponseMessage;
import com.store.DTO.SaleDTO;
import com.store.DTO.Student;

@Path("/shipping")
public class Shipping {
	
	@POST
	@Path("/delivery-update/{cnpj}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliveryUpdate( @PathParam("cnpj") String cnpj, DeliveryUpdateDTO saleDTO ) throws JSONException {
		try {
			POCBusiness.processDeliveryUpdate(cnpj, saleDTO);
			return Response.status(200).entity(ResponseMessage.successUpdate()).build();
		} catch (Exception e) {
			return Response.status(200).entity(ResponseMessage.errorUpdate()).build();
		}		
	}

}
