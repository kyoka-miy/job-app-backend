ALTER TABLE `assignments`
ADD COLUMN `activity_id` VARCHAR(128) NOT NULL;

ALTER TABLE `assignments`
ADD CONSTRAINT `fk_assignments_activities`
            FOREIGN KEY (`activity_id`)
                REFERENCES `activities` (`activity_id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION;