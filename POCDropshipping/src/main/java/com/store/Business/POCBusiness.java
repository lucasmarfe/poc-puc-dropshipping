package com.store.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jettison.json.JSONObject;
import org.javatuples.Pair;

import com.store.DB.DBOperator;
import com.store.DTO.DeliveryUpdateDTO;
import com.store.DTO.ProductSoldMessageDTO;
import com.store.DTO.SaleDTO;
import com.store.Kafka.KafkaConnect;
import com.store.Model.Client;
import com.store.Model.Order;
import com.store.Model.OrderDetail;
import com.store.Model.Product;
import com.store.Model.Provider;
import com.store.Model.Seller;
import com.store.Model.ShipAddress;

public class POCBusiness {
	private static DBOperator db = new DBOperator();
	
	public static void processSale(SaleDTO sale) throws Exception{
		
		KafkaConnect kafka = new KafkaConnect();
		int orderID = db.saveOrderAndGetInsertedId(new Order(sale.getIdClient(), sale.getIdShippment()));
		ArrayList<OrderDetail> orderDetailsList = new ArrayList<OrderDetail>();
		ShipAddress ship = db.getShipAddressByClientIdAndShipId(sale.getIdClient(), sale.getIdShippment());
		Map<String,List<ProductSoldMessageDTO>> kafkaMessages = new HashMap<>();
		for (int[] productsDTO : sale.getListProducts()) {
			db.getProductProviderShipmmentForMessage(productsDTO[0], ship.getId(), productsDTO[1], orderID, kafkaMessages);
			orderDetailsList.add(new OrderDetail(orderID,productsDTO[0],productsDTO[1]));
		}
		db.saveOrderDetails(orderDetailsList);
		for (Map.Entry<String,List<ProductSoldMessageDTO>> entry : kafkaMessages.entrySet()) {
			kafka.sendProductSoldMessage(entry.getKey(),entry.getValue());
		}
	}

	public static JsonNode getSalesByProviderAndTimeWindow(String cnpj) throws Exception {
		Provider provider = db.getProviderByCNPJ(cnpj);
		KafkaConnect kafka = new KafkaConnect();
		JsonNode productSoldMessage = kafka.getProductSoldMessage(provider.getTopicName_Orders());
		return productSoldMessage;
	}

	public static void processDeliveryUpdate(String cnpj, DeliveryUpdateDTO saleDTO) throws Exception {
		Provider provider = db.getProviderByCNPJ(cnpj);
		Pair<Client, Seller> clientSeller = db.getClientByOrderCode(saleDTO.getOrderCode());
		KafkaConnect kafka = new KafkaConnect();
		kafka.sendDeliveryUpdateMessage(clientSeller, saleDTO, provider);
	}
}