CREATE TABLE `profiles`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `user_name`     varchar(40) NOT NULL,
    `password`      varchar(300) NOT NULL,
    `roles`         varchar(300) NOT NULL,
    PRIMARY KEY (`id`)
);