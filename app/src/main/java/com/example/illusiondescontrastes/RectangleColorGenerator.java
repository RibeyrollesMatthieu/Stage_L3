package com.example.illusiondescontrastes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class RectangleColorGenerator {
	void initColors ( int tries, boolean darkBackground, boolean smallSize ) {
		if ( darkBackground ) {

		}

		if ( smallSize ) {

		}
	}

	static ArrayList< Integer > generateColors( int tries, int range ) {
		ArrayList< Integer > colors = new ArrayList<>();
		final int BASE_ALPHA = 255 / 2;
		int scale = (range * 255 / 100) / tries * 2;
		int currentScale;

		for (int i = 0; i < tries; i++) {
			currentScale = i * scale;

			colors.add( (i <= tries / 2) ? BASE_ALPHA - currentScale : BASE_ALPHA + currentScale);
		}

		Collections.shuffle( colors );

		return colors ;
	}
}
