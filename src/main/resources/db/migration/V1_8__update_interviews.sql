ALTER TABLE `interviews`
ADD COLUMN `activity_id` VARCHAR(128) NOT NULL;

ALTER TABLE `interviews`
ADD CONSTRAINT `fk_interviews_activities`
            FOREIGN KEY (`activity_id`)
                REFERENCES `activities` (`activity_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION;