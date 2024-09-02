CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` VARCHAR(128) NOT NULL,
    `registered_datetime` DATETIME NOT NULL,
    `email` VARCHAR(128) NOT NULL UNIQUE,
    `password` VARCHAR(128) NOT NULL,
    `name` VARCHAR(128) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`account_id`)
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;