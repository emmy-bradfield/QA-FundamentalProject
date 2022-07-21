INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `value`) VALUES ('cheese', 2.49);
INSERT INTO `orderitem` (`customerID`, `itemID`, `itemAmount`, `costunit`) VALUES (1, 1, 2, 2.49);


SELECT * FROM customers;
SELECT * FROM items;
SELECT * FROM orderitem;
SELECT * FROM orders;