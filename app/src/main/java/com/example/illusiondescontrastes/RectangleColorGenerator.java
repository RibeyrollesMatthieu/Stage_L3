package com.example.illusiondescontrastes;

import java.util.ArrayList;
import java.util.Collections;

class RectangleColorGenerator {
	private static ArrayList< Answer > rightAnswers;
	private static ArrayList< Integer > alphas;
	private static ArrayList<Couple> couples = new ArrayList<>(  );

	static ArrayList< Answer > getRightAnswers( ) {
		return rightAnswers;
	}

	private static void shuffle() {
		Collections.shuffle( couples );

		for (Couple couple : couples) {
			alphas.add( couple.getAlpha() );
			rightAnswers.add( couple.getAnswer() );
		}
	}

	static ArrayList< Integer > generateColors( int tries, int range ) {
		alphas = new ArrayList<>();
		rightAnswers = new ArrayList<>();

		final int BASE_ALPHA = 255 / 2;
		int scale = (range * 255 / 100) / tries * 2;
		int currentScale;

		for (int i = 0; i < tries; i++) {
			currentScale = i * scale;
			Answer currentAnswer;

			if (i == 0) {
				currentAnswer = Answer.EQUALS;
			} else if (i <= tries / 2) {
				currentAnswer = Answer.LIGHTER;
			} else {
				currentAnswer = Answer.DARKER;
			}

			int currentAlpha = (i <= tries / 2) ? BASE_ALPHA - currentScale : BASE_ALPHA + currentScale;

			couples.add( new Couple( currentAlpha, currentAnswer ) );
		}

		shuffle();
		return alphas ;
	}
}

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