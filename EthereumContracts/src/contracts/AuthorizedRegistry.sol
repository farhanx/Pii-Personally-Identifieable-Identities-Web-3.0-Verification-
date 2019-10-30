pragma solidity ^0.5.0;
import "./Ownable.sol";

/// @title  AuthorizedRegistry is the main registry contract which holds the version history and tracks the new upgrades based on version details.
/// @author Farhan Hameed Khan (farhankhan@blockchainsfalcon.com)
/// @notice The contract is inherited from a Ownable contract so adding a new version can only be done by the main owner of the contract
/// @dev    The main purpose of this contract to store and retrieve the current version of the AuthorityPII contract
contract AuthorizedRegistry is Ownable{

    uint current_version_number; // It holds the contract version , all the  version must be decimals.

    struct VersionDetails {
        address added_by_owner;
        address contract_Address;
        string  versionName;
        uint    version_number;
    } // Version detail structure holds the information like owner name who added , contract address , version  name and number.

    mapping(uint => VersionDetails) versionHistory; // version number is associated with the version details.


    /** @dev constructor - the constructor store the first version of the AuthorityPII address and its name
    *  The user who will deploy this contract will automatically becomes the owner of the contract using ownable parent class
    *  @param _versionName      Name of the version
    *  @param _contractAddress  contract address of the latest version
    */
    constructor (string memory _versionName, address _contractAddress) public
    {
        current_version_number = 0; // initialize it zero since first version will be incremented and becomes 1 in addLatestVersion function

        addLatestVersion(_versionName,_contractAddress); // for the deployment it is must to provide first version of AuthorityPII
    }

   /** @dev addLatestVersion - direct fucntion for the owner to add new upgraded versions of AuthorityPII contract
   *  @param _versionName      Name of the version
   *  @param _contractAddress  contract address of the latest version
   *  @return current_version_number  return the added version number
   */
    function addLatestVersion( string memory _versionName, address _contractAddress) public onlyOwner returns (uint) {

        // versions should be incremented on every call (we are only storing decimal number as a version )
        current_version_number = current_version_number + 1;

        // add the version history including name , address, owner and version number , also associating the array with version number
        versionHistory[current_version_number] = VersionDetails({added_by_owner:msg.sender,contract_Address:_contractAddress, versionName:_versionName,version_number:current_version_number});

        // return latest version number which is added
        return current_version_number;
    }

    /** @dev getLatestVersion - This function returns the latest AuthorityPII contract's address and its version number
    *  @return contract_Address  AuthorityPII contract Address
    *  @return version_number    AuthorityPII contract version
    */
    function getLatestVersion() view public returns(address contract_Address, uint version_number)
    {
        // return the latest version of the contract
        return (versionHistory[current_version_number].contract_Address, versionHistory[current_version_number].version_number);
    }

    /** @dev getVersionByNumber - This function returns the AuthorityPII contract's based on the version parameter provided
     *  @param version_number     version number
     *  @return contract_Address  AuthorityPII contract Address
     *  @return version_number    AuthorityPII contract version
     */
    function getVersionByNumber(uint version_number) view public returns(address, uint)
    {
        require(version_number>=0,"Unauthorized Attempt"); // version supposed to be bigger than 0
        require(version_number<=current_version_number,"Unauthorized Attempt"); // version supposed to be less than last stored version number

        // return the associated version contract address
        return (versionHistory[version_number].contract_Address, versionHistory[version_number].version_number);
    }

    /** @dev getVersionByNumber - This function returns the AuthorityPII contract's based on the version parameter provided
     *  @param version_number     version number
     *  @return contract_Address  AuthorityPII contract Address
     *  @return version_number    AuthorityPII contract version
     *  @return versionName       AuthorityPII contract version name
     *  @return added_by_owner    Owner name who added this version
     */
    function getVersionDetailByNumber(uint version_number) view public returns(address, uint,string memory, address)
    {
        require(version_number>=0,"Unauthorized Attempt"); // version supposed to be bigger than 0
        require(version_number<=current_version_number,"Unauthorized Attempt"); // version supposed to be less than last stored version number

        // return the associated version contract address
        return (versionHistory[version_number].contract_Address, versionHistory[version_number].version_number,versionHistory[version_number].versionName,versionHistory[version_number].added_by_owner);
    }

    /** @dev contractHasVersionInfo - This function checks if the contract was deployed successful with proper version storage
     *  @return bool    if contract has the version data then it will return true else false
     */
    function contractHasVersionInfo() view public returns(bool)
    {
        if(current_version_number==0) return false;

        return true;
    }
}