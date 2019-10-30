package com.farhan.piiverification.web.home.services;

import com.farhan.piiverification.web.home.domains.UserIdentity;

public interface IdentityService {

    String getContractAddress();

    String getRegistryContractAddress();

    String getNetworkType();

    String submitIdentityonEthereum(UserIdentity userIdentity);

    UserIdentity getIdentityFromEthereum(String userAccount, String userKey);




}
