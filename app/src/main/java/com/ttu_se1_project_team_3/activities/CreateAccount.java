package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ttu_se1_project_team_3.R;

import java.util.Map;

/**
 * This class manages the page that allows the user to create a new account given an email
 * and matching passwords.
 */
public class CreateAccount extends AppCompatActivity {
    EditText input_email, input_password, confirm_password;
    Firebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

    }

    /**
     * This function is called when the user clicks "Create Account",
     * it allows the user to create an account.
     *
     * @param view
     */
    public void createNewAccount(View view) {

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if(input_password.getText().toString().trim().matches(confirm_password.getText().toString().trim())) {
            db = DBconn.getInstance().getFbConnection();

            db.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    System.out.println("Successfully created user account with uid: " + result.get("uid"));
                    Toast.makeText(CreateAccount.this, "Account created. You may login now.", Toast.LENGTH_LONG).show();
                    Intent welcome = new Intent(CreateAccount.this, Welcome.class);
                    startActivity(welcome);
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    Toast.makeText(CreateAccount.this, "ERROR. Account NOT created.", Toast.LENGTH_LONG).show();
                    System.err.println(firebaseError.toString());
                }
            });
        }else{
            Toast.makeText(CreateAccount.this, "ERROR: Passwords do not match.", Toast.LENGTH_LONG).show();
        }
    }
}
