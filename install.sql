CREATE USER 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';
CREATE USER 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmpw';

CREATE DATABASE honeyalarmdev;
CREATE DATABASE honeyalarmprod;

GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeylarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';
GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeylarmprod'@'localhost' IDENTIFIED BY 'honeyalarmppw';

FLUSH PRIVILEGES;
