CREATE TABLE IF NOT EXISTS `boards` (
    `board_id` VARCHAR(128) NOT NULL,
    `created_datetime` DATETIME NOT NULL,
    `name` VARCHAR(128) NOT NULL,
    `account_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`board_id`),
    CONSTRAINT `fk_boards_accounts`
            FOREIGN KEY (`account_id`)
                REFERENCES `accounts` (`account_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;