CREATE TABLE IF NOT EXISTS `jobs` (
    `job_id` VARCHAR(128) NOT NULL,
    `job_title` VARCHAR(128) NOT NULL,
    `company_name` VARCHAR(128) NOT NULL,
    `url` TEXT,
    `location` VARCHAR(128),
    `salary` VARCHAR(128),
    `remote` VARCHAR(20),
    `description` TEXT,
    `status` VARCHAR(20) NOT NULL,
    `applied_date` DATE,
    `job_board` VARCHAR(128),
    `note` TEXT,
    `board_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`job_id`),
    CONSTRAINT `fk_jobs_boards`
                FOREIGN KEY (`board_id`)
                    REFERENCES `boards` (`board_id`)
                    ON DELETE NO ACTION
                    ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;