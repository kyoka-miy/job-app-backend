CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` VARCHAR(128) NOT NULL,
    `register_datetime` DATETIME NOT NULL,
    `mail_address` TEXT NOT NULL,
    `phone_number` VARCHAR(16) NOT NULL,
    `password`     TEXT NOT NULL,
    `name`         TEXT NOT NULL,
    `status`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`customer_id`)
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;