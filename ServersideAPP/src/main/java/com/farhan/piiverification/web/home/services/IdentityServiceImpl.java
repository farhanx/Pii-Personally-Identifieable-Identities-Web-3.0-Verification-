/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description  Complete service implementation
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */
package com.farhan.piiverification.web.home.services;

import com.farhan.piiverification.blockchain.Utilities.CryptoUtilities;
import com.farhan.piiverification.blockchain.contracts.IdentityEthereumTransaction;
import com.farhan.piiverification.web.home.config.BlockchainConfig;
import com.farhan.piiverification.web.home.domains.UserIdentity;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IdentityServiceImpl implements IdentityService{

    private IdentityEthereumTransaction identity = null;

    IdentityServiceImpl() throws Exception {
        //this is setup on the main function
        identity =   new IdentityEthereumTransaction(BlockchainConfig.RunBlockchainType);//IdentityEthereumTransaction.ETHEREUM_TRUFFLE);//ETHEREUM_ROSPOTEN);//IdentityEthereumTransaction.ETHEREUM_GANACHE);//
        identity.setUseHashData(true);
    }

    @Override
    public String getContractAddress() {

        return identity.getSmartContractAddress();
    }

    @Override
    public String getRegistryContractAddress() {

        return identity.getRegistryContractAddress();
    }

    @Override
    public String getNetworkType() {

        String network ="Unknown";
        int networktype = identity.getLastBlockchainNetworkType();

        if(IdentityEthereumTransaction.ETHEREUM_TRUFFLE==networktype) {
            network = "Truffle";
        }
        else if(IdentityEthereumTransaction.ETHEREUM_GANACHE==networktype) {
            network = "Ganache";
         }
        else if(IdentityEthereumTransaction.ETHEREUM_ROSPOTEN==networktype) {
            network = "Ropsten";
        }
        return network;
    }

    @Override
    public String submitIdentityonEthereum(UserIdentity userIdentity) {

        String cid="";

        try {

            byte[] bt = userIdentity.getPhoto().getBytes();
            String strname = userIdentity.getPhoto().getName();
            cid = getIpfsCIDfromFile(strname,bt);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        identity.setAddress_hash(userIdentity.getAddress());
        identity.setAge_hash(userIdentity.getAge());
        identity.setName_hash(userIdentity.getName());
        identity.setIPFS_ImageCid(cid);
        identity.setIdentityWallet(userIdentity.getWallet());
        String txhash =  identity.insertInContract();
        //identity.getBlockTransactionData(txhash);

        return txhash;
    }

    @Override
    public UserIdentity getIdentityFromEthereum(String userAccount, String userKey) {

        if(identity.isContractExist()) {
            //retrieve record 1
            identity.setCallingAccount(userKey);
            identity.clearAllData();
            identity.setIdentityWallet(userAccount);
          //  identity.retrieveFromContract();
            String strdata = identity.retrieveFromContract();
            System.out.println("Iqama = " + identity.getName_hash() + " , Address=" + identity.getAddress_hash() + ", " +
                    "age=" + identity.getAge_hash());

            UserIdentity userIdentity = new UserIdentity();
            userIdentity.setWallet(userAccount);
            userIdentity.setAge(identity.getAge_hash());
            userIdentity.setName(identity.getName_hash());
            userIdentity.setAddress(identity.getAddress_hash());

            return userIdentity;
        }

            return null;
    }

    String getIpfsCIDfromFile(String filename,byte[] contents)
    {
        IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
        String ret ="";
        try {

            ipfs.refs.local();

            //adding a file
            //   NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("hello.txt"));
            NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(filename, contents);
            MerkleNode addResult = ipfs.add(file).get(0);
            ret = addResult.hash.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
