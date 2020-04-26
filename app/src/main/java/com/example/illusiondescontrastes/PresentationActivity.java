package com.example.illusiondescontrastes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class PresentationActivity extends Activity implements View.OnClickListener {

//    Methods

    /* start new experiment view */
    @Override
    public void onClick( View v ) {
        this.setContentView( new Experiment( this ) );
    }

    /* init the components by creating them and linking them to the class on click listener method */
    private void initComponents() {
        Button start = findViewById( R.id.presentation_start_button );
        start.setOnClickListener( this );

        TextView presentation = findViewById( R.id.presentation_text );
        presentation.setText( LocalStorage.getValue( "experiment_presentation" ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

        setContentView(R.layout.activity_presentation);

        initComponents();
    }
}
