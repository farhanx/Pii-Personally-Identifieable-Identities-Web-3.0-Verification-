/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description  Interface of Important functionality require for any transaction,
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.blockchain.core;

public interface ContractTransaction {

    public boolean deployContract();

    public String insertInContract();

    public String retrieveFromContract();

    public String getSmartContractAddress();

    public boolean isContractExist();

    public boolean deployContractVersionRegistry(String version_name,String contract_address);

}
