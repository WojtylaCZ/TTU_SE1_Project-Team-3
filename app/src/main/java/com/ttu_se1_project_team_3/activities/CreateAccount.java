package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ttu_se1_project_team_3.R;

import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    EditText input_email, input_password;
    Firebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        input_email = (EditText)findViewById(R.id.input_email);
        input_password = (EditText)findViewById(R.id.input_password);
    }

    public void createNewAccount (View view){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        System.out.println("USER:"+email+" pw: "+password);

        db = DBconn.getInstance().getFbConnection();

        db.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                System.out.println(firebaseError.toString());
                // there was an error
            }
        });


    }
}
