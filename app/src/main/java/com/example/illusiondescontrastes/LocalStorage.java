package com.example.illusiondescontrastes;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

abstract class LocalStorage {
	private static HashMap<String, String> storage;

	static String getValue( String key ) {
		return storage.get( key );
	}

	static void setValue( String key, String value ) {
		storage.put( key, value );
	}

	static void init( Context context ) throws IOException {
		storage = new HashMap<>(  );
		Properties config = new Properties(  );
		AssetManager assetManager = context.getAssets();


		InputStream inputStream = assetManager.open( "config.properties" );

		config.load(inputStream);

		for ( Object key : config.keySet() ){
			storage.put( key.toString(), config.getProperty( key.toString() ) );
		}

		inputStream.close();
	}
}
