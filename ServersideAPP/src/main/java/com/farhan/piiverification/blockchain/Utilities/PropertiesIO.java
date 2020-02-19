/*
 * Personally identifiable information (PII) Verification Platform (Prototype)
 * @description This class helps in storing or querying properties in properties file
 * @author Farhan Hameed Khan <farhankhan@blockchainsfalcon.com> | <farhanhbk@hotmail.com>
 * @date 1-Oct-2019
 * @version 1.1.0
 * @link http://www.blockchainsfalcon.com
 */

package com.farhan.piiverification.blockchain.Utilities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesIO {

     /*
        USAGE: Details
        PropertiesIO p= new PropertiesIO("test.propertiess","src/main/resources/");

        p.saveProperties(new HashMap<String, String>() {{
            put("contractAddress", "dsjkhkjdshskjhsb");
            put("contractOwner", "jkdshkjhdskjhs");
        }});

        String val = p.loadPropertyByKey("contractOwner");

        p.loadProperties();
        */

    String filePath;
    String useResourceFolder = "";//"src/main/resources/";

    public PropertiesIO(String filePath)
    {
        this.filePath = filePath;
    }

    //if user need to use resource folder then use "src/main/resources/"
    public PropertiesIO(String filePath, String useResourceFolder)
    {
        this.filePath = filePath;

        this.useResourceFolder = useResourceFolder;
    }


    public Map<String,String> loadProperties()
    {

        Map<String,String> map = new HashMap<String, String>();
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(useResourceFolder+filePath)){//"path/to/config.properties")) {

            // load a properties file
            prop.load(input);

            prop.forEach((k, v) -> map.put(k.toString(),v.toString()));

            return map;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String loadPropertyByKey(String key)
    {

        String value="";
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(useResourceFolder+filePath)){//"path/to/config.properties")) {

            // load a properties file
            prop.load(input);

            value = prop.getProperty(key);

           return value;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return value;
    }


    public boolean saveProperties(Map<String, String> data) {


        String strResourcePath;

            Properties properties;

            try (OutputStream output = new FileOutputStream(useResourceFolder+filePath)) {//"path/to/config.properties")) {

                Properties prop = new Properties();

                for (Map.Entry<String, String> me : data.entrySet()) {
                    System.out.print(me.getKey() + ":");
                    System.out.println(me.getValue());
                    prop.setProperty(me.getKey(), me.getValue());
                }

                prop.store(output, "PropertiesDemo");

                return true;

            } catch (IOException io) {
                io.printStackTrace();
            }

        return false;
    }


}
