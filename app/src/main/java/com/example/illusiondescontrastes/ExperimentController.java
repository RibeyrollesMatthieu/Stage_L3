package com.example.illusiondescontrastes;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.ArrayList;

class ExperimentController {
	private final int SCREEN_WIDTH ;
	private final int SCREEN_HEIGHT ;

	private int alpha_range;
	private int maxExperiments;
	private int try_count;
	private int number_of_experiments;
	private int current_experiment;

	private boolean inExperiment;
	private boolean canClick;

	private ArrayList<ArrayList<Answer>> answersList;
	private ArrayList<ArrayList<Answer>> rightAnswers;

	private ArrayList< Answer > answers;
	private ArrayList< Integer > alphas;

	private Context context;
	private Experiment experiment;
	private Rectangles rectanglesView;

	private Handler handler;
	private Runnable removeRectanglesRunnable;

//	Getters and setters
	int getMaxExperiments( ) { return maxExperiments; }
	int getExperimentsCount() { return try_count; }
	void increaseExperimentsCount() { this.try_count++; }
	boolean canClick() { return this.canClick; }
//	void setInExperiment() { this.inExperiment = true; }

//	Methods

	/* add answer to the list*/
	void addAnswer( Answer answer ) {
		this.answers.add( answer );
	}

	/* write a file containing the answers */
	private void writeAnswersFile() {
		new FileWriter( this.context ).createExternalStoragePrivatePicture();
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
		this.try_count = 1;
		this.current_experiment = 0;
	}

	/* call whenever we need to add new rectangles */
	private void addRectangles( ) {
		this.rectanglesView = new Rectangles( this.context, this.SCREEN_WIDTH, this.SCREEN_HEIGHT, this.alphas.get( this.try_count - 1 ) );
		this.experiment.addView( rectanglesView );

		try { this.startNewRectanglesLoop(); }
		catch ( IOException e ) { e.printStackTrace(); }

	}

	/* call for starting a new experiment */
	void newExperiment() {
		this.initExpCount();
		RectangleColorGenerator.generateColors( this.maxExperiments, this.alpha_range );
		this.alphas = RectangleColorGenerator.getAlphas();
		this.answers = new ArrayList<>(  );
		this.experiment.removeAllViews();
		this.addRectangles();
		this.experiment.addButtons();
	}

	/* call when an experiment ends */
	void endOfExperiment( ) {
		this.experiment.removeAllViews();
		this.answersList.add( this.answers );
		this.rightAnswers.add(RectangleColorGenerator.getRightAnswers());

		if (inExperiment) {

			this.writeAnswersFile();
			this.experiment.addView( new DisplayAnswers( this.context, this.answersList, this.rightAnswers ) );
		} else {
			this.experiment.addView( new TrainingToExperimentView( this.context, this ) );
		}

		inExperiment = ! inExperiment;
	}

	void newTry() {
		this.canClick = false;
		experiment.removeAllViews();

		new Handler( Looper.getMainLooper() ).postDelayed( new Runnable( ) {
			@Override
			public void run( ) {
				addRectangles();
				experiment.addButtons();
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
		try {
			this.number_of_experiments = Integer.parseInt( Util.getProperty( "number_of_experiments", this.context ) );
		} catch ( IOException e ) { e.printStackTrace( ); }
		this.maxExperiments = 0;
		this.inExperiment = inExperiment;
		this.canClick = true;
		this.alpha_range = 30;

		this.initTimers();

		this.context = context;
		this.experiment = experiment;
		this.answersList = new ArrayList<>(  );
		this.rightAnswers = new ArrayList<>(  );

		try {
			this.alpha_range = Integer.parseInt( Util.getProperty( "opacity_range", this.context ) );
		} catch ( IOException e ) { e.printStackTrace( ); }

		this.SCREEN_WIDTH = this.experiment.getResources( ).getDisplayMetrics( ).widthPixels;
		this.SCREEN_HEIGHT = this.experiment.getResources().getDisplayMetrics().heightPixels;
	}
}
