# RewardPoints

Open the Postman for getting the Response.

Customers has id from 100 till 104.


Endpoints:

Method: GET

1. Get all customers record with transaction details and Reward points
 http://localhost:8080/customers

2. Get individual customer record with transaction details and Reward points
 http://localhost:8080/customers/{id}
   eg:  http://localhost:8080/customers/100

3. Get all customers Reward points per month and total Reward Points
 http://localhost:8080/customers/rewardpoints

4. Get individual customer Reward points per month and total Reward Points
 http://localhost:8080/customers/rewardpoints/{id}
   eg:  http://localhost:8080/customers/rewardpoints/100

