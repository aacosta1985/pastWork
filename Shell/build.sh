#!/bin/bash

set -e

set -o pipefail

export BUILD_ARGS=''
export BUILD_ENV=''

echo "=======================Cleanup=========================="
rm -rf node_modules
rm -rf dist

echo "=================Installing dependencies================"
npm install

echo "=====================Building app======================="
mkdir dist
cp index.js dist/
cp chart.js dist/
cp package.json dist/

echo "====================Move built app======================"

cp -R node_modules dist
cp -R Dockerfile dist/Dockerfile

echo "====================Directory Structure======================"
ls -la dist/
