#!/usr/bin/env bash

if [ ! -f /var/lib/mysql/ibdata1 ]; then
    mysqld --initialize --datadir=/datadb

service mysql start

   # mysql -e "GRANT ALL ON *.* TO root@'%' IDENTIFIED BY '' WITH GRANT OPTION;"

#    echo "nach DB"
#
#    /usr/bin/mysqld_safe &
#    sleep 10s
#
    mysql -u root -e "CREATE USER 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';"
    mysql -u root -e "CREATE USER 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmpw';"

    mysql -u root -e "CREATE DATABASE honeyalarmdev;"
    mysql -u root -e "CREATE DATABASE honeyalarmprod;"
    mysql -u root -e "GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' WITH GRANT OPTION;"
    mysql -u root -e "GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' WITH GRANT OPTION;"



#    echo "GRANT ALL PRIVILEGES ON honeyalarmdev.* TO 'honeyalarmdev'@'localhost' IDENTIFIED BY 'honeyalarmpw';" | mysql
#    echo "GRANT ALL PRIVILEGES ON honeyalarmprod.* TO 'honeyalarmprod'@'localhost' IDENTIFIED BY 'honeyalarmppw';" | mysql


fi
