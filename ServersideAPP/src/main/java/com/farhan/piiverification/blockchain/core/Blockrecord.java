/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description  Interface to handle blockchain records e.g. transaction.
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */


package com.farhan.piiverification.blockchain.core;

public interface Blockrecord {

    String getTransactionHash();
    String getBlockNumber();
    String getTransactionCreatorAddress();


}
