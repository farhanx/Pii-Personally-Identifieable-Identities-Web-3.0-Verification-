const ENS = require('../node_modules/ethjs-ens');

provider= web3.currenProvider;
const ens = new ENS({ provider, network: '3' });

alert(ens);
console.log(ens);
