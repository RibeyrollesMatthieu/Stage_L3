package com.example.illusiondescontrastes;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    /* value of the edit text   /     string linked to in the properties file*/
    ArrayList<String[]> _inputs;

    private void save() {
            Toast.makeText( this.getApplicationContext(), "Les valeurs ont bien été enregistrées.", Toast.LENGTH_SHORT ).show();
            changeValuesInStorage();
            this.finish();
    }

    private void changeValuesInStorage() {
        for (String[] _input : _inputs) {
            _input[0] = ((EditText) (findViewById( Integer.parseInt( _input[0] ) ))).getText().toString().trim();
            String input = _input[0];

            int value = ( input.length() != 0 ) ? Integer.parseInt( input ) : 0;

            if (value > 0) {
                LocalStorage.setValue( _input[1], _input[0] );
            }
        }
    }

    private void initComponents() {
        /* textView_id, editText_id, properties_yexy_key, properties_value_key*/
        initComponent( R.id.settings_tries_for_experiment_text, R.id.settings_experiment_tries_number, "settings_experiment_tries_text", "experiment_number_of_tries" );
        initComponent( R.id.settings_tries_for_training_text, R.id.settings_training_tries_number,"settings_training_tries_text", "training_number_of_tries" );
        initComponent( R.id.settings_time_before_vanish_text, R.id.settings_time_before_vanish_part_value, "settings_time_before_vanish_text", "time_before_vanish" );
        initComponent( R.id.settings_alphas_text, R.id.settings_alphas_value, "settings_alphas_range_text", "range_for_alphas" );
        initComponent( R.id.settings_number_of_experiments_text, R.id.settings_number_of_experiments_value, "settings_number_of_experiments_text", "number_of_experiments" );

        Button saveButton = findViewById( R.id.settings_save_button );
        saveButton.setOnClickListener( this );
    }

    private void initComponent(int _tvID, int _etID, String _tvKEY, String _etKEY) {
        TextView _tv = findViewById( _tvID );
        EditText _et = findViewById( _etID );
        _tv.setText( LocalStorage.getValue( _tvKEY ) );
        _et.setHint( LocalStorage.getValue( _etKEY ) );

        _inputs.add( new String[]{ "" + _etID, _etKEY} );

    }

    @Override
    public void onWindowFocusChanged( boolean hasFocus ) {
        super.onWindowFocusChanged( hasFocus );
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onClick( View v ) {
        save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_settings);

        _inputs = new ArrayList<>(  );
        initComponents();
    }
}
