package com.farhan.piiverification.blockchain.core;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;

import static org.web3j.utils.Numeric.hexStringToByteArray;

public class EthereumAccount implements BlockchainAccount {

    Credentials credentials;

    public EthereumAccount(String strPrivateKey)
    {
        credentials = Credentials.create(ECKeyPair.create(hexStringToByteArray(strPrivateKey)));
    }

    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public String getAccountAddress() {
        return credentials.getAddress();
    }


    @Override
    public String getAccountPrivateKey() {
        return credentials.getEcKeyPair().getPrivateKey().toString();
    }

    @Override
    public String getAccountPublicKey() {
        return credentials.getEcKeyPair().getPublicKey().toString();
    }

    @Override
    public String createOrImportAccount(String strPrivateKey) {

        credentials = Credentials.create(ECKeyPair.create(hexStringToByteArray(strPrivateKey)));

        return credentials.getAddress();
    }
}
