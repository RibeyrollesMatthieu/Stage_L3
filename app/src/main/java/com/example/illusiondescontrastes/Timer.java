package com.example.illusiondescontrastes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Timer extends RelativeLayout implements View.OnClickListener {
	private int timerCount;

	private ExperimentController controller;

//	private void start() {
//		final TextView textView = findViewById( R.id.timer_text );
//
//		new CountDownTimer( (this.timerCount + 1) * 1_000, 1_000 ) {
//			@Override
//			public void onTick( long millisUntilFinished ) {
//				int timeLeft = (int) (millisUntilFinished / 1_000);
//
//				textView.setText( (timeLeft > 0) 	? String.valueOf( timeLeft )
//											 		: "C'est partit !");
//			}
//
//			@Override
//			public void onFinish( ) {
//				controller.newExperiment();
//			}
//		}.start();
//	}

	public Timer( Context context, ExperimentController controller ) {
		super( context );

		LayoutInflater.from( context ).inflate( R.layout.timer, this );
		this.controller = controller;
//		this.timerCount = Integer.parseInt( LocalStorage.getValue( "before_experiment_timer_duration" ) );
//		this.start();

		final Button nextButton = findViewById( R.id.timer_button );
		nextButton.setOnClickListener( this );
	}

	@Override
	public void onClick( View v ) {
		controller.newExperiment();
	}
}
