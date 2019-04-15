package com.store.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.kafka.common.KafkaException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jettison.json.JSONException;
import com.store.Business.POCBusiness;
import com.store.DTO.ResponseMessage;
import com.store.DTO.SaleDTO;
import com.sun.jersey.spi.container.ResourceFilters;
@Secured
@Path("/shopping")
public class Shopping {
	
	@POST
	@ResourceFilters(AuthFilter.class)
	@Path("/buy")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saleJSONdata( SaleDTO saleDTO ) throws JSONException {
		try {
			POCBusiness.processSale(saleDTO);
			return Response.status(200).entity(ResponseMessage.successSale()).build();
		}
		catch (KafkaException e)
		{
			return Response.status(500).entity(ResponseMessage.errorKafkaSale()).build();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(ResponseMessage.errorSale()).build();
		}
	}
	
	@GET
	@ResourceFilters(AuthFilter.class)
	@Path("/get-sales/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSales( @PathParam("cnpj") String cnpj) throws JSONException, JsonProcessingException, IOException {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = POCBusiness.getSalesByProviderAndTimeWindow(cnpj);
			return Response.status(200).entity(jsonResponse).build();
		} catch (Exception e) {
			jsonResponse = ResponseMessage.errorGetMessages();
			return Response.status(500).entity(jsonResponse).build();
		}
	}

}
