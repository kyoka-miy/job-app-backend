CREATE TABLE IF NOT EXISTS `activities` (
    `activity_id` VARCHAR(128) NOT NULL,
    `name` VARCHAR(128) NOT NULL,
    `activity_datetime` DATETIME NOT NULL,
    `deleted` BOOLEAN NOT NULL,
    `job_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`activity_id`),
    CONSTRAINT `fk_activities_jobs`
            FOREIGN KEY (`job_id`)
                REFERENCES `jobs` (`job_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;