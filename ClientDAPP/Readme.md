#### **ClientDAPP : Client based application to communicate with the blockchain using MetaMask**

**Description:**

This client DAPP's purpose is to provide a client-side interface for the users who want to verify their PII data. 
The application sends a query to the Smart contract on the Ethereum network, and this query must be sent with the already registered user's account.
For example, if a wallet associated with the PII data of the user and it already exists on the smart contract, then it returns verified data; otherwise, it rejects the data.

Therefore, clientside DAPP does a query or retrieve the data, but it has no permission to store any new data on the smart contract. Or in other words, you could say this application allows any user to query at the smart contract using his registered wallet through MetaMask. 

The client DAPP sends the query to the contract by signing with the user's wallet keys, which gets verifies at the smart contract. It checks if the registered user is intended to get verification status on his provided PII attribute, for instance, name, address, or age.

If his name, age, or address is already registered on the smart contract along with his wallet, then he would see the result as approved; otherwise, a verification status will be displayed. 


### **Who can host this Client DAPP application:**

Any participant or organization who would like to get Pii information from any user, but they would like to verify the PII data from the governance or Authority's owned Smart Contract.  

So the organization can incorporate this DAPP with its system, which allows them to get verified the information because every user must sign and send the query to the smart contract through MetaMask. On return,  we get authenticity if he has entered a correct Pii data or not. But for every attribute attestation, he has to pay 1 ether. 

**Requirement:**

1 - NPM version 5.6.0 or above

2 - Node (Nodejs) version  8.11.3 or above

3 - npm package  webpack 4.39.3

4 - IPFS version 0.4.21

**How to Run** 

Step 0: Go to the the command line and visit the following path (src folder) here we have to get all node modules.

    ClientDAPP/src> 

Step 1: Now write the below command to install webpack node modules 

    npm install webpack webpack-cli webpack-dev-server --save-dev

Step 2: On command line go to the nodejs folder and write following command to run a development server  

    ClientDAPP/src/nodejs> npx webpack-dev-server --https --port 80

 
On a successful compilation it will allow you to access the below url. **(MAKE SURE TO PUT HTTPS)**
 
https://localhost:80/

Step 3: Now run IPFS daemon 

IPFS is used for retrieving the user photos ,therefore, make sure to run ipfs daemon. 


`  > ipfs daemon
`

The nodejs application is using IPFS CDN libraries using 

    <script src="https://unpkg.com/ipfs-http-client@39.0.2/dist/index.min.js" integrity="sha384-DUTAjqwwqxmoFuDozFeVvanWVA8QQBYyGSq4MQOlBxH03rqD4yyaSl43RQHU5E8d" crossorigin="anonymous"></script><!-- loading the human-readable (not minified) version -->

    <script src="https://unpkg.com/ipfs-http-client@39.0.2/dist/index.js" integrity="sha384-SbtgpGuHo4HmMg8ZeX2IrF1c4cDnmBTsW84gipxDCzeFhIZaisgrVQbn3WUQsd0e" crossorigin="anonymous"></script>




**Technical Notes**

1 - If you are using CDN files directly in your client app and you would like to use IPFS functionality then might get error 403 

so it is better before running ipfs daemon use the config command and set the cors domain as this

` > ipfs config --json API.HTTPHeaders.Access-Control-Allow-Origin "[\"https://localhost:80\"]"
`
Here https://localhost:80\ is the DAPP which needs the access to the ipfs API. 

2 - The main DAPP code is written in index.html file. The same html file has the web3 code and all releavent JS code for any transaction using MetaMask. 

Additionally index.html javascipt code has a local contract address and live contract address where all request sent to retrieve the output.

3 - There are two main contracts , 

    a) AuthorityPII Contract ( This is our operational contract for any kind of transaction including adding data and retriving data)
    
    b) AuthorityRegistry Contract ( This is our registry contract, in order to get AuthorityPII contract address we ust call registry contract's function which will give us a version and address)
    
    
 
4 - To change the local truffle or ganache Registry Contract   

        getTruffleOrGanache_RegistryContractAddress() 


or to change the live Rospten Registry Contract address 

        getRosptenENS_RegistryContractAddress() 

5 - The registry contract is already resolved at **"piiauthority.eth"** Ethereum naming service. It will return the Ropsten based ENS contract address.

