CREATE TABLE IF NOT EXISTS `interview_tags` (
    `interview_id` VARCHAR(128) NOT NULL,
    `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`interview_id`, `name`),
    CONSTRAINT `fk_interview_tags_interviews`
            FOREIGN KEY (`interview_id`)
                REFERENCES `interviews` (`interview_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
)  ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;
