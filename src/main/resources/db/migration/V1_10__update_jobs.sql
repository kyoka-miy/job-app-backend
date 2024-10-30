ALTER TABLE `jobs`
DROP COLUMN `description`;

ALTER TABLE `jobs`
CHANGE `remote` `work_style` VARCHAR(20);