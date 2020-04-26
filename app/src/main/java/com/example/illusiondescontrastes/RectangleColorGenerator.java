package com.example.illusiondescontrastes;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

class RectangleColorGenerator {
	private static ArrayList< Answer > rightAnswers;
	private static ArrayList< Integer > alphas;
	private static ArrayList<Couple> couples = new ArrayList<>(  );

	/*  return the list containing all the alpha values for current experiment */
	static ArrayList< Integer > getAlphas( ) { return alphas; }

	/* return the list containing all the correct answers for current experiment */
	static ArrayList< Answer > getRightAnswers( ) { return rightAnswers; }

	/* shuffle the couples list and add content to alpha and answers lists */
	private static void shuffle() {
		Collections.shuffle( couples );

		for (Couple couple : couples) {
			alphas.add( couple.getAlpha() );
			rightAnswers.add( couple.getAnswer() );
		}
	}

	/* generate colors and answers for current experiment */
	static void generateColors( int tries, int range ) {
		/* initialize the lists we gonna use in there */
		alphas = new ArrayList<>();
		rightAnswers = new ArrayList<>();
		couples = new ArrayList<>(  );

		final int BASE_ALPHA = 255 / 2;	// starting alpha. Mid alpha (0.5)
		int scale = (range * 255 / 100) / tries * 2;	// scale between each alpha value

		int currentScale;

		for (int i = 0; i < tries; i++) {
			currentScale = i * scale;

			/* ça vient sans doute de là*/
			int currentAlpha = (i <= tries / 2) ? BASE_ALPHA - currentScale : BASE_ALPHA + currentScale;

			Log.d( "teub de black", " caca    " + currentAlpha );

			Answer currentAnswer = (currentAlpha < 127)	? Answer.LIGHTER
														: (currentAlpha == 127) ? Answer.EQUALS : Answer.DARKER;

//			/* create alpha and answer to make as much less than more, and one equals */
//			Answer currentAnswer = (i <= tries / 2) ? (i == 0) ? Answer.EQUALS : Answer.LIGHTER
//													:  Answer.DARKER;


			couples.add( new Couple( currentAlpha, currentAnswer ) );
		}

		shuffle();
	}
}

/* class couple. used to bind alpha and answer */
class Couple {
	private int alpha;
	private Answer answer;

	int getAlpha() { return this.alpha; }
	Answer getAnswer() { return this.answer; }

	Couple(int alpha, Answer answer) {
		this.alpha = alpha;
		this.answer = answer;
	}
}