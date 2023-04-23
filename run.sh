#!/bin/bash

# This script is used to run the java program

# Compile
mvn clean install;

# Run
java -cp target/gol-1.0-SNAPSHOT.jar com.thunbergolle.App;