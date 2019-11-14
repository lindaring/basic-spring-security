show databases;

create database springsecurity;

use springsecurity;

CREATE TABLE users (
id INT(4) AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL UNIQUE,
`password` VARCHAR(255) NOT NULL,
`role` VARCHAR(50)
);

select * from users;

insert into users
values (null, 'lindaringsk', '123456', 'ADMIN'),
(null, 'stukzin', '123456', 'USER');