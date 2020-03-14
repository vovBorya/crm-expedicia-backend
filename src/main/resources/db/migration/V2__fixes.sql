ALTER TABLE `expeditions`
    CHANGE COLUMN `name_loaction` `name_location`
        VARCHAR(100) NOT NULL;

ALTER TABLE `customer_phone_numbers`
    CHANGE COLUMN `customers_id` `customer_id`
        INT NOT NULL;

ALTER TABLE `children`
    MODIFY `parent_id` INT NULL;