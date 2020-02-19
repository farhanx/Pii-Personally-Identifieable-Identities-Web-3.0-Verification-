/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description class for user identity with user attributes
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */
package com.farhan.piiverification.web.home.domains;

import org.springframework.web.multipart.MultipartFile;

public class UserIdentity {

    private String Name;
    private String Age;
    private String address;
    private MultipartFile photo;
    private String wallet;
    private String privatekey;

    //on verification get the target
    private String target;

    public MultipartFile  getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile  photo) {
        this.photo = photo;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
