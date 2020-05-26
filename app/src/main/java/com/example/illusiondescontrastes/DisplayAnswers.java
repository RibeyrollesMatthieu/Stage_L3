package com.example.illusiondescontrastes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayAnswers extends LinearLayout {
	private Button button;

	public DisplayAnswers( Context context, ArrayList<ArrayList<Integer>> alphas, ArrayList< Integer > outLeft, ArrayList<ArrayList<Answer>> answers, ArrayList<ArrayList<Answer>> rightAnswers ) {
		super( context );

		/* get layout from xml file */
		LayoutInflater.from( context ).inflate( R.layout.display_answers , this);
		LinearLayout _container = findViewById( R.id.display_layout );

		for (int i = 0; i < answers.size(); i++) {
			ArrayList<Answer> list = answers.get( i );

			TextView textView = new TextView( context );
			String currentText = String.format( "\nSérie n°%d:\t", i );

			if (i == 0) {
				currentText = String.format( "\nEntrainement:\t" );
			}

			currentText += String.format( "Valeur de l'encadrant: %d\t Valeur du référent:%d", outLeft.get( i ), 255/2);

			textView.setText( currentText );
			_container.addView( textView );

			TextView essai = new TextView( context );	// display of tries: 0, 1, 2
			TextView valRep = new TextView( context ); 	// display of val / rep
			TextView userAnswer = new TextView( context );	// display of answers picked by user

			String essaiText = String.format( "Essai:\t\t\t\t\t" );
			String userAnswerText = "Choisies:\t\t";
			String valRepText = "Val/Rep\t";

			for (int j = 0; j < list.size(); j++) {

				essaiText += String.format( "%d\t\t\t\t\t", j );
				valRepText += String.format( "%d/%s\t\t", 254 - alphas.get( i ).get( j ), rightAnswers.get(i).get(j).getValue() ) ;

				essai.setText( essaiText );
				userAnswerText += String.format( "%s\t\t\t\t\t\t", answers.get( i ).get( j ).getValue() );
			}
			valRep.setText( valRepText );
			userAnswer.setText( userAnswerText );
			_container.addView( essai );
			_container.addView( valRep );
			_container.addView( userAnswer );

			button = new Button( context );
			button.setText( "Retour au menu" );
			button.setOnClickListener( v -> (( Activity ) getContext()).finish() );
		}

		_container.addView( button );
	}
}
