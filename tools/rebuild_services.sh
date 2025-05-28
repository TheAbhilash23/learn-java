#!/usr/bin/env bash

SERVICES="$@"

docker compose -f docker-compose.yml down
docker compose -f docker-compose.yml build
docker compose -f docker-compose.yml up -d $a