pragma solidity ^0.5.0;
import "./Ownable.sol";

contract AuthorizedPIIRelay{

    address public currentVersion;
    address public relayowner;


    constructor (address initAddr) public {
        currentVersion = initAddr;
        relayowner = msg.sender;
    }

    modifier onlyOwnerUse() {
        require(msg.sender == relayowner);
        _;
    }

    function changeContract(address newVersion) public
    onlyOwnerUse() {
        currentVersion = newVersion;
    }

    // fallback function
    function() external {
      (bool b,)=currentVersion.delegatecall(msg.data);
      require(b);
    }

    function getRelayOwner() public view  returns (address)
    {
    return relayowner;
    }

    function getCurrentVersion() public view  returns (address)
    {
        return currentVersion;
    }
}

