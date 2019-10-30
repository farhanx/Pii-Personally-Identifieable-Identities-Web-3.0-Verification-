pragma solidity ^0.5.0;
import "./Ownable.sol";

/// @title  SecurityRestriction this contract is built to support child contracts. The child contract can utilize circuit breaker pattern and ownable pattern.
/// @author Farhan Hameed Khan (farhankhan@blockchainsfalcon.com)
/// @notice The contract is inherited from a SecurityRestrction contract which supports circuit breaker and ownable based restrictions for any child contract
/// @dev    Main purpose of this contract to provide ownable and circuit breaker pattern for the child contract.
contract SecurityRestriction is Ownable{

    // Circuit Breaker
    bool stopped;

    // lets have this breaker switched off e.g. false
    function circuit_breaker() public {
        stopped = false;
    }

    // this function will toggle stop or start only by the main owner of the contract
    function toggle_active() onlyOwner public {

        stopped = !stopped;
    }

    // this modifier will stop the function if emergency breaker is switched on
    modifier stop_if_emergency() {
        if(!stopped) _;
    }

    // this modifer will allow the function to work only if there is an emergency situation
    modifier emergency_only() {
        if(stopped) _;
    }

}
