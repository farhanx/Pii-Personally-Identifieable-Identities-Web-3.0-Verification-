var AuthorizedPII1 = artifacts.require('./AuthorizedPII.sol');
var AuthorizedRegistry = artifacts.require('./AuthorizedRegistry.sol');

//lets get the default information like network and number of generated accounts
module.exports = function(deployer, network, accounts) {

    // const owners = [accounts[0]]; // use the first user as the authroized user to deploy the contract who will remain as the owner of contract
    // deployer.deploy(AuthorizedPII1,owners);
    const version_name = "ChocolateCake";
    const contract_address = AuthorizedPII1.address;
    deployer.deploy(AuthorizedRegistry,version_name,contract_address);
};