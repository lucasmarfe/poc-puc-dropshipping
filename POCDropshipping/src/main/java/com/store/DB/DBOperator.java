package com.store.DB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.javatuples.Pair;
import org.javatuples.Tuple;

import com.store.DTO.ProductSoldMessageDTO;
import com.store.Model.Client;
import com.store.Model.Order;
import com.store.Model.OrderDetail;
import com.store.Model.Product;
import com.store.Model.Provider;
import com.store.Model.Seller;
import com.store.Model.ShipAddress;
import com.store.Util.HibernateUtil;

public class DBOperator {
	public Client getClientById(Integer client_id) {
        Session session = null;
        Client client = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            client =  (Client) session.get(Client.class,
                    client_id);
            Hibernate.initialize(client);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return client;
    }
	
	public ShipAddress getShipAddressByClientIdAndShipId(Integer clientId, Integer shipId) {
        Session session = null;
        ShipAddress shipAddress = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ShipAddress.class);
            shipAddress = (ShipAddress) criteria.add(Restrictions.eq("clientID",clientId)).add(Restrictions.eq("id",shipId)).uniqueResult();
            Hibernate.initialize(shipAddress);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return shipAddress;
    }

	public Product getProductById(int prodId) {
		Session session = null;
        Product product = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            product =  (Product) session.get(Product.class,
            		prodId);
            Hibernate.initialize(product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return product;
	}
	
	public Order getOrderById(String orderId) {
		Session session = null;
		Order order = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            order =  (Order) session.get(Order.class,
            		orderId);
            Hibernate.initialize(order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return order;
	}
	
	public Integer saveOrderAndGetInsertedId(Order order) {
		Session session = null;
		int id = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(order);
            session.getTransaction().commit();
            //session.flush();
            //session.update(order);
            /*session.persist(order);
            session.flush();
            return order.getId();*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return order.getId();
	}

	public void saveOrderDetails(ArrayList<OrderDetail> orderDetailsList) {
		Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	session.setFlushMode(FlushMode.COMMIT);
        	for(OrderDetail od : orderDetailsList) {
        		session.getTransaction().begin();
        		session.save(od);
        		session.getTransaction().commit();
        		session.evict(od);
        	}
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
	}
	
	public void getProductProviderShipmmentForMessage(int productId, int shipAddressId, int quantity, int orderID, Map<String, List<ProductSoldMessageDTO>> kafkaMessages) {
		Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	Object[] messageRaw = (Object[]) session.createSQLQuery("SELECT PRODUCTS.Code as ProdCode, PRODUCTS.Weight, PROVIDERS.Name, PROVIDERS.TopicName_Orders, SHIPADDRESS.FullName, SHIPADDRESS.City, " + 
					"SHIPADDRESS.State, SHIPADDRESS.Zip, SHIPADDRESS.Phone, SHIPADDRESS.Country, SHIPADDRESS.Address, SHIPADDRESS.Address2, ORDERS.Code as OrderCode, ORDERS.Date " + 
					"FROM SHIPADDRESS, ORDERS, PRODUCTS JOIN PROVIDERS on PRODUCTS.ProviderID = PROVIDERS.ID " + 
					"WHERE PRODUCTS.ID = " + productId + " AND SHIPADDRESS.id = " + shipAddressId + " AND ORDERS.ID = " + orderID).uniqueResult();
        	ProductSoldMessageDTO prodSold = new ProductSoldMessageDTO((String)messageRaw[0], (String) messageRaw[12],new Date(((Timestamp) messageRaw[13]).getTime()),  quantity, (Float) messageRaw[1],  (String) messageRaw[4], (String) messageRaw[5], (String) messageRaw[6], (String) messageRaw[7], (String) messageRaw[8], (String) messageRaw[9], (String) messageRaw[10], (String) messageRaw[11]);
        	if(kafkaMessages.containsKey((String)messageRaw[3]))
        	{
        		List<ProductSoldMessageDTO> msgList = kafkaMessages.get((String)messageRaw[3]);
        		msgList.add(prodSold);
        		kafkaMessages.put((String)messageRaw[3], msgList);
        	}
        	else
        	{
        		kafkaMessages.put((String)messageRaw[3], new ArrayList<ProductSoldMessageDTO>() {{add(prodSold);}});
        	}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
	}

	public Provider getProviderByCNPJ(String cnpj) {
		Session session = null;
		Provider provider = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Provider.class);
            provider = (Provider) criteria.add(Restrictions.eq("CNPJ",cnpj)).uniqueResult();
            Hibernate.initialize(provider);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return provider;
	}

	public Pair<Client, Seller> getClientByOrderCode(String orderCode) throws Exception {
		Session session = null;
        Client client = null;
        Order order = null;
        Seller seller = null;
        Pair<Client,Seller> pair = null; 
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            order = (Order) criteria.add(Restrictions.eq("code",orderCode)).uniqueResult();
            Hibernate.initialize(order);
            
            client =  (Client) session.get(Client.class,
            		order.getClientID());
            Hibernate.initialize(client);
            
            if(client.getSellerID() != null ) {
	            seller =  (Seller) session.get(Seller.class,
	            		client.getSellerID());
	            Hibernate.initialize(seller);
            }
            pair = new Pair<Client,Seller>(client,seller);
            
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return pair;
	}
}
