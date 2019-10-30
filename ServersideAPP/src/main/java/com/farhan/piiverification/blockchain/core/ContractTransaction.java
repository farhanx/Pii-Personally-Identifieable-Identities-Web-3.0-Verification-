package com.farhan.piiverification.blockchain.core;

public interface ContractTransaction {

    public boolean deployContract();

    public String insertInContract();

    public String retrieveFromContract();

    public String getSmartContractAddress();

    public boolean isContractExist();

    public boolean deployContractVersionRegistry(String version_name,String contract_address);

}
