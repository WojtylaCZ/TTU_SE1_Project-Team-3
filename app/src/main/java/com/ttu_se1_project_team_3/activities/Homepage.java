package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ttu_se1_project_team_3.R;

/**
 * Created by Isaac on 2/24/2016.
 *
 * The homepage contains all options available to a verified user.
 * - Create a Study Template
 * - Conduct a study via a Study Template
 * - Export a Study Template
 * - Howdy
 * - Analyze a Study
 */
public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }


    public void createStudyTemplate(View v) {
        Intent newStudyTemplate = new Intent(this, CreateStudyTemplate.class);
        startActivity(newStudyTemplate);
    }

    public void selectStudy(View v) {
        Intent newSelectStudy =  new Intent(this, SelectStudy.class);
        startActivity(newSelectStudy);
    }
    public void changePassword(View v){
        Intent ChangePassword = new Intent(this, com.ttu_se1_project_team_3.activities.ChangePassword.class);
        startActivity(ChangePassword);
    }

    public void exportTemplate(View v) {

    }


}
