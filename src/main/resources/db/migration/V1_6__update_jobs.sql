ALTER TABLE `jobs`
ADD COLUMN `added_datetime` DATETIME NOT NULL;

ALTER TABLE `jobs`
CHANGE `applied_date` `applied_datetime` DATETIME;