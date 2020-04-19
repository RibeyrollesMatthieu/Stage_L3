package com.example.illusiondescontrastes;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class Util {

    /* load the required property in the properties file*/
    static String getProperty(String key, Context context) throws IOException {
        Properties config = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");

        config.load(inputStream);

        return config.getProperty(key);
    }
}
