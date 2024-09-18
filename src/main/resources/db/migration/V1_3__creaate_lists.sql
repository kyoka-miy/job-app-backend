CREATE TABLE IF NOT EXISTS `job_lists` (
    `job_list_id` VARCHAR(128) NOT NULL,
    `active` BOOLEAN NOT NULL,
    PRIMARY KEY (`job_list_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `job_lists` (`job_list_id`, `active`) VALUES
('WISH_LIST', true),
('APPLIED', true),
('INTERVIEW', true),
('OFFER', true),
('REJECTED', true);