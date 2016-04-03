package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.AppState;
import com.ttu_se1_project_team_3.model.SessionDataField;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import java.util.HashMap;
import java.util.Random;

public class TestJSON extends AppCompatActivity {
    Firebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        Random rand = new Random();

        HashMap<String,String> slfName = new HashMap<>();
        slfName.put("name", "Vojta");

        HashMap<String,String> slfYear = new HashMap<>( );
        slfYear.put("year", "2016");

        HashMap<String,String> sdfx1 = new HashMap<>();
        sdfx1.put("male", "0");
        sdfx1.put("female", "0");
        sdfx1.put("na", "0");

        HashMap<String,String> sdfx2 = new HashMap<>();
        sdfx2.put("up", "0");
        sdfx2.put("down", "0");
        sdfx2.put("left", "0");
        sdfx2.put("right", "0");

        AppState appState = AppState.getInstance();
        StudyTemplate studyTemplate = appState.getStudyTemplate();
        studyTemplate.clearTemplate();
        studyTemplate.setName("Foo");

        studyTemplate.addSessionLogField("name", "Text",slfName);
        studyTemplate.addSessionLogField("year", "Text",slfYear);

        studyTemplate.addSessionDataField("gender", "RadioButtons",sdfx1);
        studyTemplate.addSessionDataField("gestures", "Checkboxes",sdfx2);

        db = DBconn.getInstance().getFbConnection();


        Firebase templatesRef = db.child("Templates");

        Firebase newTemplateRef = templatesRef.push();

        newTemplateRef.setValue(studyTemplate);

        String postKey = newTemplateRef.getKey();

        System.out.println("Just saved template key is: " + postKey);

        db = DBconn.getInstance().getTemplatesQueryConn();

        Query queryRef = db.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                StudyTemplate studyTemplate = snapshot.getValue(StudyTemplate.class);
                System.out.println(snapshot.getKey() + " is " + studyTemplate.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


}
