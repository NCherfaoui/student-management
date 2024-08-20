#!/bin/bash

docker-compose up -d

echo "Attente du démarrage des conteneurs MongoDB..."
sleep 45

echo "Initialisation du replica set..."


docker exec mongo2 bash -c "chmod +x /scripts/rs-init.sh && /scripts/rs-init.sh"

echo "Le système est prêt !"