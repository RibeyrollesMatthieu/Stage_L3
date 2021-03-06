package com.example.illusiondescontrastes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
class TrainingToExperimentView extends RelativeLayout implements View.OnClickListener {

	private ExperimentController experimentController;

//	Methods

	@Override
	public void onClick( View v ) {
		this.experimentController.displayTimer();	/* start a new experiment */
	}

//	controller
	TrainingToExperimentView( Context context, ExperimentController experimentController ) {
		super( context );

		/* attach the xml file to the current view */
		LayoutInflater.from( context ).inflate(  R.layout.start_experiment, this, true );
		this.experimentController = experimentController;

		/* loading text from the properties file */
		(( TextView ) findViewById( R.id.start_experiment_text )).setText(  LocalStorage.getValue( "message_between_training_and_experiment") );

		/* linking button to the class on click method */
		findViewById( R.id.start_experiment_start_button ).setOnClickListener( this );
	}
}
