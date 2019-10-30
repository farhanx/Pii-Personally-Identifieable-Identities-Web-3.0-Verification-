var AuthorizedPII1 = artifacts.require('AuthorizedPII')
var AuthorizedRegistry1 = artifacts.require('AuthorizedRegistry')


contract('AuthorizedPII', async(accounts) => {


    it('Test 10: No public function should return any output, since contract is killed.  ',async()=> {
            const user = accounts[4];

            // get the deployed contract instance
            contractInstance = await AuthorizedPII1.deployed()
            stillReturningValues = false;
            name_rec = "";

            const name = "Farhan";

            try {
                const fees = "1"; //web3.utils.toHex(1);
                let tx1 = await contractInstance.getName(user,{from:user,value:fees}) ;

                if(tx1.logs[0].event) name_rec = tx1.logs[0].args.name;

                if(name_rec===name) stillReturningValues = true;

                console.log("REC : "+name_rec);
            }
            catch(Exception)
            {
                stillReturningValues = false;
            }

            assert.equal(stillReturningValues,false, " Self destructed contract should not return any value")
     },
    it('Test 9: Contract should only be killed by the owner of the contract ',async()=> {

            const kill_contract_caller = accounts[2]; //for test change only this part

            const real_contract_admin = accounts[2];
            contractInstance = await AuthorizedPII1.deployed()

            let ownerCalled = false;
            try {
                let result1 = await contractInstance.kill({from: kill_contract_caller});
                ownerCalled = true;
            }
            catch(Exception)
            {
                ownerCalled = false;

            }

            assert.equal(kill_contract_caller,real_contract_admin, " Kill function can only be called by the owner of the contract")

        },
        it('Test 8: Withdraw all the collected fees in admin account',async()=> {

                const new_contract_admin = accounts[2];

                // get the deployed contract instance
                contractInstance = await AuthorizedPII1.deployed()
                let result1 = await contractInstance.withdraw({from:new_contract_admin});
                let amount =0;
                let balanceReceieveBy = "";
                let balanceinContract = 0;

                if(result1.logs[0].event)
                {
                    amount = result1.logs[0].args.amount;
                    receieveBy = result1.logs[0].args.to;
                }

                // get the current balance inside the smart contract
                balanceinContract = await web3.eth.getBalance(AuthorizedPII1.address);

                //console.log("amount  "+amount+ " add "+balanceinContract);

                assert.equal(3,amount, " total balance supposed to be equal to the number of valid calls")
                assert.equal(new_contract_admin,receieveBy, " The admin user must be able to withdraw amount")
                assert.equal(0,balanceinContract, " The balance in the contract should be now 0 since withdrawl is called")

            },
        it('Test 7: Check existing balance by the admin function ',async()=> {

                const new_contract_admin = accounts[2];

                // get the deployed contract instance
                contractInstance = await AuthorizedPII1.deployed()

                // get the current total fees we have collected
                let result1 = await contractInstance.getTotalPaymentRecieve({from:new_contract_admin});

                // get the current balance inside the smart contract address
                balanceinContract = await web3.eth.getBalance(AuthorizedPII1.address);

                assert.equal(3,result1, " total balance supposed to be equal to the number of valid calls and recorded by the funcion")
                assert.equal(3,balanceinContract, " total balance supposed to be equal to the number of valid calls and should be exist inside the smart contract address")

         },
         it('Test 6: Verify if no one can query someone else PII data ',async()=> {

            const wrong_user = accounts[0];
            const registered_user = accounts[4];
            const fees = "1"; //web3.utils.toHex(1);

           // get the deployed contract instance
            contractInstance = await AuthorizedPII1.deployed()

            const name = "Farhan";
            const address = "Riyadh,KSA";
            const age = "35";
            const ipfs_image_cid = "QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB";



            let wrongCallerForName = false;
            let wrongCallerForAddress = false;
            let wrongCallerForAge = false;
            let wrongCallerForPhoto = false;

            try {
                let result1 = await contractInstance.getPhoto(registered_user,{from:wrong_user});

            }
            catch(exception)
            {
                wrongCallerForPhoto = true;
            }

            try {
                let result1 = await contractInstance.getName(registered_user,{from:wrong_user,value:fees});
                if(result1.logs[0].event) name_rec = result1.logs[0].args.name;

                if(name_rec===name) wrongCallerForName = false;
                else wrongCallerForName = true;
            }
            catch(exception)
            {
                wrongCallerForName = true;
            }

            try {
                let result1 = await contractInstance.getAddress(user,{from:user,value:fees});
                if(result1.logs[0].event) address_rec = result1.logs[0].args.homeaddress;

                if(address_rec===address) wrongCallerForAddress = false;
                else wrongCallerForAddress = true;
            }
            catch(exception)
            {
                wrongCallerForAddress = true;
            }

            try {
                let result1 = await contractInstance.getAge(user,{from:user,value:fees});
                if(result1.logs[0].event) age_rec = result1.logs[0].args.age;

                if(age_rec===age) wrongCallerForAge = false;
                else wrongCallerForAge = true;
            }
            catch(exception)
            {
                wrongCallerForAge = true;
            }


            assert.equal(wrongCallerForName,true, " Calling someone else Name data using wrong account, the account must be registered with this Name data")
            assert.equal(wrongCallerForAddress,true, " Calling someone else Address data using wrong account, the account must be registered with this Address data")
            assert.equal(wrongCallerForAge,true, " Calling someone else age data using wrong account, the account must be registered with this age data")
            assert.equal(wrongCallerForPhoto,true, " Calling someone else Photo data using wrong account, the account must be registered with this Photo data")

     },
    it('Test 5: Verify if previously stored PII data is correct with respect to the registered user address ',async()=> {

            const user = accounts[4];

            // get the deployed contract instance
            contractInstance = await AuthorizedPII1.deployed()

            const name = "Farhan";
            let name_rec = "";
            const address = "Riyadh,KSA";
            let address_rec =""
            const age = "35";
            let age_rec = "";
            const ipfs_image_cid = "QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB";

            const fees = "1"; //web3.utils.toHex(1);

            let tx1 = await contractInstance.getName(user,{from:user,value:fees}) ;
            let tx2 = await contractInstance.getAddress(user,{from:user,value:fees});
            let tx3 = await contractInstance.getAge(user,{from:user,value:fees});
            let cid_rec = await contractInstance.getPhoto(user,{from:user});

            // console.log(JSON.stringify(tx1));
            // console.log(JSON.stringify(tx2));
            // console.log(JSON.stringify(tx3));

            //Important: Since payable functions does not return values therefore we are checking events
            if(tx1.logs[0].event) name_rec = tx1.logs[0].args.name;
            if(tx2.logs[0].event) address_rec = tx2.logs[0].args.homeaddress;
            if(tx3.logs[0].event) age_rec = tx3.logs[0].args.age;

            assert.equal(ipfs_image_cid,cid_rec, " IPFS Image CID must be same as it was stored before")
            assert.equal(name,name_rec, " Name which was stored by the admin must be equal to the expected value")
            assert.equal(address,address_rec, " Address which was stored by the admin must be equal to the expected value")
            assert.equal(age,age_rec, " Age which was stored by the admin must be equal to the expected value")
        },
    // Test 3
    it('Test 4: New PII data can only be set by the current contract admin',async()=>
    {
        const new_contract_admin = accounts[2];

        // get the deployed contract instance
        contractInstance = await AuthorizedPII1.deployed()

        const name = "Farhan";
        const address = "Riyadh,KSA";
        const age = "35";
        const ipfs_image_cid = "QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB";
        const wallet = accounts[4];

        let result = await contractInstance.addTransaction(name,address,age,ipfs_image_cid,wallet,{from:new_contract_admin});

       // console.log("New Admin is - "+ JSON.stringify(result)); //to dump jason string

        assert.equal(result.logs.length,1, " Atleast one event must be submitted by the transaction call")
        assert.equal(result.receipt.from.toLowerCase(),new_contract_admin.toLowerCase(), " The caller supposed to be the admin only")
    },
    // Test 2
    it('Test 3: Other than admin no one should able to call the restricted AddTransaction function ',async()=> {

            //lets add a wrong admin owner
            const non_admin_caller = accounts[1];

            // get the deployed contract instance
            contractInstance = await AuthorizedPII1.deployed()

            const name = "Jason Bourne";
            const address = "California, USA";
            const age = "25";
            const wallet = accounts[4];

           // let result = await ;
            let callerIsWrongOwner = false;
            try {
                await contractInstance.addTransaction(name, address, age, wallet, {from: non_admin_caller})
                callerIsWrongOwner = false;
            }
            catch(exception)
            {
                callerIsWrongOwner = true;
            }

            assert.equal(callerIsWrongOwner,true," Only an admin can call the add transaction function")

        },
    // Test 1
    it('Test 2 : Contract owner must be able to transfer his ownership to new contract owner or admin',async()=>
    {
        const new_contract_admin = accounts[2];

        // get the deployed contract which was deployed with ownership of accounts[0]
        contractInstance = await AuthorizedPII1.deployed()

        let result = await contractInstance.getOwner();

        // console.log("Current Admin is "+ result + " Contract Address is" + contractInstance.address);

        await contractInstance.transferOwnership(new_contract_admin);

         result = await contractInstance.getOwner();

       // console.log("New Admin is"+ result);

       assert.equal(result,new_contract_admin, " The owner address must be replaced with the new admin address this should happen after transferOwnership ")
    },
    it('Test 1 : Upgrade the contract version and check its value ',async()=>
    {

        contractInstance = await AuthorizedPII1.deployed();
        registryInstance = await AuthorizedRegistry1.deployed();

        let version = await registryInstance.addLatestVersion("VanillaCake",contractInstance.address);

        let result = await registryInstance.getLatestVersion();

        assert.equal(result.version_number,2, " After addition of a new version the version number must be increased ")
        assert.equal(result.contract_Address,contractInstance.address, " Registry must have the latest contract address of the AuthorizedPII contract ")
    },
    it('Test 0 : Get the latest version and address of the contract using registry ',async()=>
    {

        contractInstance = await AuthorizedPII1.deployed();
        registryInstance = await AuthorizedRegistry1.deployed();

        let result = await registryInstance.getLatestVersion();


        assert.equal(result.contract_Address,contractInstance.address, " Registry must have the latest contract address of the AuthorizedPII contract ")
    }
    )))))))))))
})