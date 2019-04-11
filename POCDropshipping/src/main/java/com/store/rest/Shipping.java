package com.store.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.store.Business.POCBusiness;
import com.store.DTO.DeliveryUpdateDTO;
import com.store.DTO.ResponseMessage;

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
			return Response.status(500).entity(ResponseMessage.errorUpdate()).build();
		}		
	}

}
