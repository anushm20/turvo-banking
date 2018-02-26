# turvo-banking
This repository contains Prototype for Turvo Banking Problem. 
<h2> Technologies</h2>
Java <br/>
Spring Boot <br/>
MySQL - For Database <br/>
Spring Data JPA(with Hibernate) - For ORM <br/>
Rabbit MQ - Messaging Queues <br/>
Swagger - For API documentation

<h2> High Level Flow </h2>
Customer creates a token and it will be placed in a queue
Consumer of the queue takes the token and places it in first counter based on services selected(order is considered.
Operator takes the token and completes the action and places it to another queue for further processing.
Processor takes the token and based on the services selected and completed, it will either place the token in next counter or mark the status as completed.

<h2> Available services : </h2>
1. CRUD operations for creating a Bank (No REST End Points) <br/>
2. CRUD operations for creating a Branch of Bank(No REST End Points)<br/>
3. CRUD operations for creating services in Bank<br/>
4. CRUD operations for Mapping a Bank service to a Branch<br/>
5. CRUD operations for creating a Counter in a Branch<br/>
6. CRUD operations for creating a Customer in a Bank<br/>
7. CRUD operations for creating a Token <br/>
8. Different services for taking actions on a token in a Counter<br/>

<h2>Assumptions : </h2>
1. Only one service is done at a particular counter <br/>
2. Service to Service counters should be provided using the application. Also, for a multi counter service, order of counters should be given correctly using the application<br/>
3. In case of Multi Services , a single token with the list of services will be used across different counters. <br/>
4. Same token will be pushed to different counters. Customer will be moved counter by counter, based on priority he will come up in the next counter <br/>
5. Minimum validations are applied in code. Prototype works for cases which have proper data.<br/>
6. Based on the problem statement, an assumption is made that tokens should be allocated to token counters in advance. <br/>

<h2>Build Instructions : </h2>
This is a simple Spring boot application. 
Download the zip and import the project into Eclipse/STS IDE.
Run the Main class “AbcApplication.java”. Application should be deployed in eclipse using Embedded Tomcat on port 8080

<h2>Database Model</h2>
<img src="https://github.com/anushm20/turvo-banking/blob/turvo_banking_with_queues/abc/images/Turvo-DBModel.png" />
