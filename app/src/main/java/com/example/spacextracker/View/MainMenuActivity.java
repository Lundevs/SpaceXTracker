package com.example.spacextracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spacextracker.R;
import com.google.gson.Gson;

public class MainMenuActivity extends AppCompatActivity {


    private Button pastLaunchButton;
    private Button futureLaunchButton;
    private Button statsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        pastLaunchButton = findViewById(R.id.buttonPastLaunch);
        futureLaunchButton = findViewById(R.id.buttonFutureLaunch);
        statsButton = findViewById(R.id.buttonStats);
        pastLaunchButton.setOnClickListener(new ListenerPastLaunch());
        futureLaunchButton.setOnClickListener(new ListenerFutureLaunch());
        statsButton.setOnClickListener(new ListenerStats());

    }

    public class ListenerPastLaunch implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainMenuActivity.this, LaunchActivity.class);
            intent.putExtra("launchType", 1);
            startActivity(intent);
        }
    }

    public class ListenerFutureLaunch implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainMenuActivity.this, LaunchActivity.class);
            intent.putExtra("launchType", 2);
            startActivity(intent);
        }
    }

    public class ListenerStats implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainMenuActivity.this, LaunchActivity.class); // to modify => new activity
            startActivity(intent);
        }
    }
}
