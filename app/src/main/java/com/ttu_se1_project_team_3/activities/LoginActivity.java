package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ttu_se1_project_team_3.R;

/**
 * Created by Isaac on 2/24/2016.
 * <p/>
 * This activity is for the Login Page, the user will have two options,
 * - Login with their username and password
 * - Recover their password
 */
public class LoginActivity extends AppCompatActivity {
    Firebase db;
    EditText enter_email, enter_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        enter_email = (EditText) findViewById(R.id.enter_user);
        enter_password = (EditText) findViewById(R.id.enter_password);
    }

    public void login(View v) {
        db = DBconn.getInstance().getFbConnection();

        String email = enter_email.getText().toString();
        String password = enter_password.getText().toString();


        db.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User successfully authenticated with User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Toast.makeText(LoginActivity.this, "Success.", Toast.LENGTH_LONG).show();
                Intent home = new Intent(LoginActivity.this, HomepageActivity.class);
                startActivity(home);

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                System.err.println(firebaseError.toString());
                Toast.makeText(LoginActivity.this, "Wrong email or password.", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void forgotPassword(View v) {
        Intent forgotPassword = new Intent(this, ForgotPassword.class);
        startActivity(forgotPassword);
    }

}
