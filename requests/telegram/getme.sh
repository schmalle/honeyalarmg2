#!/usr/bin/env bash

curl -s -X POST https://api.telegram.org/bot$1/getMe
curl -s -X POST https://api.telegram.org/bot$1/getUpdates
