#!/usr/bin/env bash

curl -X POST --header "Content-Type:text/xml;charset=UTF-8" -d @./update.xml http://127.0.0.1:8080/updateip
