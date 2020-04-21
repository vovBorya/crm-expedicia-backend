ALTER TABLE children
    ADD `city` varchar(100),
    ADD `status` varchar(255),
    ADD `points` VARCHAR(255) -- The points are discount for next expedition
        AFTER `birth_day`;

ALTER TABLE customers
    ADD `city` varchar(100),
    ADD `status` VARCHAR(255),
    ADD `exemptions` VARCHAR(255);

ALTER TABLE deals
    ADD `departure_place` VARCHAR(100),
    ADD `transportation_way` VARCHAR(255),
    ADD `discount` VARCHAR(255)
        AFTER `sleeping_bag`;