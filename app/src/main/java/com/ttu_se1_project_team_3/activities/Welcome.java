package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ttu_se1_project_team_3.R;

/**
 * Created by Isaac on 2/23/2016.
 *
 * The WelcomeActivity gives the user the opportunity to either create an account or
 * login.
 */
public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goToLogin(View v) {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    public void goToCreateAccount(View v) {
        Intent createAccount = new Intent(this, CreateAccount.class);
        startActivity(createAccount);
    }
}
