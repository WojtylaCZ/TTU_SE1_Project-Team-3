package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.AppState;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import java.util.HashMap;

/**
 * Created by Isaac on 2/27/2016.
 * <p/>
 * This activity allows the user to create as many sessionDataFields as they wish
 * for their study.
 */
public class AddSessionData extends AppCompatActivity {
    AppState appState;
    StudyTemplate studyTemplate;
    Spinner spinner;
    EditText dataName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sessiondata);
        getStudyTemplate();

        dataName = (EditText) findViewById(R.id.session_data_name);

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

    public void addSessionDataField(View v) {
        String inputType = spinner.getSelectedItem().toString();
        String inputName = dataName.getText().toString();

        //@TODO UI for value inputs
        HashMap<String,String> content = new HashMap<>();
        content.put("TODO","TODO");

        if (studyTemplate.addSessionDataField(inputName, inputType,content))
            Toast.makeText(AddSessionData.this, "You've added a Session Log Field.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AddSessionData.this, "That field name already exists.", Toast.LENGTH_LONG).show();
    }

    public void save(View v) {

    }

    public void back(View v) {
        Intent sessionLog = new Intent(this, AddSessionLog.class);
        startActivity(sessionLog);
    }
}
