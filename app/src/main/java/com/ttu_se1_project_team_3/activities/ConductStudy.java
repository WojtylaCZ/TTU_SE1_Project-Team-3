package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ttu_se1_project_team_3.R;

import org.w3c.dom.Text;


public class ConductStudy extends AppCompatActivity {

    TextView name;
    public static String templateName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        templateName = getIntent().getStringExtra("Selected");
        setContentView(R.layout.activity_conduct_study);
        //templateName = getIntent().getStringExtra("Selected");

        //name = (TextView) findViewById(R.id.studyName);
        //name.setText(templateName);
    }
}
