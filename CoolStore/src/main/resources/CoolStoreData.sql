INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `cost`) VALUES ('cheese', 2.49);
INSERT INTO `orders` (`customerID`, `itemID`, `itemAmount`, `ref`) VALUES (1, 1, 2, 1);

SELECT * FROM customers;
SELECT * FROM items;
SELECT * FROM orders;
