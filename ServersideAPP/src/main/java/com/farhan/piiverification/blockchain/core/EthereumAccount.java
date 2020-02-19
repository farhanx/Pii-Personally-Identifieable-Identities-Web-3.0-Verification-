/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description This class provides account credential keys and functionalities like creation account, account
 *              import or export in public and private keys.
 *              E
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

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
