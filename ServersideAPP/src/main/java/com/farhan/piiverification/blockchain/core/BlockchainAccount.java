/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description  Interface to support blockchain account creation and important features like address, private key or public key.
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.blockchain.core;

public interface BlockchainAccount {

    String accountAddress="";
    String privateKey="";
    String publicKey="";


    String getAccountAddress();

    String getAccountPrivateKey();

    String getAccountPublicKey();

    String createOrImportAccount(String data);
}
