#!/usr/bin/env bash


docker compose -f docker-compose.yml down mongodb
docker compose -f docker-compose.yml up -d --build mongodb
