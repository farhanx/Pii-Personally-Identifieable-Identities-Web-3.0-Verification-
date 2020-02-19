/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description An interface to implement other services
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */
package com.farhan.piiverification.web.home.services;

import com.farhan.piiverification.web.home.domains.UserIdentity;

public interface IdentityService {

    String getContractAddress();

    String getRegistryContractAddress();

    String getNetworkType();

    String submitIdentityonEthereum(UserIdentity userIdentity);

    UserIdentity getIdentityFromEthereum(String userAccount, String userKey);




}
