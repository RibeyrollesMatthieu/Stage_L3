package com.example.illusiondescontrastes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    Methods

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_start_button:    /* starting the presentation of the experiment */
                startActivity(new Intent(MainActivity.this, PresentationActivity.class));
                break;

            case R.id.home_settings:    /* going to the settings panel */
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
        }
    }

    /* init components, creating them and linking them to the class on click method */
    private void initComponents() {
        Button startButton = findViewById( R.id.home_start_button );
        ImageView settingsImage = findViewById( R.id.home_settings );

        startButton.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            LocalStorage.init( this );
        } catch ( IOException e ) { e.printStackTrace( ); }

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        initComponents();
    }
}
