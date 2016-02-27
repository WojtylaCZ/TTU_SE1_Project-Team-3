package com.ttu_se1_project_team_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Isaac on 2/23/2016.
 *
 * The WelcomeActivity gives the user the opportunity to either create an account or
 * login.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
    }

    public void goToLogin(View v) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    public void goToCreateAccount(View v) {

    }
}
