#!/usr/bin/env bash

echo "Get my own data:"
curl -s -X POST https://api.telegram.org/bot$1/getMe
echo ""
echo "Get my updates"
curl -s -X POST https://api.telegram.org/bot$1/getUpdates
echo ""
