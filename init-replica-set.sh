#!/bin/bash

echo "Waiting for MongoDB to start..."
sleep 50

echo "Initiating the replica set..."
mongosh  --host mongo2 --eval '
rs.initiate({
  _id: "rs0",
  members: [
    { _id: 0, host: "mongo1:27017" },
    { _id: 1, host: "mongo2:27017" },
    { _id: 2, host: "mongo3:27017" }
  ]
})
'

echo "Replica set initiated."
