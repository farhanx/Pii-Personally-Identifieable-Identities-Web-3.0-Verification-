package com.farhan.piiverification.blockchain.core;

public class EthereumBlockRecord implements Blockrecord {

    String blockhash;
    String blocknumber;
    String from;
    String gas;
    String gasPrice;
    String hash;
    String input;

    @Override
    public String getTransactionHash() {
        return hash;
    }

    @Override
    public String getBlockNumber() {
        return blocknumber;
    }

    @Override
    public String getTransactionCreatorAddress() {
        return from;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public void setBlocknumber(String blocknumber) {
        this.blocknumber = blocknumber;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public String getBlocknumber() {
        return blocknumber;
    }

    public String getFrom() {
        return from;
    }

    public String getGas() {
        return gas;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getHash() {
        return hash;
    }

    public String getInput() {
        return input;
    }
}
