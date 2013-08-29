# Foodeys
* Glassfish 4 Build and Test: [![Build Status](http://ci.wombatsoftware.de/job/Foodeys%20-%20Glassfish%204/badge/icon)](http://ci.wombatsoftware.de/job/Foodeys%20-%20Glassfish%204/)
* Glassfish 4 Deploy: [![Build Status](http://ci.wombatsoftware.de/job/Foodeys%20-%20Glassfish%204%20Deployment/badge/icon)](http://ci.wombatsoftware.de/job/Foodeys%20-%20Glassfish%204%20Deployment/)

# Build and Run it
* ```git clone https://github.com/JUGCologne/Foodeys.git```

## Glassfish 4
If you have a Glassfish instance:
* cd into Glassfish 4\bin directory
* ```./asadmin start-domain```
* ```./asadmin start-database```
* cd into „Foodeys/Application“
* ```mvn clean install```
* ```mvn -Dmaven.test.skip=true -pl jsf clean package cargo:redeploy```
 
If you don´t have Glassfish:
* cd into „Foodeys/Application“
* ```mvn -Dmaven.test.skip=true -pl jsf clean package embedded-glassfish:run```

Checkout your browser at [http://localhost:8080/foodeys-jsf](http://localhost:8080/foodeys-jsf)

## JBoss Wildfly 8
* cd into Wildfly\bin directory
* ```sh standalone.sh```
* cd into „Foodeys/Application“
* ```mvn clean install -Pjbossas-remote-wildfly-8```
* ```mvn jboss-as:deploy -Pjbossas-remote-wildfly-8```
* Checkout your browser at [http://localhost:8080/Foodeys](http://localhost:8080/Foodeys)

# Build environment
The Jenkins server can be reached via [http://ci.wombatsoftware.de/](http://ci.wombatsoftware.de/).
The Jenkins server polls Github for changes every 5 minutes. So be a little patient until you can see you commits building.
