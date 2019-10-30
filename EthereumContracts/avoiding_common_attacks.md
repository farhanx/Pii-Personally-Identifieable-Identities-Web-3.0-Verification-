This project avoid the following types of frequent attacks
1 - Reentrancy attack
2 - TxOrigin Attack
3 - DoS with (Unexpected) revert  

#### ** 1 - Reentrancy Attack**
Since the scope of the smart contract only gives authority to the owner of the smart contract to execute withdrawal function in AuthorizedPII.sol, but still, all the points have been addressed to avoid any reentrancy attacks. 

Below are the security points. 

a) If fees are available, then allow this function to withdraw otherwise don't.
b) If the caller is owner or admin of the smart contract, then only allow him to withdraw otherwise reject any other account's request who wants to withdraw.
c) The balance record variable gets zero before sending it to the recipient regardless if it is the owner. 
d) The function is avoiding using call function, and it is using a safe function with default limited gas e.g., msg.sender.transfer(balance); 
Therefore, these four points make the withdrawal function protected from Reentrancy attacks.


#### ** 2 - TxOrigin Attack**

All the sensitive functions are using msg.sender instead of tx.origin in their require modifier.  Therefore, the chance of calling the attacker's contract using tx.origin is mitigated.


#### ** 3 - DoS with (Unexpected) revert**

Three primary functions must be called by sending fees in ethers to obtain the information from the contract e.g. getName, getAddress, and getAge. 
But all the fee is being held in a single variable without any subscription-based or tracking-based array. 
Since once the value came in the smart contract, there is no refund policy, or requirement exist. 
However, it does track the information about the user accounts who have submitted total fees till the date, which is only for admin knowledge purposes. 
These three functions are straightforward; they take the receiving values and store them in a single balance variable and returns the required data in the event. 
Therefore, no array is required, which could become a victim through an attacker's contract where attacker could use loops.

