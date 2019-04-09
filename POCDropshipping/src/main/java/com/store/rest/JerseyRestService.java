package com.store.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.store.DTO.SaleDTO;
import com.store.DTO.Student;
import com.store.Model.Client;
import com.store.Util.HibernateUtil;

@Path("/jsonServices")
public class JerseyRestService {

	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON( @PathParam("name") String name ) {
		
		Student st = new Student(name, "Diaz",22,1);

		return st;

	}
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON( SaleDTO sale ) {
		
		String output = sale.toString();
		//Client user = getUserById(1);
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		Client client = new Client(1, "", "", "", "", new Date(), "", "", "");
//		session.save(client);
//        session.getTransaction().commit();
		
		return Response.status(200).entity("").build();//user.toString()).build();
	}
	
	
	
	/*
	 * @POST
	 * 
	 * @Path("/send")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response consumeJSON( Student
	 * student ) {
	 * 
	 * String output = student.toString();
	 * 
	 * return Response.status(200).entity(output).build(); }
	 */


}
