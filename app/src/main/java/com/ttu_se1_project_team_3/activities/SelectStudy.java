package com.ttu_se1_project_team_3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ttu_se1_project_team_3.R;

/**
 * Created by Ryan.
 *
 * This class allows to select study template which are available.
 * It loads the templates from db and show them in spinner view.
 * Selected study is used to conduct study.
 */
public class SelectStudy extends AppCompatActivity implements OnItemSelectedListener{

    private Firebase ref;
    private ArrayList<String> templates = new ArrayList<String>();
    private String selectedTemplateName;
    private String selectedTemplate;
    private Spinner items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_study);


        /**
         * Connnecting to DataBase and pulling String names
         */

        ref = DBconn.getInstance().getFbConnection();
        final Firebase templateNames = ref.child("Templates");

        templateNames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot templateSnap : dataSnapshot.getChildren()) {
                    Log.d("TemplateName", (String) templateSnap.child("name").getValue());
                    templates.add((String) templateSnap.child("name").getValue().toString());
                }


                /**
                 * Setting up the Spinners
                 *
                 */
                ArrayAdapter<String> templateAdapter = new ArrayAdapter<String>(SelectStudy.this, android.R.layout.simple_spinner_dropdown_item, templates);
                items = (Spinner) findViewById(R.id.nameSpinner);
                if (items != null) {
                    items.setAdapter(templateAdapter);
                }
                if (items != null) {
                    items.setOnItemSelectedListener(SelectStudy.this);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }



    public void conductStudy(View v) {
        Intent newConductStudy = new Intent(this, ConductStudy.class);
        if (selectedTemplate != null) {
            newConductStudy.putExtra("Selected", selectedTemplate);
        } else {
            selectedTemplate = templates.get(0);
        }
        startActivity(newConductStudy);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTemplate = items.getSelectedItem().toString();
        Toast.makeText(SelectStudy.this, selectedTemplate, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
