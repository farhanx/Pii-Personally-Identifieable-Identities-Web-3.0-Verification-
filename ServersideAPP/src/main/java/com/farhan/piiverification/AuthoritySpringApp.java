/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description This is the main spring biit application file.
 *              The server APP is the "Authorty's APP" built in Java Spring technology using Maven depenency manager.
 *              This project sole purpose is to allow the owner or the administrator to register any users with their
 *              Full name, age , home address and photo along with the wallet address.
 *
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification;

import com.farhan.piiverification.blockchain.contracts.IdentityEthereumTransaction;
import com.farhan.piiverification.web.home.config.BlockchainConfig;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AuthoritySpringApp extends SpringBootServletInitializer {

    //for making war we need to extend this class with SpringBootServletInitializer
    public static void main(String[] args)
    {


        // You can change blockchain network type from truffle , ganache and rospsten
        // MAKE SURE YOU DELETE THE CONTENT OF smartcontract.properties BEFORE RUNNING NEW GANACHE OR TRUFFLE
        // OTHERWISE YOU MIGHT FACE PROBLEM IS READING DATA.

        BlockchainConfig.RunBlockchainType = IdentityEthereumTransaction.ETHEREUM_TRUFFLE;//IdentityEthereumTransaction.ETHEREUM_ROSPOTEN ;//IdentityEthereumTransaction.ETHEREUM_TRUFFLE;//.ETHEREUM_GANACHE;//.ETHEREUM_TRUFFLE;//.ETHEREUM_ROSPOTEN;

        SpringApplication.run(AuthoritySpringApp.class, args);

        IPFS_test();


    }

    //for making war we need to override this below function
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthoritySpringApp.class);
    }

    public static void IPFS_test()
    {
        try {

            IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

            ipfs.refs.local();

            //adding a file
         //   NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("hello.txt"));
            NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("hello.txt", "G'day world! IPFS rocks!".getBytes());
            MerkleNode addResult = ipfs.add(file).get(0);

            // Getting a file
            Multihash filePointer = Multihash.fromBase58("QmXJ8NLGUkkSqVVXgmtZqxknwuekXZC4BbRBfZ23qcsrGJ");
            byte[] fileContents = ipfs.cat(filePointer);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
