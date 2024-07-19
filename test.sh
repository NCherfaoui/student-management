#!/bin/bash

# Créer un réseau Docker
docker network create mongo-network

# Lancer les conteneurs MongoDB
docker run -d --name mongo1 --net mongo-network mongo:latest --replSet rs0
docker run -d --name mongo2 --net mongo-network mongo:latest --replSet rs0
docker run -d --name mongo3 --net mongo-network mongo:latest --replSet rs0

# Attendre que les conteneurs soient prêts
sleep 10

# Initialiser le replica set
docker exec -it mongo1 mongosh --eval '
rs.initiate({
  _id: "rs0",
  members: [
    { _id: 0, host: "mongo1:27017" },
    { _id: 1, host: "mongo2:27017" },
    { _id: 2, host: "mongo3:27017" }
  ]
})
'

# Vérifier le statut du replica set
docker exec -it mongo1 mongosh --eval 'rs.status()'
