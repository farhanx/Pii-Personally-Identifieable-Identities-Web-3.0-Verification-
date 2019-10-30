### **Design Pattern Desicions**

In the AuthorizedPII.Sol smart contract following patterns has been used.

1 - Fail early and fail loud

2 - Restricting Access

3 - Mortal

4 - Circuit Breaker

#### ** 1 - Fail early and fail loud**

This pattern is used in AddTransaction function, where it was required to validate the input length. If length is more than the specified number of characters, then the function gets fail. 
 
#### **2 - Restricting Access**

The reason to add this pattern is that the contract must be established and owned by the recognized authority. Therefore, all the administrative policies like adding a new user Pii information, transferring ownership of the contract, and killing the contract all has the ownership restriction.

Other functions like getName , getAddress and getAge are only callable by their registered users. This restriction even does not allow the admin to view anyone else stored data.  Which means the storage facility to store is allowed by the registered authority, but to retrieve the stored data is only allowed to whom it was registered. 

This pattern is essential for the user so they can prove their Pii data on any third-party system. The only thing they would need is to call the getName, getAge, or getAddress function through MetaMask. This will allow the user to sign and send the request from his valid wallet to the smart contract. Additionally, the smart contract will check if a valid registered user has sent a query to get his PII data if yes then this data will be sent back to the calling platform. 

#### **3 - Mortal**

If any kind of anomalies appeared, then the owner of the contract or the admin of the contract is allowed to kill the smart contract. So before killing contract checks if the owner is real and then allowed him to selfdestruct the contract.

#### **4 - Circuit Breaker**

It is vital to stop certain functionalities if any vulnerabilities found in the contract. Therefore, the modifier stop_if_emergency is used in query functions and add transaction function. It helps the administrator to stop the contract for some time and can re-open it when he finds the cure or if he fixes the new contract.
