#!/usr/bin/env bash

curl -X POST --header "Content-Type:text/xml;charset=UTF-8" -d @./update.xml http://localhost:8080/honeyalarmg2/updateip
