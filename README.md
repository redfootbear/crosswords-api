![Build](https://github.com/redfootbear/crosswords-api/workflows/Build/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=redfootbear_crosswords-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=redfootbear_crosswords-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=redfootbear_crosswords-api&metric=coverage)](https://sonarcloud.io/dashboard?id=redfootbear_crosswords-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=redfootbear_crosswords-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=redfootbear_crosswords-api)

# CROSSWORDS-API

Crosswords API provides a REST interface that supports the retrieval of crossword puzzles as well the addition of new
words used by the puzzles.

## Usage

run the Neo4j docker-compose located at ./src/test/docker/neo4j/docker-compose.yml navigating to the directory and
executing
`docker-compose up`

You can access your database console through `http://localhost:7474/browser/`

run the project in dev mode by executing

`gradlew quarkusDev`