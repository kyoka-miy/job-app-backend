CREATE TABLE IF NOT EXISTS `offers` (
    `offer_id` VARCHAR(128) NOT NULL,
    `received_datetime` DATETIME NOT NULL,
    `deadline_datetime` DATETIME NOT NULL,
    `accepted_datetime` DATETIME,
    `job_id` VARCHAR(128) NOT NULL,
    `activity_id` VARCHAR(128) NOT NULL,
    `board_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`offer_id`),
    CONSTRAINT `fk_offers_jobs`
            FOREIGN KEY (`job_id`)
                REFERENCES `jobs` (`job_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION,
    CONSTRAINT `fk_offers_activities`
                FOREIGN KEY (`activity_id`)
                    REFERENCES `activities` (`activity_id`)
                    ON DELETE NO ACTION
                    ON UPDATE NO ACTION,
    CONSTRAINT `fk_offers_boards`
                    FOREIGN KEY (`board_id`)
                        REFERENCES `boards` (`board_id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;