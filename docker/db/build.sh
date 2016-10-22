#!/usr/bin/env bash

docker-machine create dev --driver virtualbox --virtualbox-memory=4096
docker-machine start dev
eval $(docker-machine env dev)
docker build --no-cache --rm=true -t honeyalarmg2-db .