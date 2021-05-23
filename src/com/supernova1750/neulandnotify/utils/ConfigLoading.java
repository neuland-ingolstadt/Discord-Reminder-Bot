package com.supernova1750.neulandnotify.utils;

import com.supernova1750.neulandnotify.Bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoading {

    public static void loadConfig() {
        String fileName = "config.properties";
        String head = "Config file for reminder bot";
        Properties prop;
        try {
            File f = new File(fileName);
            prop = new Properties();
            if(f.createNewFile())
                System.out.println("Created new Config file");
            FileInputStream fis = new FileInputStream(f);
            prop.load(fis);

            for (ConfigEntries configEntries : ConfigEntries.values()) {
                if(prop.getProperty(configEntries.name) == null) {
                    prop.setProperty(configEntries.name, configEntries.value);
                    continue;
                }
                Bot.properties.put(configEntries.name, prop.getProperty(configEntries.name));
            }
            FileOutputStream fos;
            fos = new FileOutputStream(f);
            prop.store(fos, head);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
