package com.farhan.piiverification.blockchain.contracts;

import org.web3j.tx.gas.StaticGasProvider;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class DeployGasProvider extends StaticGasProvider {

        public DeployGasProvider() {
            super(GAS_PRICE, GAS_LIMIT);
        }
}
