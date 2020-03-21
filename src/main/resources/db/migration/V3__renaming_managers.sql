RENAME TABLE managers TO employees;

ALTER TABLE `deals`
    CHANGE COLUMN `manager_id` `employee_id` INT NULL;