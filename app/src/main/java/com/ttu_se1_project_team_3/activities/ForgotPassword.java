package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ttu_se1_project_team_3.R;

/**
 * Created by wojtyla on 3/2/16.
 *
 * Allows the user to retrieve a forgotten password.
 */
public class ForgotPassword extends AppCompatActivity {
    Firebase db;
    EditText input_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Firebase.setAndroidContext(this);


        input_email = (EditText) findViewById(R.id.email_reset);
    }

    public void ForgotPassword(View v) {

        String email = input_email.getText().toString();

        db = DBconn.getInstance().getFbConnection();
        db.resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                System.out.println("Email sent successfully");
                Toast.makeText(ForgotPassword.this, "Email sent successfully. Please check your spam folder if you do not receive it.", Toast.LENGTH_LONG).show();
                Intent welcome = new Intent(ForgotPassword.this,Welcome.class);
                startActivity(welcome);
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(ForgotPassword.this, "ERROR: Email NOT Sent.", Toast.LENGTH_LONG).show();
                System.err.println(firebaseError.toString());
            }
        });
    }


}


