package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ttu_se1_project_team_3.R;

/**
 * Created by Isaac on 2/24/2016.
 *
 * The homepage contains all options available to a verified user.
 * - Create a Study Template
 * - Conduct a study via a Study Template
 * - Export a Study Template
 * - Analyze a Study
 */
public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void createTemplate(View v) {
        Intent newTemplate = new Intent(this, TemplateDetails.class);
        startActivity(newTemplate);
    }

    public void conductStudy(View v) {

    }

    public void exportTemplate(View v) {

    }

    public void analyzeStudy(View v) {

    }
}
