/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description This is the main controller for the server side application.
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.web.home.controller;

import com.farhan.piiverification.web.home.domains.UserIdentity;
import com.farhan.piiverification.web.home.services.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController

@Controller
public class IdentityController {

    private IdentityService identityService;
    protected static final Logger log = LoggerFactory.getLogger(IdentityController.class);


    @Autowired
    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }
//    @RequestMapping("/")

   // @RequestMapping(value = "/index", method = RequestMethod.GET)
   //@GetMapping({"hello"})
   //@ResponseBody

    // INDEX PAGE
    @RequestMapping({"/",""})
    public String index(Model model)
    {
        model.addAttribute("primaryContractAddress",identityService.getContractAddress());
        model.addAttribute("registryContractAddress",identityService.getRegistryContractAddress());
        model.addAttribute("networktype", identityService.getNetworkType());

        return "index";
    }

    // VERIFY PAGE
    @RequestMapping({"/verify",""})
    public String verifyPage(Model model)
    {
        model.addAttribute("mymodel",identityService.getContractAddress());

        return "verify";
    }


    @PostMapping(value = "/identityadd",produces = "application/json")
    public ResponseEntity<String> addIdentity(UserIdentity userIdentity) {

        String jsonObj = "";

        log.info(userIdentity.getAge()+" "+ userIdentity.getAddress()+" "+userIdentity.getName()+" "+userIdentity.getWallet());

        if(userIdentity.getAddress().length()<2 || userIdentity.getAge().length()==0 || userIdentity.getName().length()<1 ||
        userIdentity.getWallet().length()<21)
        {
            jsonObj= "{\"code\":\"0\",\"status\":\"Invalid Wallet or connection issue\"}";
            log.warn(jsonObj);
        }
        else
        {
            String transaction = identityService.submitIdentityonEthereum(userIdentity);
            if(transaction.isEmpty())
            {
                jsonObj= "{\"code\":0,\"status\":\"Invalid Wallet or connection issue\"}";
                log.info(jsonObj);

            }
            else {

                jsonObj = "{\"code\":1,\"status\":\"" + transaction + "\",\"contract_address\":\""+identityService.getContractAddress()+"\"}";
                log.warn(jsonObj);
            }
        }

        return ResponseEntity.ok(jsonObj);
    }


    @PostMapping("/verifysubmit")
    public String verifyIdentitySubmit(UserIdentity userIdentity,Model model) {
        log.info(userIdentity.getAge()+" "+ userIdentity.getAddress()+" "+userIdentity.getName()+" "+userIdentity.getWallet());
        //


        UserIdentity transaction = identityService.getIdentityFromEthereum(userIdentity.getWallet(),userIdentity.getPrivatekey());

        if(transaction==null) {
            model.addAttribute("transaction", "Contract not found");
        }
        else if(userIdentity.getTarget().compareToIgnoreCase("name")==0) {

            if (transaction.getName().compareToIgnoreCase(userIdentity.getName()) == 0) {
                model.addAttribute("transaction", "Name is verified");
            }
            else
                model.addAttribute("transaction", "Name is not verified");
        }
        else if(userIdentity.getTarget().compareToIgnoreCase("age")==0) {

            if (transaction.getAge().compareToIgnoreCase(userIdentity.getAge()) == 0) {
                model.addAttribute("transaction", "Age is verified");
            }
            else
                model.addAttribute("transaction", "Age is not verified");
        }
        else if(userIdentity.getTarget().compareToIgnoreCase("address")==0) {

            if (transaction.getAddress().compareToIgnoreCase(userIdentity.getAddress()) == 0) {
                model.addAttribute("transaction", "Address is verified");
            }
            else
                model.addAttribute("transaction", "Address is not verified");
        }
        else
        {
            model.addAttribute("transaction", "Incorrect Detail Provided");
        }

        return "verification_status";
    }


}
