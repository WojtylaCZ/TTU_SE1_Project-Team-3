package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.StudyTemplate;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This activity allows the user to create as many PreStudyFields as they wish
 * for their study.
 */
public class CreateSessionLogField extends AppCompatActivity {
    StudyTemplate studyTemplate;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sessionlog_field);
        getStudyTemplate();

        spinner = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.input_types, android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);
    }

    public void getStudyTemplate() {
        this.studyTemplate = StudyTemplate.getInstance();
    }

    /**
     * Allows the user to go back and change details associated with the template being
     * created.
     *
     * @param v
     */
    public void changeTemplateDetails(View v) {
        Intent details = new Intent(this, TemplateDetails.class);
        startActivity(details);
    }

    public void createStudyFields(View v) {

    }

    public void addSessionLogField(View v) {

    }
}
