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
