#!bin/bash


cd java-type-enforcing 
mvn clean install && mvn exec:java -Dexec.mainClass=com.uxjp.App 
