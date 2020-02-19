/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description This class handles the Ethereum based functionality by using web3J (Java class object)
 *              This class helps in querying ethereum current block , connecting with ethereum ip or url etc.
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.blockchain.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

public class EthereumEngine implements BlockchainEngine {

    protected Web3j web3a;

    protected static final Logger log = LoggerFactory.getLogger(BlockchainEngine.class);

    @Override
    public int connectBlockchainServer(String strServerPath) {

        web3a = Web3j.build(new HttpService(strServerPath));

        if(web3a!=null) return 1;

        return 0;
    }

    @Override
    public Blockrecord getBlockTransactionData(String transation_hash) {

        web3a.ethGetTransactionByHash(transation_hash);

        EthTransaction ethTransaction = null;
        try {
            ethTransaction = web3a.ethGetTransactionByHash(
                    transation_hash).send();
            assertTrue(ethTransaction.getTransaction().isPresent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Transaction transaction = ethTransaction.getTransaction().get();

        transaction.getInput();
        //transaction.

        return null;
    }


    public String getLatestBlockNumber()
    {
        EthBlockNumber result = new EthBlockNumber();
        String strResult = "";
        try {
            result = web3a.ethBlockNumber()
                    .sendAsync().get();

            log.info("Recent Blockchain Block: " + result.getBlockNumber().toString());

            strResult = result.getBlockNumber().toString();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return strResult;
    }


    public String getCurrentWeb3Version()
    {
        Web3ClientVersion web3ClientVersion = null;
        String strResult="";
        try {
            web3ClientVersion = web3a.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();

            log.info("Current Version "+clientVersion);

            strResult = clientVersion;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strResult;
    }


    public EthGetBalance getEthBalance(String strAddress) {
        EthGetBalance result = null;

        try {
            result = new EthGetBalance();
            result = this.web3a.ethGetBalance(strAddress,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();

            BigDecimal eth = Convert.fromWei(result.getBalance().toString(), Convert.Unit.ETHER);

            log.info(eth+" ETH "+result.getBalance().toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<String> getAllWalletAddresses()
    {
        //Loop all the addresses inside ganache network
        List<String> address=new ArrayList<>();
        String account = null;
        try {

            //access direct without loop
            account = web3a.ethAccounts().send().getAccounts().get(0);

            for(int loop=0; loop<web3a.ethAccounts().send().getAccounts().size();loop++) {
                account = web3a.ethAccounts().send().getAccounts().get(loop);
                log.info("Account address: " + loop +" - "+account);
                getEthBalance(account);
                address.add(account);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }
}
