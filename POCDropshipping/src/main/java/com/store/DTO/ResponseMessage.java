package com.store.DTO;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public final class ResponseMessage {
	public static final JSONObject successSale() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "success");
		json.put("Message", "Sale processed with success!");
		return json;
	}
	
	public static final JSONObject errorSale() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "error");
		json.put("Message", "Sale with problems!");
		return json;
	}
	
	public static final JSONObject successDeliveryMessage() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "success");
		json.put("Message", "Message received succesfully!");
		return json;
	}
	
	public static final JSONObject errorDeliveryMessage() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "error");
		json.put("Message", "Problems processing message!");
		return json;
	}
	
	public static final JsonNode errorGetMessages() throws JSONException, JsonProcessingException, IOException {
		String newString = "{\"Status\": \"error\"}";
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode newNode = mapper.readTree(newString);
		return newNode;
	}

	public static Object successUpdate() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "success");
		json.put("Message", "Update message processed with success!");
		return json;
	}
	public static Object errorUpdate() throws JSONException {
		JSONObject json = new JSONObject();  
		json.put("Status", "error");
		json.put("Message", "Error on update message processing!");
		return json;
	}
}
