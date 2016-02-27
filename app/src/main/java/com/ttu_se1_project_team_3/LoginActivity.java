package com.ttu_se1_project_team_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Isaac on 2/24/2016.
 *
 * This activity is for the Login Page, the user will have two options,
 * - Login with their username and password
 * - Recover their password
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void login(View v) {
        Intent home = new Intent(this, HomepageActivity.class);
        startActivity(home);
    }
}
