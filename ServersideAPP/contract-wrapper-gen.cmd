REM This batch file is made by Farhan Hameed Khan (farhankhan@blockchainsfalcon.com) , to generate the java wrapper
REM It will automate the process to generate bin and abi contract files before generating the java wrapper.
REM if this command does not generate the wrapper then make sure to call them manually. Thanks

start  ./tools/solidity-windows/solc.exe ../EthereumContracts/src/contracts/AuthorizedPII.sol --bin --abi --optimize -o ./target --overwrite
timeout 2
call "./tools/web3j-4.5.0/bin/web3j.bat" "solidity" "generate" "-b" "./target/AuthorizedPII.bin"  "-a" "./target/AuthorizedPII.abi" "-o" "./src/main/java/" "-p" "com.farhan.piiverification.blockchain.contracts.AuthorizedPII_v1"
timeout 2
start  ./tools/solidity-windows/solc.exe ../EthereumContracts/src/contracts/AuthorizedRegistry.sol --bin --abi --optimize -o ./target --overwrite
timeout 2
call "./tools/web3j-4.5.0/bin/web3j.bat" "solidity" "generate" "-b" "./target/AuthorizedRegistry.bin"  "-a" "./target/AuthorizedRegistry.abi" "-o" "./src/main/java/" "-p" "com.farhan.piiverification.blockchain.contracts.AuthorizedRegistry"