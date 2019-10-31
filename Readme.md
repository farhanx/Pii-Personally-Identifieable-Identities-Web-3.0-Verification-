## **Personally identifiable information (PII) Verification Platform**

#### **Project Detail**

**Description :** This project demonstrate that how a government or any super authority can utilize the ethereum blockchain for the attestation of citizen's PII data.
In the current world, it is not possible that a 3rd-party website can verify a user's PII data from a trustworthy resource online. Therefore, there is considerable data that lives in several databases, which can be termed as fake or wrong. Simply because, till now, we did not have any facility exist which could be used to verify user data from a governance authority within a few minutes or seconds. Though, there were some attempts where a government entity or authority can provide APIs to selected departments or 3rd-parties for the same purpose.

However, this can be called a bad idea because of the following points: 

a - Entity can choose special departments or selected 3rd parties who could utilize their verification services.  Which means not all can take this attestation benefit.

b - Secondly, a centralized attack on the government or authority's server would be a considerable loss.

c - An authority would not trust on all 3rd party websites to provide their services. So how a user can obtain attestation on any site without going to the old tedious manual paper process.

This project addresses these issues and solves it by simulating the "Authority," "User,"  and " a 3rd Party"'s use cases for PII data attestation.

Now imagine, An authority decided to receive the user's wallet address information and bind it with the authentic user data, for instance, Full Name, Age, and home address. So 
all those citizens who wanted to use this service will send their wallet information along with their data.

**User Stories:**  

1 - An administrator of the authority has access to the authority's smart contract — he logins to the blockchain Application, which allows him to enter the required user's data. 

2 - The administrator only enters a name, age, home address, and photo with the user's wallet address.

3 - Once he publishes this data on the Ethereum's smart contract, the relevant user would able to verify from any 3rd party application.

4 - The administrator has the only right to make this user data entry. Therefore, no other user can submit such data on the smart contract. 

5 - The administrator can also transfer this ownership by himself to any other address.

6 - Now, this data is available to everyone, but this data is not free. This means any 3rd party who would like to attest PII data can enforce the relevant user to pay for the attestation to the authority. Therefore, the 3rd party obtains assurance from the authority regarding the user's full name, age, photo, and address. 

7 - The 3rd party website can be any website where they take formal user information through their form, but they want few attributes to attest from the authority. So if 3rd party needs only user name attestation, then the user has to attest his name on the same form rather than all other PII data.

6 - When the user clicks the "verify button" then MetaMask gets open, and then the user has to pay 1 ether of single PII data verification.

7 - Therefore, the user must choose his relevant account address, which is registered by the authority against his PII data.

8 - If the user chooses the wrong account or someone else account, then PII data gets rejected, and his fees never return. No user can verify someone else data this check and balance make sure to avoid any fraud in the attestation. 

9 - If all data gets verified, then the user can submit his form to the 3rd party who can now, without any hesitation, save the real data. 

10 - Any 3rd party application can use web3/IPFS  plugin in their DAPP for their user verification. They need to incorporate ethereum name service resolution to get Authority address which is piiauthority.eth.

#### ** Technical Detail**

The project is divded into three different folders or sub-projects. 

1 - EthereumContracts

2 - ServersideAPP

3 - ClientDAPP

below are the details of each project.


#### 1 - EthereumContracts  ( Truffle Project )


**Technical Reuirement**

    1 - Truffle version v5.0.8
    
    2 - OS Windows 10 (Or Ubuntu latest version)

**How to Run** 

Step 1: Open the terminal and go to the truffle project 

`  EthereumContract/src/
`
Step 2: Open the truffle session

`  EthereumContract/src/> truffle develop
` 
Step 3: Migrate all the contracts

`  EthereumContract/src/> migrate
` 

Step 4: Test all the contracts

`  EthereumContract/src/> test
` 

**Description:**

This project has the regular truffle project directories inside EthereumContracts/src/

All the contracts are exist inside contract directory , tests are inside test directory and migration scripts are inside migrations directory.

**Smart Contracts**  

There are five contracts inside 

1 - AuthorizedPII : main operational contract (Inherted from SecurityRestrictions)

2 - SecurityRestriction : It restricts the functionality with security patterns (Inherited from Ownable)

3 - Ownable : It has all the ownership admin functionality

4 - Migration : Default migration contract

5 - AuthorizedRegistry : This contract is a registry contract to track and control upgrades. Additionally, To get the latest version of AuthorizedPII 

**Test Cases**

    Test 0 : Get the latest version and address of the contract using registry
    
    Test 1 : Upgrade the contract version and check its value
    
    Test 2 : Contract owner must be able to transfer his ownership to new contract owner or admin
    
    Test 3: Other than admin no one should able to call the restricted AddTransaction function 
    
    Test 4: New PII data can only be set by the current contract admin
    
    Test 5: Verify if previously stored PII data is correct with respect to the registered user address 
    
    Test 6: Verify if no one can query someone else PII data 
    
    Test 7: Check existing balance by the admin function 
    
    Test 8: Withdraw all the collected fees in admin account
    
    Test 9: Contract should only be killed by the owner of the contract
    
    Test 10: No public function should return any output, since contract is killed. 

**Live Contracts**  

Following two contracts are live on the Ropsten network.

AuthorityRegistry Contract :

The Ethereum Naming Service is uses to resolve registry contract "AuthorityRegistry" on the Ropsten Network. 

The ens domain is **piiauthority.eth** which resolves the AuthorityRegistry address on 

https://ropsten.etherscan.io/address/0x74dc431a15d82837ed637f63f5844df68d5d3d00

AuthorityPii Contract:

This is the main contract which records all the data and provide operational services like adding transaction and retrieve transaction.

https://ropsten.etherscan.io/address/0xa3aa160c14fe9ed2978f986ce6be90cb2e5ff3aa


####  2 - ServersideAPP (Authority who would like to register users) 

**Technical Requirement**
    
    1 - Java 1.8.0.211
    
    2 - Web3J 4.5.0 (Java library/Bin)
    
    3 - IPFS v1.2.2 (Java library) 
    
    4 - IPFS Installed version (0.4.21) (Windows OS Supproetd)

    5 - Truffle version v5.0.8
    
    6 - solidity-windows Version: 0.5.11+commit.22be8592.Windows.msvc (Soldiity windows compiler) 
    
    7 - IDEA IntelliJ (Recommended to open all three projects / otherwise you can use your own editors)
    
**How to Run** 

Step 0: Go to the AuthoritySpringApp and in the main function select which network you want to run (By default truffle is selected)

            BlockchainConfig.RunBlockchainType = IdentityEthereumTransaction.ETHEREUM_TRUFFLE;//IdentityEthereumTransaction.ETHEREUM_ROSPOTEN ;//IdentityEthereumTransaction.ETHEREUM_TRUFFLE;//.ETHEREUM_GANACHE;//.ETHEREUM_TRUFFLE;//.ETHEREUM_ROSPOTEN;

Step 1: Go to the resource folder in src/main and empty the contents of these files "smartcontract.properties" "smartcontract_registry.properties"


Step 2: Run the IPFS daemon 

`  > ipfs daemon
`

Step 3: Run Truffle console (For local test) and migrate all contracts.

`  > truffle develop
`
`  > migrate
` 

Step 4: Open IDEA IntelliJ and select ServersideAPP and then press Run on AuthoritySpringApp.

Step 5: Go to the URL : http://localhost:8090/

Step 6: Now you can add the PII Data. 

**Description:**

The server APP is the "Authorty's APP" built in Java Spring technology using Maven depenency manager.   
This project sole purpose is to allow the owner or the administrator to register any users with their Full name, age , 
home address and photo along with the wallet address. 

**Directory Information**

This is a traditional java spring project with the package com.farhan.piiverification. Under this package
two more packages are there. Below are the important folders and files information,

a) blockchain: It has some java interfaces to generalize the common functions of any contract along with the contract folder.
 The contract folder has java generated wrappers that were generated by using solidity-windows solc and web3j batch files. 
 
b) web.home: This folder has all the required web-related folders like config, controller, domains, and services. 
  IdentityService implementation has all the significant logics for adding transactions that are called by the identityController. 
  
c) AuthoritySpringApp: This class is on the root, and it has the primary function from where the spring application is initialized.

**Ropsten Network and Ethereum Name Service**

In the Ropsten network Ethereum Name Service is used to get the live AuthorityContract address from piiauthority.eth. 
Then later application calls the AuthorityRegistry function **getLatestVersion** to get the AuthorityPII live address.
All its code are written by utilizing **web3J** library.
    
** Changes in Smart Contract and Geenrate in Java Wrapper**

If you change anything in the EthereumContract project like changing in any contract, then you can automatically generate the new wrappers for AuthortyPII and AuthorityRegistry. There is a cmd file created for this purpose "contract-wrapper-gen.cmd"

> src/contract-wrapper-gen.cmd      

**Starting new Truffle Console Session**
      
If you want to install new contracts in the fresh ganache or truffle console, then you have to delete the content of the file inside.

> resources/smartcontract.properties 

> resources/smartcontract_registry.properties

These two files store the actively deployed contract addresses, network type, and contract owner information. If you change any other
network and runs it, then it detects and deploys the contract on that network.   


**Network configurations** 

    blockchains.ganache.properties : 

_It contains the private key and account address of the first account with host:port._

    blockchains.truffle.properties : 

_It contains the private key and account address of the first account with host:port_

    blockchains.ropsten.properties : 

_It contains the private key and account address with infura ropsten url._ 

These account information denotes the authority administrator. 

**Running Application with any network**

To run the application with Ethereum Ropsten , Ethereum truffle or Ganache , then 
go to the AuthoritySpringApp class and change the config to to any desired network.

> BlockchainConfig.RunBlockchainType = yournetwork 

Later when you will run the application it will detect the releavent network's property file.


#### 3 - ClientDAPP  ( 3rd Party application integrated with the web3 / Metamask )

**Technical Requirement:**

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


**Description:**

This client DAPP's purpose is to provide a client-side interface for the users who want to verify their PII data. 
The application sends a query to the Smart contract on the Ethereum network, and this query must be sent with the already registered user's account.
For example, if a wallet associated with the PII data of the user and it already exists on the smart contract, then it returns verified data; otherwise, it rejects the data.

Therefore, clientside DAPP does a query or retrieve the data, but it has no permission to store any new data on the smart contract. Or in other words, you could say this application allows any user to query at the smart contract using his registered wallet through MetaMask. 

The client DAPP sends the query to the contract by signing with the user's wallet keys, which gets verifies at the smart contract. It checks if the registered user is intended to get verification status on his provided PII attribute, for instance, name, address, or age.

If his name, age, or address is already registered on the smart contract along with his wallet, then he would see the result as approved; otherwise, a verification status will be displayed. 


**Who can run this DAPP?**

Any participant or organization who would like to get Pii information from any user, but they would like to verify the PII data from the governance or Authority's owned Smart Contract.  

So the organization can incorporate this DAPP with its system, which allows them to get verified the information because every user must sign and send the query to the smart contract through MetaMask. On return,  we get authenticity if he has entered a correct Pii data or not. But for every attribute attestation, he has to pay 1 ether. 


**Technical Notes**


0 - The nodejs application is using IPFS CDN libraries using 

      <script src="https://unpkg.com/ipfs-http-client@39.0.2/dist/index.min.js" integrity="sha384-DUTAjqwwqxmoFuDozFeVvanWVA8QQBYyGSq4MQOlBxH03rqD4yyaSl43RQHU5E8d" crossorigin="anonymous"></script><!-- loading the human-readable (not minified) version -->

      <script src="https://unpkg.com/ipfs-http-client@39.0.2/dist/index.js" integrity="sha384-SbtgpGuHo4HmMg8ZeX2IrF1c4cDnmBTsW84gipxDCzeFhIZaisgrVQbn3WUQsd0e" crossorigin="anonymous"></script>


1 - If you are using CDN files directly in your client app and you would like to use IPFS functionality then might get error 403 

so it is better before running ipfs daemon use the config command and set the cors domain as this

     > ipfs config --json API.HTTPHeaders.Access-Control-Allow-Origin "[\"https://localhost:80\"]"

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


