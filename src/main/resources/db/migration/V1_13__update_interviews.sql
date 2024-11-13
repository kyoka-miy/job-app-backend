ALTER TABLE `interviews`
DROP COLUMN `stage`;

ALTER TABLE `interviews`
DROP COLUMN `type`;

ALTER TABLE `interviews`
CHANGE `active` `completed` BOOLEAN NOT NULL;

ALTER TABLE `interviews`
ADD COLUMN `title` VARCHAR(128) NOT NULL AFTER `interview_id`;
