/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.blockchain.contracts;

import org.web3j.tx.gas.StaticGasProvider;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class DeployGasProvider extends StaticGasProvider {

        public DeployGasProvider() {
            super(GAS_PRICE, GAS_LIMIT);
        }
}
