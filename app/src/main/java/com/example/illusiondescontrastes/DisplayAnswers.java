package com.example.illusiondescontrastes;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAnswers extends LinearLayout {
	public DisplayAnswers( Context context, ArrayList<ArrayList<Answer>> answers, ArrayList<ArrayList<Answer>> rightAnswers ) {
		super( context );

		this.setOrientation( VERTICAL );

		for (ArrayList<Answer> list : answers) {
			int index = answers.indexOf( list );

			TextView textView = new TextView( context );
			textView.setText( String.format( "Experiment number %d", index ) );
			this.addView( textView );

			for (int j = 0; j < list.size(); j++) {
				TextView textView2 = new TextView( context );

				textView2.setText( String.format( "Réponse sélectionnnée : %s          |          La réponse était %s", list.get(j).getValue(), rightAnswers.get(index).get(j).getValue() ) );
				this.addView( textView2 );
			}
		}
	}
}
