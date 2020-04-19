package com.example.illusiondescontrastes;

import android.view.View;

public class ExperimentButtonsController implements View.OnClickListener {

	private ExperimentController experimentController;

//	Methods

	/* on click methods. define comportment buttons will have on click */
	@Override
	public void onClick( View v ) {

		/* add the answer to the list of answers on the controller for coming comparison */
		switch ( v.getId() ) {
			case R.id.exp_buttons_less:
				this.experimentController.addAnswer( Answer.LIGHTER );
				break;
			case R.id.exp_buttons_equals:
				this.experimentController.addAnswer( Answer.EQUALS );
				break;
			case R.id.exp_buttons_more:
				this.experimentController.addAnswer( Answer.DARKER );
				break;
		}

		if (this.experimentController.canClick()) {		/* prevent the user to spam click the button and pass through all the tries */
			if (this.experimentController.getExperimentsCount() != this.experimentController.getMaxExperiments()) {		/* we are still on experiment or training */
				this.experimentController.newTry();		/* starting a new try */

				this.experimentController.increaseExperimentsCount();	/* increasing tries count */
			} else {	/* we ended our training or experiment */
				this.experimentController.endOfExperiment();	/* we end the current training or experiment */
			}
		}
	}

//	controller
	ExperimentButtonsController( ExperimentController experimentController ) {
		this.experimentController = experimentController;
	}
}
