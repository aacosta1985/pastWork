# Script designed to "clean" a repository from local testing. It was intended to allow more junior developers a faster way to ensure they weren't making any breaking changes by pushing version or file updates on their local machines (restricted to more senior devs)

#!/bin/bash

# set -e

set -o pipefail

echo "=================Pruning================"

npm prune 

echo "=====================Updating dependencies======================="
npm uninstall nodemon -g
npm uninstall expo-random 
npm uninstall react-native-securerandom 
npm uninstall quickchart-js@1.4.2 
npm install quickchart-js@1.0.7 

echo "=======================Cleanup=========================="
rm -rf node_modules
rm -rf dist

echo "=======================Restoring package lock files=========================="

git checkout main~ package-lock.json
git checkout main~ package.json
git checkout main~ dev-start.sh

echo "=================Confirming pre-installed software================"

if ! [ -x "$(command -v npm)" ]; then
  echo 'Error: npm is not installed.' >&2
  exit 1
else 
    echo "npm still exists."
fi

if ! [ -x "$(command -v node -v)" ]; then
  echo 'Error: node is not installed.' >&2
  exit 2
fi 


function version { echo "$@" | awk -F. '{ printf("%d%03d%03d%03d\n", $1,$2,$3,$4); }'; }
if [ $(version $(node -v| cut -d'v' -f 2)) -ge $(version "8.9.4") ]; 
then
    echo "node version is still greater than the minimum." 
else 
    echo "node version should be a minimum of 8.9.4. Currently at " & (node -v| cut -d'v' -f 2)
    exit 3
fi

if ! [ -x "$(command -v ngrok -v)" ]; then
  echo 'Error: ngrok is not installed.' >&2
  exit 4
else 
    echo "ngrok still exists."
fi 