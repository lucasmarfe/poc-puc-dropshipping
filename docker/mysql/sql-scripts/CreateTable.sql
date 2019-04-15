CREATE USER 'java'@'%' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON ecommerce.* TO 'java'@'%';


SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "-03:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ecommerce`
--
-- --------------------------------------------------------

--
-- Table structure `sellers`
--

CREATE TABLE IF NOT EXISTS `SELLERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(500) DEFAULT NULL,  
  `Password` varchar(500) DEFAULT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `Country` varchar(90) DEFAULT NULL,
  `City` varchar(90) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Zip` varchar(12) DEFAULT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Address2` varchar(50) DEFAULT NULL,
  `Active` tinyint(1) DEFAULT '0',
  `CPF` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;
-- --------------------------------------------------------

--
-- Table structure `clients`
--

CREATE TABLE IF NOT EXISTS `CLIENTS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SellerID` int(11) DEFAULT NULL,
  `Email` varchar(500) DEFAULT NULL,
  `Password` varchar(500) DEFAULT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Country` varchar(90) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Mobile` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;

ALTER TABLE `CLIENTS` ADD CONSTRAINT `fk_client_seller` FOREIGN KEY ( `SellerID`) REFERENCES `SELLERS`(`ID`);

-- --------------------------------------------------------

--
-- Table structure `shipaddress`
--

CREATE TABLE IF NOT EXISTS `SHIPADDRESS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClientID` int(11) NOT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `City` varchar(90) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Zip` varchar(12) DEFAULT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Phone` varchar(20) DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Address2` varchar(50) DEFAULT NULL,
  `Active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;

ALTER TABLE `SHIPADDRESS` ADD CONSTRAINT `fk_ship_client` FOREIGN KEY ( `ClientID`) REFERENCES `CLIENTS`(`ID`);
-- --------------------------------------------------------

--
-- Table structure `providers`
--

CREATE TABLE IF NOT EXISTS `PROVIDERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `City` varchar(90) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Zip` varchar(12) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Phone` varchar(30) DEFAULT NULL,
  `Fax` varchar(30) DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Address2` varchar(50) DEFAULT NULL,
  `CNPJ` varchar(50) DEFAULT NULL,
  `TopicName_Orders` varchar(50) DEFAULT NULL,
  `TopicName_Shipping` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure `products`
--

CREATE TABLE IF NOT EXISTS `PRODUCTS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProviderID` int(11) NOT NULL,
  `Code` varchar(50) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` float NOT NULL,
  `Weight` float NOT NULL,
  `Description` varchar(250) NOT NULL,
  `ImagePath` varchar(100),
  `UpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Stock` float DEFAULT NULL,
  `Active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;

ALTER TABLE `PRODUCTS` ADD CONSTRAINT `fk_product_provider` FOREIGN KEY ( `ProviderID`) REFERENCES `PROVIDERS`(`ID`);

-- --------------------------------------------------------

--
-- Table structure `orders`
--

CREATE TABLE IF NOT EXISTS `ORDERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClientID` int(11) NOT NULL,
  `ShipAddressID` int(11) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Code` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci AUTO_INCREMENT=1 ;

ALTER TABLE `ORDERS` ADD CONSTRAINT `fk_order_client` FOREIGN KEY ( `ClientID`) REFERENCES `CLIENTS`(`ID`);
ALTER TABLE `ORDERS` ADD CONSTRAINT `fk_order_ship` FOREIGN KEY ( `ShipAddressID`) REFERENCES `SHIPADDRESS`(`ID`);

--
-- Table structure `ORDER_DETAIL`
--

CREATE TABLE IF NOT EXISTS `ORDER_DETAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

ALTER TABLE `ORDER_DETAIL` ADD CONSTRAINT `fk_orderdetail_order` FOREIGN KEY ( `OrderID`) REFERENCES `ORDERS`(`ID`);
ALTER TABLE `ORDER_DETAIL` ADD CONSTRAINT `fk_orderdetail_product` FOREIGN KEY ( `ProductID`) REFERENCES `PRODUCTS`(`ID`);


CREATE TABLE IF NOT EXISTS `TOKEN` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClientID` int(11) DEFAULT NULL,
  `ProviderID` int(11) DEFAULT NULL,
  `Token` varchar(50) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

ALTER TABLE `TOKEN` ADD CONSTRAINT `fk_token_client` FOREIGN KEY ( `ClientID`) REFERENCES `CLIENTS`(`ID`);

ALTER TABLE `TOKEN` ADD CONSTRAINT `fk_token_provider` FOREIGN KEY ( `ProviderID`) REFERENCES `PROVIDERS`(`ID`);
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
