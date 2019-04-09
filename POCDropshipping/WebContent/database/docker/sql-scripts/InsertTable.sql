INSERT INTO ecommerce.SELLERS (Email, Password, FullName, Country, City, State, Zip, RegistrationDate, Phone, Address, Address2, Active, CPF) VALUES
('test_drop_seller1@gmail.com', 'XXXX', 'Seller 1', 'Brasil', 'Belo Horizonte', 'Minas Gerais', '30000001', CURRENT_TIMESTAMP, '3133333331', 'Rua X1, 1', 'Bloco A', 1, '00000000001'),
('test_drop_seller2@gmail.com', 'XXXX', 'Seller 2', 'Brasil', 'Montes Claros', 'Minas Gerais', '300000002', CURRENT_TIMESTAMP, '3133333332', 'Rua X2, 2', 'Bloco B', 1, '00000000002');

INSERT INTO ecommerce.CLIENTS (SellerID, Email, Password, FullName, CPF, RegistrationDate, Country, Phone, Mobile) VALUES
(NULL, 'test_drop_client1@gmail.com', 'XXXXX', 'Cliente 1', '00000000001', CURRENT_TIMESTAMP, 'Brasil', '30000001', '900000001'),
(1, 'test_drop_client2@gmail.com', 'XXXXX', 'Cliente 2', '00000000002', CURRENT_TIMESTAMP, 'Brasil', '30000002', '900000002'),
(NULL, 'test_drop_client3@gmail.com', 'XXXXX', 'Cliente 3', '00000000003', CURRENT_TIMESTAMP, 'Brasil', '30000003', '900000003'),
(2, 'test_drop_client4@gmail.com', 'XXXXX', 'Cliente 4', '00000000004', CURRENT_TIMESTAMP, 'Brasil', '30000004', '900000004'),
(NULL, 'test_drop_client5@gmail.com', 'XXXXX', 'Cliente 5', '00000000005', CURRENT_TIMESTAMP, 'Brasil', '30000005', '900000005');

INSERT INTO ecommerce.PROVIDERS (Name, City, State, Zip, Email, RegistrationDate, Phone, Fax, Country, Address, Address2, CNPJ, TopicName_Orders, TopicName_Shipping) VALUES
('Provider 1', 'Belo Horizonte', 'Minas Gerais', '30000001', 'test_drop_prov1@gmail.com', CURRENT_TIMESTAMP, '3133333331', '3133333331', 'Brasil', 'Rua X1, 1', '','00000000001','provider1_sale', 'provider1_shipping'),
('Provider 2', 'Belo Horizonte', 'Minas Gerais', '30000002', 'test_drop_prov2@gmail.com', CURRENT_TIMESTAMP, '3133333332', '3133333332', 'Brasil', 'Rua X1, 2', '', '00000000002','provider2_sale', 'provider2_shipping'),
('Provider 3', 'Montes claros', 'Minas Gerais', '30000003', 'test_drop_prov3@gmail.com', CURRENT_TIMESTAMP, '3133333333', '3133333333', 'Brasil', 'Rua X1, 3', '', '00000000003','provider3_sale', 'provider3_shipping'),
('Provider 4', 'Diamantina', 'Minas Gerais', '30000004', 'test_drop_prov4@gmail.com', CURRENT_TIMESTAMP, '3133333334', '3133333334', 'Brasil', 'Rua X1, 4', '', '00000000004','provider4_sale', 'provider4_shipping');

INSERT INTO ecommerce.SHIPADDRESS (ClientID, FullName, City, State, Zip, RegistrationDate, Phone, Country, Address, Address2, Active) VALUES
(1, 'Client 1 - FullName 1', 'Aracaju', 'Sergipe', '30000001', CURRENT_TIMESTAMP, '79000000001', 'Brasil', 'Rua A 15', 'Ap 101', 1),
(1, 'Client 1 - FullName 2', 'Belo Horizonte', 'Minas Gerais', '40000001', CURRENT_TIMESTAMP, '31000000001', 'Brasil', 'Rua B 165', 'Ap 201', 1),
(2, 'Client 2 - FullName 1', 'Belo Horizonte', 'Minas Gerais', '50000001', CURRENT_TIMESTAMP, '31000000003', 'Brasil', 'Rua C 178', '', 1),
(3, 'Client 3 - FullName 1', 'Maceio', 'Alagoas', '60000001', CURRENT_TIMESTAMP, '31000000004', 'Brasil', 'Rua D 187', '', 1),
(4, 'Client 4 - FullName 1', 'Belo Horizonte', 'Minas Gerais', '70000001', CURRENT_TIMESTAMP, '31000000005', 'Brasil', 'Rua E 1569', 'Apto 508', 1);

INSERT INTO ecommerce.PRODUCTS (ProviderID, Code, Name, Price, Weight, Description, ImagePath, UpdateDate, Stock, Active) VALUES
(1, 'A01', 'Product 1', 57.3, 1, 'Product 1 - desc', '/images/category1/1.jpg', CURRENT_TIMESTAMP, 6, 1),
(1, 'A02', 'Product 2', 1000, 0.6, 'Product 2 - desc', '/images/category1/2.jpg', CURRENT_TIMESTAMP, 1, 1),
(2, 'B01', 'Product 3', 80, 0.375, 'Product 3 - desc', '/images/category2/1.jpg', CURRENT_TIMESTAMP, 10, 1),
(3, 'C01', 'Product 4', 508, 0.6, 'Product 4 - desc', '/images/category2/3.jpg', CURRENT_TIMESTAMP, 100, 1),
(3, 'C02', 'Product 5', 50, 0.3, 'Product 5 - desc', '/images/category2/6.jpg', CURRENT_TIMESTAMP, 50, 1),
(1, 'A03', 'Product 6', 620, 10, 'Product 6 - desc', '/images/category3/67.jpg', CURRENT_TIMESTAMP, 20, 1),
(2, 'B02', 'Product 7', 450, 2, 'Product 7 - desc', '/images/category1/89.jpg', CURRENT_TIMESTAMP, 30, 1),
(2, 'B03', 'Product 8', 850, 3, 'Product 8 - desc', '/images/category3/13.jpg', CURRENT_TIMESTAMP, 9, 1),
(2, 'B04', 'Product 9', 630, 5, 'Product 9 - desc', '/images/category1/11.jpg', CURRENT_TIMESTAMP, 3, 1),
(1, 'A04', 'Product 10', 960, 6, 'Product 10 - desc', '/images/category4/16.jpg', CURRENT_TIMESTAMP, 8, 1);

INSERT INTO ecommerce.ORDERS (ClientID, ShipAddressID, `Date`, `Code`) VALUES
(1, 2, CURRENT_TIMESTAMP,'1204042019152020'),
(1, 1, CURRENT_TIMESTAMP,'1104042019162532'),
(2, 3, CURRENT_TIMESTAMP,'2304042019162532'),
(3, 4, CURRENT_TIMESTAMP,'3404042019192632'),
(4, 5, CURRENT_TIMESTAMP,'4504042019123532'),
(4, 5, CURRENT_TIMESTAMP,'3404042019152932');

INSERT INTO ecommerce.ORDER_DETAIL (OrderID, ProductID, Quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 1),
(2, 3, 2),
(3, 3, 1),
(3, 4, 3),
(3, 6, 1),
(4, 5, 1),
(4, 7, 1),
(4, 4, 1),
(4, 10, 2),
(5, 9, 2),
(5, 7, 1),
(6, 8, 1),
(6, 10, 1);