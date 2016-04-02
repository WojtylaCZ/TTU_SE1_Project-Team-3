package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ttu_se1_project_team_3.R;

public class CreateStudyTemplatePart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_template_part);
    }

    public void createSessionLog(View v) {
        Intent newSessionLog = new Intent(this, AddSessionLog.class);
        startActivity(newSessionLog);
    }

    public void addSessionData(View v) {
        Intent addSessionData = new Intent(this, AddSessionData.class);
        startActivity(addSessionData);
    }
}
