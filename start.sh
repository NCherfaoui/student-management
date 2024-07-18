#!/bin/bash

docker-compose up -d

echo "Attente du démarrage des conteneurs MongoDB..."
sleep 30

docker exec mongo1 /bin/bash -c "chmod +x /scripts/rs-init.sh && /scripts/rs-init.sh"

echo "Le système est prêt !"