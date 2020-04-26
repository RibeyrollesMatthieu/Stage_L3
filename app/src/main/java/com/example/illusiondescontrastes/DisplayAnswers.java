package com.example.illusiondescontrastes;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAnswers extends ScrollView {
	public DisplayAnswers( Context context, ArrayList<ArrayList<Answer>> answers, ArrayList<ArrayList<Answer>> rightAnswers ) {
		super( context );

		LayoutInflater.from( context ).inflate( R.layout.display_answers , this);

		LinearLayout linearLayout = findViewById( R.id.display_layout );

		for (ArrayList<Answer> list : answers) {
			int index = answers.indexOf( list );

			TextView textView = new TextView( context );
			textView.setText( String.format( "Experiment number %d", index ) );
			linearLayout.addView( textView );

			for (int j = 0; j < list.size(); j++) {
				TextView textView2 = new TextView( context );

				textView2.setText( String.format( "Réponse sélectionnnée : %s          |          La réponse était %s", list.get(j).getValue(), rightAnswers.get(index).get(j).getValue() ) );
				linearLayout.addView( textView2 );
			}
		}
	}
}
