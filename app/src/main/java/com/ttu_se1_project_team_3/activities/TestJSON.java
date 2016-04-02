package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.gson.Gson;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.SessionDataField;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestJSON extends AppCompatActivity {
    Firebase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        Random rand = new Random();

        SessionLogField slfName = new SessionLogField("name", "Text");
        slfName.setItemValue("Vojta");

        SessionLogField slfYear = new SessionLogField("year", "Text" );
        slfYear.setItemValue(String.valueOf(rand.nextInt(2000)));

        SessionDataField sdfx1 = new SessionDataField("behavior", "ToggleButtons");
        sdfx1.setItemValue(String.valueOf(rand.nextInt(5)));

        SessionDataField sdfx2 = new SessionDataField("polite", "Checkboxes");
        sdfx2.setItemValue(String.valueOf(rand.nextInt(2)));

        StudyTemplate studyTemplate = StudyTemplate.getInstance();
        studyTemplate.clearTemplate();
        studyTemplate.setName("Foo");

        studyTemplate.addSessionLogField(slfName);
        studyTemplate.addSessionLogField(slfYear);

        studyTemplate.addSessionDataField(sdfx1);
        studyTemplate.addSessionDataField(sdfx2);

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
