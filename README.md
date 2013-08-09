# Foodeys [![Build Status](http://ci.wombatsoftware.de/job/Foodeys/badge/icon)](http://ci.wombatsoftware.de/job/Foodeys/)

# Build and Run it
* ```git clone https://github.com/JUGCologne/Foodeys.git```

## Glassfish 4
* cd into Glassfish 4 directory
* ```asadmin start-domain start-database```
* cd into „Foodeys/Application“
* ```mvn clean install```
* ```mvn embedded-glassfish:run```
* Checkout your browser at [http://localhost:8080/Foodeys](http://localhost:8080/Foodeys)

## JBoss Wildfly 8
* cd into Wildfly\bin directory
* ```sh standalone.sh```
* cd into „Foodeys/Application“
* ```mvn clean install -Pjbossas-remote-wildfly-8```
* ```mvn jboss-as:deploy -Pjbossas-remote-wildfly-8```
* Checkout your browser at [http://localhost:8080/Foodeys](http://localhost:8080/Foodeys)


# Build environment
The Jenkins server can be reached via [http://ci.wombatsoftware.de/](http://ci.wombatsoftware.de/)
The Jenkins server polls Github for changes every 5 minutes. So be a little patient until you can see you commits building.