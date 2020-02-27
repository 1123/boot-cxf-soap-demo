# Spring Boot Apache CXF SOAP Service to Kafka 


This demo application generates a SOAP Web service from a WSDL web service description file. 
The web service includes a "saveAccount" method, that receives a soap request to create an account and stores the newly created account into Apache Kafka.

This is particularly useful when you want to migrate legacy SOAP based web services to write events to Kafka. 

Pull the source from github with git clone https://github.com/briansjavablog/boot-cxf-soap-demo.git. 
To run the integration test run mvn test. To run as executable JAR run java -jar target/boot-cxf-demo.jar

Note: his requires a Kafka broker listening on `localhost:9092`. The easiest way to achieve this is by downloading Confluent Platform and running `confluent local kafka start`. 
