var AuthorizedPII1 = artifacts.require('./AuthorizedPII.sol');
var AuthorizedPIIRelay1 = artifacts.require('./AuthorizedPIIRelay.sol');

//lets get the default information like network and number of generated accounts
module.exports = function(deployer, network, accounts) {

    // const owners = [accounts[0]]; // use the first user as the authroized user to deploy the contract who will remain as the owner of contract
    // deployer.deploy(AuthorizedPII1,owners);
    deployer.deploy(AuthorizedPIIRelay1,AuthorizedPII1.address);
};