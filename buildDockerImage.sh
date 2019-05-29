#!/usr/bin/env bash

set -eo pipefail

modules=( config-service discovery-service )

for module in "${modules[@]}"; do
    docker build -t "dms/${module}:latest" ${module}
done

docker build -t "dms/config-service:latest" config-service

docker build -t "dms/discovery-service:latest" discovery-service