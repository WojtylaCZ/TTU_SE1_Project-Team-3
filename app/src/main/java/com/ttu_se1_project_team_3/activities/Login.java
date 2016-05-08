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
import com.ttu_se1_project_team_3.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Isaac on 2/24/2016.
 * <p/>
 * This activity is for the Login Page, the user will have two options,
 * - Login with their username and password
 * - Recover their password
 */
public class Login extends AppCompatActivity {
    Firebase db;
    EditText enter_email, enter_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enter_email = (EditText) findViewById(R.id.enter_user);
        enter_password = (EditText) findViewById(R.id.enter_password);
    }

    public void forgotPassword(View v) {
        Intent ForgotPassword = new Intent(this, ForgotPassword.class);
        startActivity(ForgotPassword);
    }


    //https://www.firebase.com/docs/android/guide/user-auth.html
    public void login(View v) {
        Intent home = new Intent(Login.this, Homepage.class);
        startActivity(home);
        //return;
        db = DBconn.getInstance().getFbConnection();

        final String email = enter_email.getText().toString();
        String password = enter_password.getText().toString();

        db.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User successfully authenticated with User ID: " + authData.getUid());
                User user = User.getInstance();
                user.setEmail(email);
                user.setFbUid(authData.getUid());

               // VOJTA: I am not sure if we will need to use or not, but it is prepared here
                //https://www.firebase.com/docs/android/guide/user-auth.html#section-storing
                Map<String, String> map = new HashMap<String, String>();
                map.put("login", email);
                db.child("users").child(authData.getUid()).setValue(map);



                Toast.makeText(Login.this, "Success.", Toast.LENGTH_LONG).show();
                Intent home = new Intent(Login.this, Homepage.class);
                startActivity(home);

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                System.err.println(firebaseError.toString());
                Toast.makeText(Login.this, "Wrong email or password.", Toast.LENGTH_LONG).show();
            }
        });


    }



}
