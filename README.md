# AWATAR
A Web Application Test Automation Resource. This Project is based on master Thesis 'Testkonzept zur Einführung von End-to-End Tests für SAP Commerce im Kontext von Test-Driven Development" writton at FH Campus02 - IT & Wirtschaftsinformatik 2020/2021. 

This project represents a Framework to create and execute automated tests for web applications  based on Frameworks Cucumber Open and Selenium API. 
It is structured in three sub modules
 - AwatarCore: Webservices which represents the CORE of this project. It contains the HTTP Interface for data exchange and business logic
 - AwatarCreator: Webapplication based on vueJS to create testcases on Behavior Test Driven Approach in an textual form. Further details see (https://cucumber.io/)
 - AwatarRunner: Postman-Collection to execute created testcases with AwatarCreator
 - AwatarResultant: POSTMAN-Collection to retrive the testresults of AtawarRunner in JSON.

##Scope
The Framwork supports functionality to create Testcases via a web application in textual form. This Testcases can be structured in folders and saved as a File to execute via AwatarRunner. The Testexecution can be triggered via a Script which can invluded in Jenkins or other Development Framework. 
Furthermore it includes the possibility to read the test results via an HTTP-Request (REST) in JSON-Format to provide this information in external applications. 


## Setup Requirements
- Java JRE 1.8 or higher (https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
- For Developers:
    - JDK 1.8 or higher ( https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- NPM and Node 
