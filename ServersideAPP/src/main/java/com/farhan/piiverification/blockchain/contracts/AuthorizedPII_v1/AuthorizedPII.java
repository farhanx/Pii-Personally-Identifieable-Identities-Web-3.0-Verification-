package com.farhan.piiverification.blockchain.contracts.AuthorizedPII_v1;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.0.
 *
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */
public class AuthorizedPII extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055600180556119ea806100366000396000f3fe6080604052600436106100e85760003560e01c80637f12e9291161008a578063a8cd0a8011610059578063a8cd0a801461050d578063ae22c57d14610522578063f2fde38b14610548578063fb3fbc121461057b576100e8565b80637f12e92914610267578063840ec6f4146104b2578063893d20e8146104c75780638da5cb5b146104f8576100e8565b80633ccfd60b116100c65780633ccfd60b1461016f5780633e23d0dc1461018457806341c0e1b51461022c5780635fd4b08a14610241576100e8565b806307609ec4146100ed57806338451598146101325780633afd1b671461015a575b600080fd5b3480156100f957600080fd5b506101206004803603602081101561011057600080fd5b50356001600160a01b0316610590565b60408051918252519081900360200190f35b6101586004803603602081101561014857600080fd5b50356001600160a01b03166105a2565b005b34801561016657600080fd5b506101586109a0565b34801561017b57600080fd5b506101586109d8565b34801561019057600080fd5b506101b7600480360360208110156101a757600080fd5b50356001600160a01b0316610abc565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101f15781810151838201526020016101d9565b50505050905090810190601f16801561021e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561023857600080fd5b50610158610db5565b6101586004803603602081101561025757600080fd5b50356001600160a01b0316610def565b34801561027357600080fd5b50610120600480360360a081101561028a57600080fd5b810190602081018135600160201b8111156102a457600080fd5b8201836020820111156102b657600080fd5b803590602001918460018302840111600160201b831117156102d757600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561032957600080fd5b82018360208201111561033b57600080fd5b803590602001918460018302840111600160201b8311171561035c57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b8111156103ae57600080fd5b8201836020820111156103c057600080fd5b803590602001918460018302840111600160201b831117156103e157600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561043357600080fd5b82018360208201111561044557600080fd5b803590602001918460018302840111600160201b8311171561046657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550505090356001600160a01b031691506111a79050565b3480156104be57600080fd5b5061012061140f565b3480156104d357600080fd5b506104dc61142f565b604080516001600160a01b039092168252519081900360200190f35b34801561050457600080fd5b506104dc61143e565b34801561051957600080fd5b5061012061144d565b6101586004803603602081101561053857600080fd5b50356001600160a01b031661146b565b34801561055457600080fd5b506101586004803603602081101561056b57600080fd5b50356001600160a01b0316611825565b34801561058757600080fd5b50610158611845565b60036020526000908152604090205481565b600054600160a01b900460ff1661099d576001600160a01b03818116600090815260056020526040902054163314610618576040805162461bcd60e51b8152602060048201526014602482015273155b985d5d1a1bdc9a5e995908105d1d195b5c1d60621b604482015290519081900360640190fd5b60015434101561066f576040805162461bcd60e51b815260206004820152601b60248201527f496e73756666696369656e742066656573206d73672e76616c75650000000000604482015290519081900360640190fd5b6106776118e5565b6001600160a01b03828116600090815260056020908152604091829020825160a08101845281549094168452600180820180548551600261010094831615949094026000190190911692909204601f81018590048502830185019095528482529193858401939192918301828280156107315780601f1061070657610100808354040283529160200191610731565b820191906000526020600020905b81548152906001019060200180831161071457829003601f168201915b5050509183525050600282810180546040805160206001841615610100026000190190931694909404601f810183900483028501830190915280845293810193908301828280156107c35780601f10610798576101008083540402835291602001916107c3565b820191906000526020600020905b8154815290600101906020018083116107a657829003601f168201915b505050918352505060038201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156108575780601f1061082c57610100808354040283529160200191610857565b820191906000526020600020905b81548152906001019060200180831161083a57829003601f168201915b505050918352505060048201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156108eb5780601f106108c0576101008083540402835291602001916108eb565b820191906000526020600020905b8154815290600101906020018083116108ce57829003601f168201915b50505050508152505090506108fe611854565b7f8fb2dbaf6ab101ba290f3b8e22a2fa9c67bc1431886025c294d8fa98c549559381602001516040518080602001828103825283818151815260200191508051906020019080838360005b83811015610961578181015183820152602001610949565b50505050905090810190601f16801561098e5780820380516001836020036101000a031916815260200191505b509250505060405180910390a1505b50565b6000546001600160a01b031633146109b757600080fd5b6000805460ff60a01b198116600160a01b9182900460ff1615909102179055565b6000546001600160a01b031633146109ef57600080fd5b600060025411610a46576040805162461bcd60e51b815260206004820152601a60248201527f496e73756666696369656e742062616c616e6365206578697374000000000000604482015290519081900360640190fd5b600280546000918290556040519091339183156108fc0291849190818181858888f19350505050158015610a7e573d6000803e3d6000fd5b506040805182815233602082015281517f418a322c86cae7c7c2627da86731aa97c3a52870ef306e65aadb1066a19ee11f929181900390910190a150565b6001600160a01b03818116600090815260056020526040902054606091163314610b24576040805162461bcd60e51b8152602060048201526014602482015273155b985d5d1a1bdc9a5e995908105d1d195b5c1d60621b604482015290519081900360640190fd5b610b2c6118e5565b6001600160a01b03838116600090815260056020908152604091829020825160a08101845281549094168452600180820180548551600261010094831615949094026000190190911692909204601f8101859004850283018501909552848252919385840193919291830182828015610be65780601f10610bbb57610100808354040283529160200191610be6565b820191906000526020600020905b815481529060010190602001808311610bc957829003601f168201915b5050509183525050600282810180546040805160206001841615610100026000190190931694909404601f81018390048302850183019091528084529381019390830182828015610c785780601f10610c4d57610100808354040283529160200191610c78565b820191906000526020600020905b815481529060010190602001808311610c5b57829003601f168201915b505050918352505060038201805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152938201939291830182828015610d0c5780601f10610ce157610100808354040283529160200191610d0c565b820191906000526020600020905b815481529060010190602001808311610cef57829003601f168201915b505050918352505060048201805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152938201939291830182828015610da05780601f10610d7557610100808354040283529160200191610da0565b820191906000526020600020905b815481529060010190602001808311610d8357829003601f168201915b50505091909252505050608001519392505050565b6000546001600160a01b03163314610dcc57600080fd5b6000546001600160a01b0316331415610ded576000546001600160a01b0316ff5b565b600054600160a01b900460ff1661099d576001600160a01b03818116600090815260056020526040902054163314610e65576040805162461bcd60e51b8152602060048201526014602482015273155b985d5d1a1bdc9a5e995908105d1d195b5c1d60621b604482015290519081900360640190fd5b600154341015610ebc576040805162461bcd60e51b815260206004820152601b60248201527f496e73756666696369656e742066656573206d73672e76616c75650000000000604482015290519081900360640190fd5b610ec46118e5565b6001600160a01b03828116600090815260056020908152604091829020825160a08101845281549094168452600180820180548551600261010094831615949094026000190190911692909204601f8101859004850283018501909552848252919385840193919291830182828015610f7e5780601f10610f5357610100808354040283529160200191610f7e565b820191906000526020600020905b815481529060010190602001808311610f6157829003601f168201915b5050509183525050600282810180546040805160206001841615610100026000190190931694909404601f810183900483028501830190915280845293810193908301828280156110105780601f10610fe557610100808354040283529160200191611010565b820191906000526020600020905b815481529060010190602001808311610ff357829003601f168201915b505050918352505060038201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156110a45780601f10611079576101008083540402835291602001916110a4565b820191906000526020600020905b81548152906001019060200180831161108757829003601f168201915b505050918352505060048201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156111385780601f1061110d57610100808354040283529160200191611138565b820191906000526020600020905b81548152906001019060200180831161111b57829003601f168201915b505050505081525050905061114b611854565b6040818101518151602080825282518183015282517f1d057d4e0bca360fa80f50b30787b994e561d0247fc45395f406cd77c2c203bc948392908301919085019080838360008315610961578181015183820152602001610949565b600080546001600160a01b031633146111bf57600080fd5b600054600160a01b900460ff166114065760648651111561121b576040805162461bcd60e51b81526020600482015260116024820152702730b6b29034b9903a37b7903637b7339760791b604482015290519081900360640190fd5b606485511115611272576040805162461bcd60e51b815260206004820152601960248201527f486f6d65206164647265737320697320746f6f206c6f6e672e00000000000000604482015290519081900360640190fd5b6064845111156112bc576040805162461bcd60e51b815260206004820152601060248201526f20b3b29034b9903a37b7903637b7339760811b604482015290519081900360640190fd5b604583511115611307576040805162461bcd60e51b815260206004820152601160248201527049504653204349442069732077726f6e6760781b604482015290519081900360640190fd5b506004546040805160a0810182526001600160a01b0384811680835260208084018981528486018c9052606085018b90526080850189905260009283526005825294909120835181546001600160a01b0319169316929092178255925180519293919261137a926001850192019061191d565b506040820151805161139691600284019160209091019061191d565b50606082015180516113b291600384019160209091019061191d565b50608082015180516113ce91600484019160209091019061191d565b50506004805460010190555060405181907f705f97ee415a7e06069321233a1a131e1d66a7bec37f49c7c211fb2f012010a190600090a25b95945050505050565b600080546001600160a01b0316331461142757600080fd5b506002545b90565b6000546001600160a01b031690565b6000546001600160a01b031681565b600080546001600160a01b0316331461146557600080fd5b50602090565b600054600160a01b900460ff1661099d576001600160a01b038181166000908152600560205260409020541633146114e1576040805162461bcd60e51b8152602060048201526014602482015273155b985d5d1a1bdc9a5e995908105d1d195b5c1d60621b604482015290519081900360640190fd5b600154341015611538576040805162461bcd60e51b815260206004820152601b60248201527f496e73756666696369656e742066656573206d73672e76616c75650000000000604482015290519081900360640190fd5b6115406118e5565b6001600160a01b03828116600090815260056020908152604091829020825160a08101845281549094168452600180820180548551600261010094831615949094026000190190911692909204601f81018590048502830185019095528482529193858401939192918301828280156115fa5780601f106115cf576101008083540402835291602001916115fa565b820191906000526020600020905b8154815290600101906020018083116115dd57829003601f168201915b5050509183525050600282810180546040805160206001841615610100026000190190931694909404601f8101839004830285018301909152808452938101939083018282801561168c5780601f106116615761010080835404028352916020019161168c565b820191906000526020600020905b81548152906001019060200180831161166f57829003601f168201915b505050918352505060038201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156117205780601f106116f557610100808354040283529160200191611720565b820191906000526020600020905b81548152906001019060200180831161170357829003601f168201915b505050918352505060048201805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529382019392918301828280156117b45780601f10611789576101008083540402835291602001916117b4565b820191906000526020600020905b81548152906001019060200180831161179757829003601f168201915b50505050508152505090506117c7611854565b606081015160408051602080825283518183015283517f2bd0a82a604a6d5265b639f45f42bed02c15b3b1af816b72b7f6119ea8431da494938392908301919085019080838360008315610961578181015183820152602001610949565b6000546001600160a01b0316331461183c57600080fd5b61099d81611877565b6000805460ff60a01b19169055565b336000908152600360205260409020805434908101909155600280549091019055565b6001600160a01b03811661188a57600080fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b6040518060a0016040528060006001600160a01b03168152602001606081526020016060815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061195e57805160ff191683800117855561198b565b8280016001018555821561198b579182015b8281111561198b578251825591602001919060010190611970565b5061199792915061199b565b5090565b61142c91905b8082111561199757600081556001016119a156fea265627a7a72315820bf812833ebded374b5fe772b3e2335249f46de08e1b6963e2b624af9798d903a64736f6c634300050b0032";

    public static final String FUNC_PAYMENTRECORDSBYADDRESSES = "paymentRecordsByAddresses";

    public static final String FUNC_GETAGE = "getAge";

    public static final String FUNC_TOGGLE_ACTIVE = "toggle_active";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_GETPHOTO = "getPhoto";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_GETNAME = "getName";

    public static final String FUNC_ADDTRANSACTION = "addTransaction";

    public static final String FUNC_GETTOTALPAYMENTRECIEVE = "getTotalPaymentRecieve";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETTEST = "getTest";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_CIRCUIT_BREAKER = "circuit_breaker";

    public static final Event SUBMISSIONEVENT_EVENT = new Event("SubmissionEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event NAMEEVENT_EVENT = new Event("NameEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event ADDRESSEVENT_EVENT = new Event("AddressEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event AGEEVENT_EVENT = new Event("AgeEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event WITHDRAWALEVENT_EVENT = new Event("WithdrawalEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPRENOUNCED_EVENT = new Event("OwnershipRenounced", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected AuthorizedPII(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AuthorizedPII(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AuthorizedPII(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AuthorizedPII(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> paymentRecordsByAddresses(String param0) {
        final Function function = new Function(FUNC_PAYMENTRECORDSBYADDRESSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getAge(String _identityaddress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_GETAGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _identityaddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> toggle_active() {
        final Function function = new Function(
                FUNC_TOGGLE_ACTIVE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getPhoto(String _identityaddress) {
        final Function function = new Function(FUNC_GETPHOTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _identityaddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getName(String _identityaddress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_GETNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _identityaddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> addTransaction(String _name, String _homeaddress, String _age, String _ipfsPhoto, String _identityaddress) {
        final Function function = new Function(
                FUNC_ADDTRANSACTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_homeaddress), 
                new org.web3j.abi.datatypes.Utf8String(_age), 
                new org.web3j.abi.datatypes.Utf8String(_ipfsPhoto), 
                new org.web3j.abi.datatypes.Address(160, _identityaddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getTotalPaymentRecieve() {
        final Function function = new Function(FUNC_GETTOTALPAYMENTRECIEVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getOwner() {
        final Function function = new Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getTest() {
        final Function function = new Function(FUNC_GETTEST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getAddress(String _identityaddress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_GETADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _identityaddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> circuit_breaker() {
        final Function function = new Function(
                FUNC_CIRCUIT_BREAKER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<SubmissionEventEventResponse> getSubmissionEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SUBMISSIONEVENT_EVENT, transactionReceipt);
        ArrayList<SubmissionEventEventResponse> responses = new ArrayList<SubmissionEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SubmissionEventEventResponse typedResponse = new SubmissionEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.transactionid = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SubmissionEventEventResponse> submissionEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, SubmissionEventEventResponse>() {
            @Override
            public SubmissionEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SUBMISSIONEVENT_EVENT, log);
                SubmissionEventEventResponse typedResponse = new SubmissionEventEventResponse();
                typedResponse.log = log;
                typedResponse.transactionid = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SubmissionEventEventResponse> submissionEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SUBMISSIONEVENT_EVENT));
        return submissionEventEventFlowable(filter);
    }

    public List<NameEventEventResponse> getNameEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NAMEEVENT_EVENT, transactionReceipt);
        ArrayList<NameEventEventResponse> responses = new ArrayList<NameEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NameEventEventResponse typedResponse = new NameEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NameEventEventResponse> nameEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, NameEventEventResponse>() {
            @Override
            public NameEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NAMEEVENT_EVENT, log);
                NameEventEventResponse typedResponse = new NameEventEventResponse();
                typedResponse.log = log;
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NameEventEventResponse> nameEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NAMEEVENT_EVENT));
        return nameEventEventFlowable(filter);
    }

    public List<AddressEventEventResponse> getAddressEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDRESSEVENT_EVENT, transactionReceipt);
        ArrayList<AddressEventEventResponse> responses = new ArrayList<AddressEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddressEventEventResponse typedResponse = new AddressEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.homeaddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddressEventEventResponse> addressEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AddressEventEventResponse>() {
            @Override
            public AddressEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDRESSEVENT_EVENT, log);
                AddressEventEventResponse typedResponse = new AddressEventEventResponse();
                typedResponse.log = log;
                typedResponse.homeaddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddressEventEventResponse> addressEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDRESSEVENT_EVENT));
        return addressEventEventFlowable(filter);
    }

    public List<AgeEventEventResponse> getAgeEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AGEEVENT_EVENT, transactionReceipt);
        ArrayList<AgeEventEventResponse> responses = new ArrayList<AgeEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AgeEventEventResponse typedResponse = new AgeEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.age = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AgeEventEventResponse> ageEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AgeEventEventResponse>() {
            @Override
            public AgeEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AGEEVENT_EVENT, log);
                AgeEventEventResponse typedResponse = new AgeEventEventResponse();
                typedResponse.log = log;
                typedResponse.age = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AgeEventEventResponse> ageEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AGEEVENT_EVENT));
        return ageEventEventFlowable(filter);
    }

    public List<WithdrawalEventEventResponse> getWithdrawalEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWALEVENT_EVENT, transactionReceipt);
        ArrayList<WithdrawalEventEventResponse> responses = new ArrayList<WithdrawalEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawalEventEventResponse typedResponse = new WithdrawalEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawalEventEventResponse> withdrawalEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, WithdrawalEventEventResponse>() {
            @Override
            public WithdrawalEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWALEVENT_EVENT, log);
                WithdrawalEventEventResponse typedResponse = new WithdrawalEventEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawalEventEventResponse> withdrawalEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWALEVENT_EVENT));
        return withdrawalEventEventFlowable(filter);
    }

    public List<OwnershipRenouncedEventResponse> getOwnershipRenouncedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, transactionReceipt);
        ArrayList<OwnershipRenouncedEventResponse> responses = new ArrayList<OwnershipRenouncedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipRenouncedEventResponse> ownershipRenouncedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipRenouncedEventResponse>() {
            @Override
            public OwnershipRenouncedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, log);
                OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipRenouncedEventResponse> ownershipRenouncedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPRENOUNCED_EVENT));
        return ownershipRenouncedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    @Deprecated
    public static AuthorizedPII load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorizedPII(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AuthorizedPII load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuthorizedPII(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AuthorizedPII load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AuthorizedPII(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AuthorizedPII load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AuthorizedPII(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AuthorizedPII> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AuthorizedPII.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<AuthorizedPII> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AuthorizedPII.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AuthorizedPII> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AuthorizedPII.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AuthorizedPII> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AuthorizedPII.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SubmissionEventEventResponse extends BaseEventResponse {
        public BigInteger transactionid;
    }

    public static class NameEventEventResponse extends BaseEventResponse {
        public String name;
    }

    public static class AddressEventEventResponse extends BaseEventResponse {
        public String homeaddress;
    }

    public static class AgeEventEventResponse extends BaseEventResponse {
        public String age;
    }

    public static class WithdrawalEventEventResponse extends BaseEventResponse {
        public BigInteger amount;

        public String to;
    }

    public static class OwnershipRenouncedEventResponse extends BaseEventResponse {
        public String previousOwner;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
