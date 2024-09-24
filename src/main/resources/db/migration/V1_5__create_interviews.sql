CREATE TABLE IF NOT EXISTS `interviews` (
    `interview_id` VARCHAR(128) NOT NULL,
    `interview_datetime` DATETIME NOT NULL,
    `stage` VARCHAR(128) NOT NULL,
    `type` VARCHAR(128) NOT NULL,
    `note` TEXT,
    `active` BOOLEAN NOT NULL,
    `job_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`interview_id`),
    CONSTRAINT `fk_interviews_jobs`
            FOREIGN KEY (`job_id`)
                REFERENCES `jobs` (`job_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;