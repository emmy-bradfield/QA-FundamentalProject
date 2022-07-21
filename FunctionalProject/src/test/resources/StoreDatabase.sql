CREATE DATABASE IF NOT EXISTS storeDB;
USE storeDB;

DROP VIEW IF EXISTS Invoices;
DROP VIEW IF EXISTS Subtotal;
DROP VIEW IF EXISTS OrderView;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Items;
DROP TABLE IF EXISTS Customers;

CREATE TABLE IF NOT EXISTS Customers (
customerID INT NOT NULL AUTO_INCREMENT,
customerForename VARCHAR(20) NOT NULL,
customerSurname VARCHAR(30) NOT NULL,
PRIMARY KEY (customerID)
);

CREATE TABLE IF NOT EXISTS Items (
itemID INT NOT NULL AUTO_INCREMENT,
itemName VARCHAR(40) NOT NULL,
itemCost DOUBLE NOT NULL,
PRIMARY KEY (itemID)
);

CREATE TABLE IF NOT EXISTS Orders (
orderID INT NOT NULL AUTO_INCREMENT,
purchaseID INT NOT NULL,
orderDate DATE NOT NULL,
fk_customerID INT NOT NULL,
fk_itemID INT NOT NULL,
itemQuantity INT NOT NULL,
PRIMARY KEY (orderID),
FOREIGN KEY (fk_customerID) REFERENCES Customers(customerID),
FOREIGN KEY (fk_itemID) REFERENCES Items(itemID)
);

CREATE VIEW OrderView
AS
SELECT o.orderID AS orderID_itemized, o.purchaseID AS invoiceID, o.orderDate AS orderDate, i.itemName AS itemName, i.itemCost AS itemCost, o.itemQuantity AS itemQuantity
FROM Orders o
JOIN Items i ON i.itemID=o.fk_itemID
;


CREATE VIEW Subtotal
AS
SELECT o.purchaseID, o.orderDate AS orderDate, o.fk_customerID, (i.itemCost*o.itemQuantity) AS subtotal
FROM Orders o
JOIN Items i ON o.fk_itemID=i.itemID
;

CREATE VIEW Invoices
AS
SELECT s.purchaseID AS 'OrderID',
		c.customerForename AS 'Forename', 
        c.customerSurname AS 'Surname', 
        s.orderDate AS 'OrderDate', 
        SUM(s.subtotal) AS 'OrderTotal'
FROM Subtotal s
JOIN Customers c 
	ON s.fk_customerID=c.customerID
GROUP BY s.purchaseID
; 