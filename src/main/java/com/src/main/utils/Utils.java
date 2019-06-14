package com.src.main.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {


	static InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("application.properties");
    private static Properties properties = new Properties();


    public static void loadConfig(String fileName) {
        if (fileName == null) {
            System.out.println("loadConfig: config file name cannot be null");
        } else {
            try {
                System.out.println("loadConfig(): Loading config file: " + fileName );
               // final InputStream fis = Thread.currentThread().getClass().getClassLoader().getResourceAsStream(fileName);
                System.out.println(inputStream);
                properties.load(inputStream);

            } catch (FileNotFoundException fne) {
               System.out.println("loadConfig(): file name not found " +fne);
            } catch (IOException ioe) {
               System.out.println("loadConfig(): error when reading the config " +ioe);
            }
        }

    }


    public static String getStringProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            value = System.getProperty(key);
        }
        return value;
    }

    public static String getStringProperty(String key, String defaultVal) {
        String value = getStringProperty(key);
        return value != null ? value : defaultVal;
    }


    public static int getIntegerProperty(String key, int defaultVal) {
        String valueStr = getStringProperty(key);
        if (valueStr == null) {
            return defaultVal;
        } else {
            try {
                return Integer.parseInt(valueStr);

            } catch (Exception e) {
                System.out.println("getIntegerProperty(): cannot parse integer from properties file for: " + key + "fail over to default value: " + defaultVal+"Exc :- "+e);
                return defaultVal;
            }
        }
    }

    //initialize
    static {
        String configFileName = System.getProperty("application.properties");

        if (configFileName == null) {
            configFileName = "application.properties";
        }
        loadConfig(configFileName);

    }



    
}
