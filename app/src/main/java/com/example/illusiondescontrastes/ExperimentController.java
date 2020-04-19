package com.example.illusiondescontrastes;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ExperimentController {
	private final int SCREEN_WIDTH ;
	private final int SCREEN_HEIGHT ;

	private int maxExperiments;
	private int experiments_count;

	private boolean inExperiment;
	private boolean canClick;

	private ArrayList<Integer> answers;

	private Context context;
	private Experiment experiment;
	private Rectangles rectanglesView;

	private Handler handler;
	private Runnable removeRectanglesRunnable;

//	Getters and setters
	int getMaxExperiments( ) { return maxExperiments; }
	int getExperimentsCount() { return experiments_count; }
	void increaseExperimentsCount() { this.experiments_count ++; }
	boolean canClick() { return this.canClick; }
//	void setInExperiment() { this.inExperiment = true; }

//	Methods

	/* add answer to the list*/
	void addAnswer( Answer answer ) {
		this.answers.add( answer.getValue() );
	}

	/* write a file containing the answers */
	private void writeAnswersFile() throws IOException {
		BufferedWriter out = new BufferedWriter( new FileWriter( String.format( "%s.txt", this.OUTPUT_FILE_NAME ) ) );

		for ( int answer : this.answers) {
			out.write( answer );
			out.newLine();
		}
	}

	/* call whenever new rectangles are shown <=> for every new try */
	private void startNewRectanglesLoop( ) throws IOException {
		this.handler.removeCallbacks( this.removeRectanglesRunnable );
		this.handler.postDelayed( this.removeRectanglesRunnable, Integer.parseInt( Util.getProperty( "experiment_duration_per_try", this.context ) ) * 1_000 );
	}

	/* init the max experiments count allowed */
	private void initExpCount() {
		try {
			if (! this.inExperiment) this.maxExperiments = Integer.parseInt( Util.getProperty("training_tries", context) );
			else this.maxExperiments = Integer.parseInt( Util.getProperty( "experiment_tries", context ));
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		this.experiments_count = 1;
	}

	/* call whenever we need to add new rectangles */
	private void addRectangles( ) {
		this.rectanglesView = new Rectangles( this.context, this.SCREEN_WIDTH, this.SCREEN_HEIGHT );
		this.experiment.addView( rectanglesView );

		try { this.startNewRectanglesLoop(); }
		catch ( IOException e ) { e.printStackTrace(); }

	}

	void newExperiment() {
		this.initExpCount();
		this.experiment.removeAllViews();
		this.addRectangles();
		this.experiment.addButtons();
	}

	void endOfExperiment( ) {
		this.experiment.removeAllViews();

		if (inExperiment) {
			LayoutInflater.from( this.context ).inflate( R.layout.activity_main, this.experiment, true );
			try {
				this.writeAnswersFile();
			} catch ( IOException e ) { e.printStackTrace( ); }
		} else {
			this.experiment.addView( new TrainingToExperimentView( this.context, this ) );
		}

		inExperiment = ! inExperiment;
	}

	void newTry() {
		this.canClick = false;
		experiment.removeView( rectanglesView );

		new Handler( Looper.getMainLooper() ).postDelayed( new Runnable( ) {
			@Override
			public void run( ) {
				addRectangles();
				canClick = true;
			}
		}, 1 * 1_000 );
	}

	private void initTimers() {
		this.handler = new Handler( Looper.getMainLooper() );
		this.removeRectanglesRunnable = new Runnable( ) {
			@Override
			public void run( ) {
				experiment.removeView( rectanglesView );
			}
		};
	}

//	constructor
	ExperimentController ( Context context, final Experiment experiment, boolean inExperiment)  {
		this.maxExperiments = 0;
		this.inExperiment = inExperiment;
		this.canClick = true;
		this.answers = new ArrayList< Integer >();

		this.initTimers();

		this.context = context;
		this.experiment = experiment;

		this.SCREEN_WIDTH = this.experiment.getResources( ).getDisplayMetrics( ).widthPixels;
		this.SCREEN_HEIGHT = this.experiment.getResources().getDisplayMetrics().heightPixels;
	}
}
