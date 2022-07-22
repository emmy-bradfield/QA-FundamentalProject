DROP DATABASE IF EXISTS CoolStore;
CREATE DATABASE IF NOT EXISTS CoolStore;
USE CoolStore;

DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `items`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `cost` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `orders`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `customerID` INT NOT NULL,
    `itemID` INT NOT NULL,
    `itemAmount` INT NOT NULL,
    `ref` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customerID`) REFERENCES `customers`(`id`),
    FOREIGN KEY (`itemID`) REFERENCES `items`(`id`)
);

CREATE VIEW `Calculator` 
AS
SELECT o.ref AS `ref`, (o.itemAmount*i.cost) AS `total`
FROM orders o
JOIN items i ON o.itemID=i.id
GROUP BY o.ref;
;

