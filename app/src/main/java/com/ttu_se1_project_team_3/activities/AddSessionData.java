package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.StudyTemplate;

/**
 * Created by Isaac on 2/27/2016.
 * <p/>
 * This activity allows the user to create as many sessionLogFields as they wish
 * for their study.
 */
public class AddSessionData extends AppCompatActivity {
    StudyTemplate studyTemplate;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sessiondata);
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

    public void addSessionDataField(View v) {

    }

    public void back(View v) {

    }


}
