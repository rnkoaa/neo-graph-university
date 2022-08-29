## Delete a node by name

https://github.com/neo4j/neo4j-documentation/blob/4.4/embedded-examples/src/main/java/org/neo4j/examples/socnet/Person.java
https://www.hascode.com/2016/07/object-graph-mapping-by-example-with-neo4j-ogm-and-java/

```shell
#!/bin/sh

# Reference
# https://medium.com/swlh/deployment-of-neo4j-docker-container-with-apoc-and-graph-algorithms-plugins-bf48226928f4

docker rm -f neo4j

docker run -itd \
 -p 7474:7474 -p 7687:7687 \
  --volume=$(pwd)/data:/data \
  --volume=$(pwd)/logs:/logs \
  --volume=$(pwd)/conf:/conf \
  --volume=$(pwd)/plugins:/plugins \
  --volume=$(pwd)/locations:/var/lib/neo4j/locations \
  --name=neo4j \
  neo4j:4.4.10 

```

```lua
-- https://neo4j.com/docs/cypher-manual/current/clauses/delete/
MATCH (n:Department {name: 'Chemistry'})
DELETE n
```