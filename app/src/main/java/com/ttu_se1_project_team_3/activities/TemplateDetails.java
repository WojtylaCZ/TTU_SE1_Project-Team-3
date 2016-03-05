package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.StudyTemplate;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This activity is used to provide specific details for the study. Currently,
 * - Name
 *
 * Long-term this may extend to,
 * - Institution (Optional) : Used to limit where the study can be used.
 * - Password (Optional) : Limit who can use the study.
 */
public class TemplateDetails extends AppCompatActivity {
    StudyTemplate studyTemplate;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_details);

        text = (EditText) findViewById(R.id.enter_name);
        studyTemplate = StudyTemplate.getInstance();
    }

    public void createPreStudyFields(View v) {
        String name = text.getText().toString();
        if (name.matches("")) {
            text.setError("Please enter a name");
        } else {
            Intent prestudy = new Intent(this, CreatePreStudyFields.class);
            studyTemplate.setName(text.getText().toString());

            startActivity(prestudy);
        }
    }

    public void goToHomepage(View v) {
        Intent home = new Intent(this, Homepage.class);
        studyTemplate.clearTemplate();
        startActivity(home);
    }
}
