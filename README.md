# turvo-banking
This repository contains Prototype for Turvo Banking Problem. 

Build Instructions : 
This is a simple Spring boot application. 
Download the zip and import the project into Eclipse/STS IDE.
Run the Main class “AbcApplication.java”. Application should be deployed in eclipse using Embedded Tomcat on port 8080

Assumptions : 
1. Only one service is done at a particular counter 
2. Service to Service counters should be provided using the application. Also, for a multi counter service, order of counters should be given correctly using the application
3. In case of Multi Services , a single token with the list of services will be used across different counters. 
4. Same token will be pushed to different counters. Customer will be moved counter by counter, based on priority he will come up in the next counter 
5. Minimum validations are applied in code. Prototype works for cases which have proper data.
6. Based on the problem statement, an assumption is made that tokens should be allocated to token counters in advance. 



