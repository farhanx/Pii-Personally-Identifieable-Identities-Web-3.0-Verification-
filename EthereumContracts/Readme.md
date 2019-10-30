To run development evenviornment  
 > truffle develop
 
Note: -  once you run the development environment then you can call other commands like console, migrate, test and js scripts.

To compile this projects
 > Truffle compile

To call all tests on this project  
 > truffle test
 
 To migrate the contracts on the truffle
 > truffle migrate
 
 To reset and re-send all contracts using migrate 
 > truffle migrate --reset

To connect a console on development environment (make sure in one of the terminal you are already running client using truffle develop)
> truffle console

To use JS scripts to call contract function (make sure you are on console)
> var obj = await AuthorizedPII.at(AuthorizedPII.address)

To check if you have the proper contract address in your obj 
> obj.address 

To call a smart contract function with the same obj (where we have to send another address as well as a parameter of the function)
> obj.addTransaction("Farhan","ABCD","32",'0x14d772ba6832e25041c7eb1fa4a687035d68c508') 

To call a smart contract function using any account we want (right after function parameter we can use {From clause} and put address which we want to use as a caller)
> obj.getName('0x14d772ba6832e25041c7eb1fa4a687035d68c508',{from:'0x14d772ba6832e25041c7eb1fa4a687035d68c508'})

To check balance of any account on console 
> web3.eth.getBalance('0x7e948fc94028a9012cfd9e1e939994eaf586ce3c')

If you recieve Error: Returned values aren't valid, did it run Out of Gas?
 Then make sure you are running >truffle develop and then >truffle console (Afterward migrate --reset) and then you can start calling these stuff

**Deploying on Rospten**
To deploy on the ropsten or rinkeybay open 

Step 0:
Make sure you have MetaMask on chrome or your browser. (I am using brave browser)

Step 1: Open the remix , and add your all contract files which you want to deploy.
[https://remix.ethereum.org/][Remix]

[Remix]: https://remix.ethereum.org/

Step 2: Open Metamask and select your account, which you would like to use as an admin address or owner address. (It is a good practise to create your new account on metamask without importing ganache or truffle account).
Additionally save its private key somewhere.

Step 3: Since you are on the test network , therefore, add some money by using Test Faucet option "get Ether" option and then click faucet.
 
Step 4: Go to the remix browser and click the tab of "DEPLOY & RUN TRANSACTIONS"

Step 5: Select "Injected Web3" in the environment.

Step 6: Now click deploy button , it will eventually opens the metamask asking to pay for the gas

Step 7: Once you pay for the gas and confirms the transaction , you will see "creation of mycontract pending.." text in the output bar

Step 8: congrats , now you will see your contract creation transaction on the link given in the output for e.g. https://ropsten.etherscan.io/tx/0xc3458c733472468803fb6a1bdcf0e0e3c3a5ebcfe8c1102c7dadfa43e0503e1d 

Step 9: Browse the link and then select "To" address in the transaction info page. It will take you to your contract address page.

**Using ENS For Registry Contract (Ethereum Naming Service)** 

Since the registry contract has the latest version of AuthorityPII , it is better to use ENS service for the registry contract. 

Therefore a dapp will use ENS name which will point to the AuthorityRegistry , and then AuthorityRegistry will provide the latest version of AuthorityPII contract address.

Step 1: Go to the https://app.ens.domains/

Step 2: Make sure your network is ropsten or mainnet etc (This does not work on truffle or ganache)

Step 3: Search a name anything you like 

Step 4: Select the name if it is available and then select years how long you want to posses this address. e.g. yourname.eth

Step 5: You will be asked for transaction and then in the end you have to click register ( There might be two transaction with gases)

Step 6: On successful transaction, you will see manage button , just click this and then you will see registrant and controller with your name

Step 7: Now click resolver

Step 8: When you click SET you will also find "Use public resolver" make sure to click this (A new address will come this is a public resolver contract address just save it).

Step 9: Now click records plus sign and add address

Step 10: in the address put your smart contract or your own address 

Step 11: Now click "reverse record not set" tab and you will see your ens name and the resolver address. Just save it.

Step 12: Now you will see "Reverse record: Set to yourname.eth" 

Step 13: Lets suppose if you have made your account inside the resolving address then open MetaMask and send some money and in search write the name as blockchainsfalcon.eth and you will see your account popup.

Step 14: Similerly if you try metamask and again click send money and search piiauthority.eth you could even see your contract address with short name as piiauthority.eth.

For some reason these information are not coming on etherscan search, so I think do not rely on that i guess.


