#!/bin/bash

if [ ! -f /var/lib/mysql/ibdata1 ]; then
    mysql_install_db

    /usr/bin/mysqld_safe &
    sleep 10s

    echo "CREATE USER 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';" | mysql
    echo "CREATE USER 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmpw';" | mysql

    echo "CREATE DATABASE honeyalarmdev;" | mysql
    echo "CREATE DATABASE honeyalarmprod;" | mysql

    echo "GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' WITH GRANT OPTION;" | mysql
    echo "GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' WITH GRANT OPTION;" | mysql

    echo "GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';" | mysql
    echo "GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmppw';" | mysql

    killall mysqld
    sleep 10s
fi