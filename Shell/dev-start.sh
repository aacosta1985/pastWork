# Script was designed to run the setup (ensuring that NGROK was running, this was a necessary pre-requisite for the application)

#!/bin/bash

set -e

set -o pipefail


echo "====================Ensure NGROK is running======================"

if ! lsof -PiTCP -sTCP:LISTEN | grep ngrok
then
    echo "ngrok needs to be running"
    exit
else
    export WEBHOOK_URL="$(curl -s localhost:4040/api/tunnels | jq -r ".tunnels[0].public_url" )"
fi


echo "====================Setup environment parameters======================"

export BOT_TOKEN=""
export SHARED_KEY=""
export test_URL=""
export CLIENT=""
export SERVER="" #default to service running in voyager (either dev or prod)
export ENV="local_dev"
# These are optional and have defaults
# export REQUEST_TIMEOUT_MS= "" #default is 35000 ms
# export PORT= "3000"


echo "====================Run with auto-refresh======================"

npm start
