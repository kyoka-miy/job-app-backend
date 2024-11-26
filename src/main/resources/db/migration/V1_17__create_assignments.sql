CREATE TABLE IF NOT EXISTS `assignments` (
    `assignment_id` VARCHAR(128) NOT NULL,
    `title` VARCHAR(128) NOT NULL,
    `deadline_datetime` DATETIME NOT NULL,
    `note` TEXT,
    `completed` BOOLEAN NOT NULL,
    `job_id` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`assignment_id`),
    CONSTRAINT `fk_assignments_jobs`
            FOREIGN KEY (`job_id`)
                REFERENCES `jobs` (`job_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;