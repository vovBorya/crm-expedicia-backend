RENAME TABLE customer_phone_numbers TO customer_contacts;

ALTER TABLE customer_contacts
    ADD `type` varchar(100) NOT NULL
        AFTER `customer_id`;

ALTER TABLE customer_contacts
    CHANGE COLUMN `phone_number` `content` VARCHAR(150) NOT NULL;

ALTER TABLE customers
    DROP COLUMN email_address;