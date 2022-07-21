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
    `value` DOUBLE DEFAULT 0,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE IF NOT EXISTS `orderitem`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `customerID` INT NOT NULL,
    `itemID` INT NOT NULL,
    `itemAmount` INT NOT NULL,
    `costunit` DOUBLE NOT NULL DEFAULT 1,
    `costtotal` DOUBLE NOT NULL DEFAULT (costUnit*itemAmount),
    `order` INT DEFAULT 1,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customerID`) REFERENCES `customers`(`id`),
    FOREIGN KEY (`itemID`) REFERENCES `items`(`id`)
);

DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `orders` (
SELECT o.order AS `id`, o.customerID AS `customerID`, SUM(o.costtotal) AS `total`
FROM orderitem o
GROUP BY o.order
);
