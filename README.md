# turvo-banking
This repository contains Prototype for Turvo Banking Problem. 
Tag 1
Branch Push

Build Instructions : 
This is a simple Spring boot application. 
Download the zip and import the project into Eclipse/STS IDE.
Run the Main class “AbcApplication.java”. Application should be deployed in eclipse using Embedded Tomcat on port 8080

Assumptions : 
1. Only one service is done at a particular counter for Non Premium Customers
2. All services can be done at a single service counter for Premium Customers
3. Service to Service counters should be provided using the application. Also, for a multi counter service, order of counters should be given correctly using the application
4. In case of Multi Services , a single token with the list of services will be used across different counters. 
5. If same Token number appears simultaneously at multiple service counters when opted for Multi Services, Operator will use REVISIT status
6. Minimum validations are applied in code. Prototype works for cases which have proper data.
7. Only one machine will be installed if application uses in memory database.
8. If a Multicounter service and Multi services are overlapped for a non premium customer at the same service counter, both of               them can be serviced at same time if needed.

Test Data for different entities to test the prototype :
Bank Services :
      {"serviceName":"Withdrawal"}
      {"serviceName":"Cash Deposit"}
      {"serviceName":"Loan"}
Service Counter :
      {"counterType":"PREMIUM","serviceId":0}
      {"counterType":"REGULAR","serviceId":1}
      {"counterType":"REGULAR","serviceId":2}
      {"counterType":"REGULAR","serviceId":3}
      {"counterType":"REGULAR","serviceId":3}
      {"counterType":"REGULAR","serviceId":3}
Service to Service Counter Mapping in a Branch:
      {"serviceId":1,"serviceCounters":[2]}
      {"serviceId":2,"serviceCounters":[3]}
      {"serviceId":3,"serviceCounters":[4,5,6]}
Customer :
      {"name":"User1","type":"PREMIUM","phoneNumber":123456789,"addresses":[{"addressType":"PERMANENT","addressLine1":"Street1","postalCode":"000000","city":"Hyderabad","state":"TN","country":"India"}]}
      {"name":"User2","type":"PREMIUM","phoneNumber":123456789,"addresses":[{"addressType":"PERMANENT","addressLine1":"Street2","postalCode":"000000","city":"Hyderabad","state":"TN","country":"India"}]}
      {"name":"User3","type":"REGULAR","phoneNumber":123456789,"addresses":[{"addressType":"PERMANENT","addressLine1":"Street3","postalCode":"000000","city":"Hyderabad","state":"TN","country":"India"}]}
      {"name":"User4","type":"REGULAR","phoneNumber":123456789,"addresses":[{"addressType":"PERMANENT","addressLine1":"Street4","postalCode":"000000","city":"Hyderabad","state":"TN","country":"India"}]}
      {"name":"User5","type":"REGULAR","phoneNumber":123456789,"addresses":[{"addressType":"PERMANENT","addressLine1":"Street5","postalCode":"000000","city":"Hyderabad","state":"TN","country":"India"}]}
CustomerToken:
      {"customerId":1,"customerType":"PREMIUM","status":"CREATED","services":[1]}
      {"customerId":2,"customerType":"PREMIUM","status":"CREATED","services":[1,2]}
      {"customerId":3,"customerType":"REGULAR","status":"CREATED","services":[1]}
      {"customerId":4,"customerType":"REGULAR","status":"CREATED","services":[1,2]}
      {"customerId":5,"customerType":"REGULAR","status":"CREATED","services":[3]}



