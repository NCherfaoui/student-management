#!/bin/bash

docker-compose up -d

echo "Attente du démarrage des conteneurs MongoDB..."
sleep 20

echo "Initialisation du replica set..."



docker exec mongo2 bash -c "/scripts/init-replica-set.sh"
#docker exec -it mongo2  mongosh --eval '
#  rs.initiate({
#   "_id": "rs0",
#    "members":[
#  { "_id": 0, "host": "mongo1:27017" },
#   { "_id": 1, "host": "mongo2:27017" },
#   { "_id": 2, "host": "mongo3:27017" } ]
#   });
#   '
  docker exec -it mongo3 mongosh --eval 'rs.status()'



echo "Le système est prêt !"