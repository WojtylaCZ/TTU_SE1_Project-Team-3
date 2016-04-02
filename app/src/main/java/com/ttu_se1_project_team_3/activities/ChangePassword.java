package com.ttu_se1_project_team_3.activities;

/**
 * Created by Zakery on 4/1/2016.
 */

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
import com.firebase.client.core.view.Change;
import com.ttu_se1_project_team_3.R;
public class ChangePassword extends AppCompatActivity {
/*email: input_cp_email
old pass: input_old_password
new pass: input_new_password
new pass confirm: confirm_new_password*/

   EditText input_email, input_password, input_confirm_password, input_old_password;
    Firebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Firebase.setAndroidContext(this);

        input_email = (EditText) findViewById(R.id.input_cp_email);
        input_password = (EditText) findViewById(R.id.input_new_password);
        input_confirm_password = (EditText) findViewById(R.id.confirm_new_password);
        input_old_password = (EditText) findViewById(R.id.input_old_password);
    }

    public void ChangePassword(View v) {
        String old_password = input_old_password.getText().toString();
        String email = input_email.getText().toString();
        String new_password = input_password.getText().toString();
        String confirm_password = input_confirm_password.getText().toString();

        if (input_password.getText().toString().trim().matches(input_confirm_password.getText().toString().trim())) {
            db = DBconn.getInstance().getFbConnection();
            db.changePassword(email, old_password, new_password, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    //password changed
                    System.out.println("Password Successfully Changed.");
                    Toast.makeText(ChangePassword.this, "Password Successfully Changed.", Toast.LENGTH_LONG).show();
                    Intent welcome = new Intent(ChangePassword.this, Welcome.class);
                    startActivity(welcome);
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    // error encountered
                    Toast.makeText(ChangePassword.this, "ERROR: Password NOT Changed. Check email and original password.", Toast.LENGTH_LONG).show();
                    System.err.println(firebaseError.toString());

                }
            });
        } else {
            Toast.makeText(ChangePassword.this, "ERROR: Passwords do not match.", Toast.LENGTH_LONG).show();
        }
    }


}
