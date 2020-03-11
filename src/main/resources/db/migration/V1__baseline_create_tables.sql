CREATE TABLE `managers`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `last_name`     varchar(40) NOT NULL,
    `first_name`    varchar(40) NOT NULL,
    `patronymic`    varchar(40),
    `birth_day`     DATE        NOT NULL,
    `phone_number`  varchar(14) NOT NULL,
    `salary`        INT         NOT NULL,
    `email_address` varchar(70) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `customers`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `last_name`     varchar(40) NOT NULL,
    `first_name`    varchar(40) NOT NULL,
    `patronymic`    varchar(40),
    `email_address` varchar(70),
    PRIMARY KEY (`id`)
);

CREATE TABLE `children`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `last_name`  varchar(40) NOT NULL,
    `first_name` varchar(40) NOT NULL,
    `patronymic` varchar(40),
    `birth_day`  DATE        NOT NULL,
    `parent_id`  INT         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `deals`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `manager_id`    INT          NOT NULL,
    `customer_id`   INT          NOT NULL,
    `status`        varchar(255) NOT NULL,
    `child_id`      INT          NOT NULL,
    `sum`           INT          NOT NULL,
    `sleeping_bag`  BOOLEAN,
    `comment`       varchar(255),
    `expedition_id` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `payments`
(
    `id`      INT       NOT NULL AUTO_INCREMENT,
    `deal_id` INT       NOT NULL,
    `paid_at` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `expeditions`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `name_loaction` varchar(100) NOT NULL,
    `abbreviation`  varchar(7)   NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `customer_phone_numbers`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `customers_id` INT         NOT NULL,
    `phone_number` varchar(14) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `children`
    ADD CONSTRAINT `children_fk0` FOREIGN KEY (`parent_id`) REFERENCES `customers` (`id`);

ALTER TABLE `deals`
    ADD CONSTRAINT `deals_fk0` FOREIGN KEY (`manager_id`) REFERENCES `managers` (`id`);

ALTER TABLE `deals`
    ADD CONSTRAINT `deals_fk1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

ALTER TABLE `deals`
    ADD CONSTRAINT `deals_fk2` FOREIGN KEY (`child_id`) REFERENCES `children` (`id`);

ALTER TABLE `deals`
    ADD CONSTRAINT `deals_fk3` FOREIGN KEY (`expedition_id`) REFERENCES `expeditions` (`id`);

ALTER TABLE `payments`
    ADD CONSTRAINT `payments_fk0` FOREIGN KEY (`deal_id`) REFERENCES `deals` (`id`);

ALTER TABLE `customer_phone_numbers`
    ADD CONSTRAINT `customer_phone_numbers_fk0` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`);
