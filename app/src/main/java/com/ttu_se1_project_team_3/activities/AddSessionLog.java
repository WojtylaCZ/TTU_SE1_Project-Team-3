package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.AppState;
import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import java.util.HashMap;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This activity allows the user to create as many sessionLogFields as they wish
 * for their study.
 */
public class AddSessionLog extends AppCompatActivity {
    AppState appState;
    StudyTemplate studyTemplate;
    Spinner spinner;
    EditText logName;
    EditText logContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sessionlog);
        getStudyTemplate();

        logName = (EditText) findViewById(R.id.session_log_name);
        logContent = (EditText) findViewById(R.id.session_log_content);

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
        this.appState = AppState.getInstance();
        this.studyTemplate = appState.getStudyTemplate();
    }

    public void addSessionLogField(View v) {
        String inputType = spinner.getSelectedItem().toString();
        String inputName = logName.getText().toString();
        HashMap<String,String> content = new HashMap<>();

        if (logName.length() == 0 || logContent.length() == 0) {
            if (logName.length() == 0)
                logName.setError("You must provide a name.");

            if (logContent.length() == 0)
                logContent.setError("You must provide content for this field.");

            return;
        }

        if (inputType.compareTo("Text") != 0)
            content = studyTemplate.getInputOptions(logContent.getText().toString());

        if (studyTemplate.addSessionLogField(inputName, inputType,content))
            Toast.makeText(AddSessionLog.this, "You've added a Session Log Field.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AddSessionLog.this, "That field name already exists.", Toast.LENGTH_LONG).show();
    }

    public void next(View v) {
        Intent sessionData = new Intent(this, AddSessionData.class);
        startActivity(sessionData);
    }

    public void back(View v) {
        Intent studyTemplate = new Intent(this, CreateStudyTemplate.class);
        startActivity(studyTemplate);
    }
}
