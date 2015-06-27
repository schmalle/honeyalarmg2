CREATE USER 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';
CREATE USER 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmpw';

GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeylarm'@'localhost' IDENTIFIED BY 'honeyalarmpw';

GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeylarmp'@'localhost' IDENTIFIED BY 'honeyalarmppw';

FLUSH PRIVILEGES;
