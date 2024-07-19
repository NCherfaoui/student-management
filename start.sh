#!/bin/bash

docker-compose up -d

echo "Attente du démarrage des conteneurs MongoDB..."
#sleep 32
echo "Attente 32 secondes"
for i in {1..32}
do
    echo "$i ..."
    sleep 1
done
echo "Initialisation du replica set..."



docker exec mongo2 bash -c "chmod +x /scripts/rs-init.sh && /scripts/rs-init.sh"

echo "Le système est prêt !"