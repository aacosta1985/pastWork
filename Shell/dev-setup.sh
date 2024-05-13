# Script designed to help more junior developers with setup on a repository for local testing. 
# It is intended to allow anyone a faster setup by checking node settings and leverage the correct version of node for the application. 
# Devs could use this script after a clean, to ensure a fresh installation if they had issues with prior installations. This should not start the application, so this is merely setting the environment for the application. 

#!/bin/bash

# set -e

set -o pipefail


echo "=======================Cleanup=========================="
rm -rf node_modules
rm -rf dist

echo "=================Checking pre-installed software================"

if ! [ -x "$(command -v npm)" ]; then
  echo 'Error: npm is not installed.' >&2
  exit 1
else 
    echo "npm exists."
fi

if ! [ -x "$(command -v node -v)" ]; then
  echo 'Error: node is not installed.' >&2
  exit 2
fi 


function version { echo "$@" | awk -F. '{ printf("%d%03d%03d%03d\n", $1,$2,$3,$4); }'; }
if [ $(version $(node -v| cut -d'v' -f 2)) -ge $(version "8.9.4") ]; 
then
    echo "node version is greater than the minimum." 
else 
    echo "node version should be a minimum of 8.9.4. Currently at " & (node -v| cut -d'v' -f 2)
    exit 3
fi

if ! [ -x "$(command -v ngrok -v)" ]; then
  echo 'Error: ngrok is not installed.' >&2
  exit 4
else 
    echo "ngrok exists."
fi 

# if `version -b ">=8.9.4" "$(node --version)"`; then
#   echo "node version is greater than minimum"
# else
#   echo "node needs an upgrade before continuing"
# fi

# if ! [ -x "$(command -v git)" ]; then
#   echo 'Error: git is not installed.' >&2
#   exit 1
# fi

echo "=================Installing dependencies================"

npm prune 
npm install 

echo "=====================Updating dependencies======================="
npm install nodemon -g
npm install expo-random 
npm install react-native-securerandom 
npm install quickchart-js@1.4.2 
npm update
