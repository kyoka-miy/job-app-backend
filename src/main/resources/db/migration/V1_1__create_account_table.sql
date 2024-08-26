CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` INT AUTO_INCREMENT NOT NULL,
    `registered_datetime` DATETIME NOT NULL,
    `email` TEXT NOT NULL,
    `password`     TEXT NOT NULL,
    `name`         TEXT NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`account_id`)
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;