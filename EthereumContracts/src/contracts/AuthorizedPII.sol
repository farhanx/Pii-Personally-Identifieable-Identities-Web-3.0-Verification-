pragma solidity ^0.5.0;
import "./SecurityRestriction.sol";

/// @title  Authorized Personally Identifiable Information (PII) , Authority's Smart contract
/// @author Farhan Hameed Khan (farhankhan@blockchainsfalcon.com)
/// @notice The contract is inherited from a SecurityRestrction contract which support circuit breaker and ownable based restrictions
/// @dev    The main purpose of this contract to store and retrieve the user PII data with many security checks
contract AuthorizedPII is SecurityRestriction{

        event SubmissionEvent(uint indexed transactionid); // submission event used when a new transaction is submitted
        event NameEvent(string name); // name event is used when a name function is called by an authentic user
        event AddressEvent(string homeaddress); // Address event is used when a name function is called by an authentic user
        event AgeEvent(string age); // Age event is used when a name function is called by an authentic user

        event WithdrawalEvent(uint amount,address to); // withdrawl event is called when admin or the contract owner requests to transfer all fees from contract address to the owner address

        uint attestationFee; // fees for attestation
        uint currentCollectedFees; // current fees values collected inside contract
        mapping (address => uint) public paymentRecordsByAddresses; // just for tracking who paid how many times

        struct UserData {
         address identityaddress;
         string  age;
         string  name;
         string  homeAddress;
         string  ipfsPhoto;
        } // UserData structure which holds the individual user data

       uint private transactionCount; // number of transactions is recorded in this variable

       mapping(address => UserData) private identity_details; // An associated array of the user detail data with his account address


        /** @dev the constructor adjust the ether value in the attestationFee. */
       constructor  () public
       {
           //the deployer of the contract must tell who are the authorities
           attestationFee = 1; // e.g. 0.54 USD
       }



       /** @dev addTransaction - this function can only be called by the authority. The authority will have the permission to add any new user PII data.
        *  In order to add a new user , admin of the authority must enter name , age and home address's Hashes along with the user's wallet address.
        *  Therefore, user wallet information is associated with name , age and home address hashes on the smart contract.
        *  @param _name Name         Authority will add the "Full Name" of the user. Technically this data must be entered in HASH instead of readable text.
        *  @param _homeaddress       Authority will add the "Home Address" information of the user. Technically this data will must be entered in HASH instead of readable text.
        *  @param _age               Authority will add the "Age" information of the user. Technically this data will must be entered in HASH instead of readable text.
        *  @param _identityaddress   Authorty  will add the "Ethereum Wallet Address" information of the user.
        *  @return transactionId     The return value is the unique number assigned on the registered user
        */
       function addTransaction (string memory _name,string memory _homeaddress, string memory _age, string memory _ipfsPhoto, address _identityaddress )
       public onlyOwner stop_if_emergency returns (uint transactionId)
       {
           // lets check the string datas length (Though we will add only hash of Pii data but still hash supposed to be smaller than 100)
           require(bytes(_name).length <= 100, "Name is too long.");
           require(bytes(_homeaddress).length <= 100, "Home address is too long.");
           require(bytes(_age).length <= 100, "Age is too long.");
           require(bytes(_ipfsPhoto).length <= 69, "IPFS CID is wrong");

           // now store all the hashes in the blockchain transaction
           transactionId = transactionCount;

           // store string data in the array
           identity_details[_identityaddress] = UserData({identityaddress:_identityaddress,age:_age, name:_name,homeAddress:_homeaddress,ipfsPhoto:_ipfsPhoto });

           // count the transaction count
           transactionCount +=1;

           // send submissio event with the total number of transaction id also return the same transaction id
           emit SubmissionEvent(transactionId);
       }



      /** @dev getName - this function  This function is a public function and in order to call this function user must pay the fee along with the calling parameter.
       *  The calling parameter supposed to be the wallet whose information needs to be returned, also this function checks if the caller is a valid caller who posses
       *  any registered information associated with his account address. If it finds the PII information against his name then it takes the fee and emit the event.
       *  @param _identityaddress   identity address must be checked .
       */
       function getName(address _identityaddress)  payable public  stop_if_emergency
       {

           require((identity_details[_identityaddress].identityaddress==msg.sender),"Unauthorized Attempt");

           require(msg.value >= attestationFee,"Insufficient fees msg.value"); // fees must be paid as per decided cost

           // copy data into memory
           UserData memory p = identity_details[_identityaddress];

           // record payment history (Just for recording)
           recordPaymentHistory();

           // emit the event
           emit NameEvent(p.name);
       }


      /** @dev getAge -  This function is a public function and in order to call this function user must pay the fee along with the calling parameter.
       *  The calling parameter supposed to be the wallet whose information needs to be returned, also this function checks if the caller is a valid caller who posses
       *  any registered information associated with his account address.
       *  If it finds the PII information against his Age then it takes the fee and emit the event.
       *  @param _identityaddress   identity address must be provided which will be later checked with the caller.
       */
       function getAge(address _identityaddress) public payable stop_if_emergency
       {
           require((identity_details[_identityaddress].identityaddress==msg.sender),"Unauthorized Attempt");

           require(msg.value >= attestationFee,"Insufficient fees msg.value"); // fees must be paid as per decided cost

           // copy the data into memory
           UserData memory p = identity_details[_identityaddress];

           // record payment history (Just for recording)
           recordPaymentHistory();

           // emit the event
           emit AgeEvent(p.age);
       }

      /** @dev getAddress -  This function is a public function and in order to call this function user must pay the fee along with the calling parameter.
       *  The calling parameter supposed to be the wallet whose information needs to be returned, also this function checks if the caller is a valid caller who posses
       *  any registered information associated with his account address.
       *  If it finds the PII information against his Address then it takes the fee and emit the event.
       *  @param _identityaddress   identity address will be checked whose information is required.
       */
       function getAddress(address _identityaddress) public payable stop_if_emergency
       {
           require((identity_details[_identityaddress].identityaddress==msg.sender),"Unauthorized Attempt");

           require(msg.value >= attestationFee,"Insufficient fees msg.value"); // fees must be paid as per decided cost

           // copy the data into memory
           UserData memory p = identity_details[_identityaddress];

           // record payment history (Just for recording)
           recordPaymentHistory();

           // emit the event
           emit AddressEvent(p.homeAddress);
       }


        /** @dev getPhoto -  Return current user's photo.
         *  @param _identityaddress   identity address must be provided which will be checked with the caller
         *  @return string The address of the current owner of the contract
         */
        function getPhoto(address _identityaddress) public view returns (string memory)
        {
            require((identity_details[_identityaddress].identityaddress==msg.sender),"Unauthorized Attempt");

            // copy the data into memory
            UserData memory p = identity_details[_identityaddress];

            return (p.ipfsPhoto);
        }

      /** @dev getOwner -  Return current owner's account address.
       *  @return address The address of the current owner of the contract
       */
       function getOwner() public view returns (address ret)
       {
           return owner;
       }

      /** @dev kill -  Self destruct the contract if it is called by the admin or the owner of the contract.
       */
       function kill() onlyOwner public
       {
            if(msg.sender == owner) selfdestruct(address(uint160(owner))); // cast owner to address payable
       }

      /** @dev recordPaymentHistory -  Record all the payment histories like which user has submitted how much fees till this date regardless if the money is withdraw or not, it is just for knowledge.
       */
       function recordPaymentHistory() private
       {
            paymentRecordsByAddresses[msg.sender] += msg.value;
            currentCollectedFees += msg.value;
       }

       /** @dev getTotalPaymentRecieve -  It returns the tot collected fees at the moment
        *  @return currentCollectedFees current collected fees
        */
       function getTotalPaymentRecieve() view public onlyOwner returns (uint)
       {
            return currentCollectedFees;
       }

    function getTest() view public onlyOwner returns (uint)
    {
        return 32;
    }

       /** @dev withdraw -  This function is restricted to the owner of this smart contract
        *  , owner can withdraw all the fees collected from smart contract account to his own account
        */
       function withdraw() public onlyOwner {
            require(currentCollectedFees>0,"Insufficient balance exist");
            uint  balance = currentCollectedFees; // get the total balance in temporary variable
            currentCollectedFees = 0; // make the balance holder variable to zero
            msg.sender.transfer(balance); // now transfer the balance which was held in a temporary variable
            emit WithdrawalEvent(balance,msg.sender); // send the withdrawl event
       }

}