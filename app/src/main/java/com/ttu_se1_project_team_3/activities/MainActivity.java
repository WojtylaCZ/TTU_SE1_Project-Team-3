package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.ttu_se1_project_team_3.R;

/**
 * The MainActivity is redirecting to the Welcome Activity, this is done to separate
 * the roles of each activity more precisely.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        Intent welcome = new Intent(this, WelcomeActivity.class);
        startActivity(welcome);
    }
}
