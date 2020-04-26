package com.example.illusiondescontrastes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Experiment extends RelativeLayout {

	private ExperimentButtonsController experimentButtonsController;

//	Methods

	/* initialize the buttons and the on click events */
	private void initButtons() {
		Button less = findViewById( R.id.exp_buttons_less );
		Button equals = findViewById( R.id.exp_buttons_equals );
		Button more = findViewById( R.id.exp_buttons_more );

		less.setText( LocalStorage.getValue( "less_button_text") );
		equals.setText( LocalStorage.getValue( "equal_button_text" ));
		more.setText( LocalStorage.getValue( "more_button_text" ) );

		less.setOnClickListener( this.experimentButtonsController );
		equals.setOnClickListener( this.experimentButtonsController );
		more.setOnClickListener( this.experimentButtonsController );
	}

	/* add the buttons xml file to the current view */
	void addButtons() {
		LayoutInflater.from( this.getContext() ).inflate( R.layout.experiment_buttons, this, true );
		this.initButtons();
	}

//	Constructor
	public Experiment( Context context )  {
		super( context );

		/* define the controllers linked to the view */
		ExperimentController controller = new ExperimentController( context, this, false );
		this.experimentButtonsController = new ExperimentButtonsController( controller );

		/* start a new experiment */
		controller.displayTimer();
	}
}
