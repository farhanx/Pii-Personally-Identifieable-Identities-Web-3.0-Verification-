package com.farhan.piiverification.blockchain.core;

public interface BlockchainEngine {

    int connectBlockchainServer(String strServerPath);

    Blockrecord getBlockTransactionData(String transation_hash);

}
