package com.ttu_se1_project_team_3.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


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
import com.ttu_se1_project_team_3.model.User;
import com.ttu_se1_project_team_3.model.jsontocsv.parser.JsonFlattener;
import com.ttu_se1_project_team_3.model.jsontocsv.writer.CSVWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestJSON extends AppCompatActivity {
    Firebase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
    }

    public void saveNewTemplateToDB(View v) {
        StudyTemplate studyTemplate = new StudyTemplate();
        studyTemplate.clearTemplate();
        studyTemplate.setName("ZZFoo");

        HashMap<String, String> slfName = new HashMap<>();
        slfName.put("name", null);
        studyTemplate.addSessionLogField("name", "Text", slfName);

        HashMap<String, String> slfYear = new HashMap<>();
        slfYear.put("year", null);
        studyTemplate.addSessionLogField("year", "Text", slfYear);


        HashMap<String, String> sdf1 = new HashMap<>();
        sdf1.put("male", null);
        sdf1.put("female", null);
        sdf1.put("na", null);
        studyTemplate.addSessionDataField("gender", "RadioButtons", sdf1);


        HashMap<String, String> sdf2 = new HashMap<>();
        sdf2.put("up", null);
        sdf2.put("down", null);
        sdf2.put("left", null);
        sdf2.put("right", null);
        studyTemplate.addSessionDataField("gestures", "Checkboxes", sdf2);

        db = DBconn.getInstance().getFbConnection();

        Firebase templatesRef = db.child("Templates");

        Firebase newTemplateRef = templatesRef.push();

        newTemplateRef.setValue(studyTemplate);

        String postKey = newTemplateRef.getKey();

        Snackbar.make(v, "Template saved. Key: " + postKey, Snackbar.LENGTH_LONG).show();
        System.out.println("Template saved. Key: " + postKey);

    }

    public void loadTemplatesFromDB(View v) {

        db = DBconn.getInstance().getFbConnection().child("Templates");

        Query queryRef = db.orderByChild("name");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                StudyTemplate studyTemplate = snapshot.getValue(StudyTemplate.class);
                System.out.print(snapshot.getKey() + " is template with: ");
                System.out.println("Name: " + studyTemplate.getName());
                System.out.println("SessionLogFields: " + studyTemplate.getSessionLogFields());
                System.out.println("SessionDataFields: " + studyTemplate.getSessionDataFields());


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
        Snackbar.make(v, "All templates loaded. ", Snackbar.LENGTH_LONG).show();

    }

    public void saveConductedStudy(View v) {
        StudyTemplate studyTemplate = new StudyTemplate();
        studyTemplate.clearTemplate();
        studyTemplate.setName("ZZFoo");


        HashMap<String, String> slfName = new HashMap<>();
        slfName.put("name", "Vodka");
        studyTemplate.addSessionLogField("name", "Text", slfName);

        HashMap<String, String> slfYear = new HashMap<>();
        slfYear.put("year", "1990");
        studyTemplate.addSessionLogField("year", "Text", slfYear);

        HashMap<String, String> sdfx1 = new HashMap<>();
        sdfx1.put("male", "1");
        sdfx1.put("female", "0");
        sdfx1.put("na", "0");
        studyTemplate.addSessionDataField("gender", "RadioButtons", sdfx1);

        HashMap<String, String> sdfx2 = new HashMap<>();
        sdfx2.put("up", "1");
        sdfx2.put("down", "0");
        sdfx2.put("left", "1");
        sdfx2.put("right", "0");
        studyTemplate.addSessionDataField("gestures", "Checkboxes", sdfx2);


        db = DBconn.getInstance().getFbConnection();

        Firebase conductedStudyRef = db.child("conductedStudies/"+ User.getInstance().getFbUid());

        Firebase newConStudyRef = conductedStudyRef.push();

        newConStudyRef.setValue(studyTemplate);

        String postKey = newConStudyRef.getKey();

        Snackbar.make(v, "Conducted study saved. Key: " + postKey, Snackbar.LENGTH_LONG).show();
        System.out.println("Conducted study saved. Key: " + postKey);



    }




    public void loadUsersStudies(View v) {

        db = DBconn.getInstance().getFbConnection().child("conductedStudies/"+ User.getInstance().getFbUid());

        Query queryRef = db.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                System.out.println("Conducted Study ID: " + snapshot.getKey());
                StudyTemplate study = snapshot.getValue(StudyTemplate.class);
                System.out.println("Conducted Study: " + study);

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
        Snackbar.make(v, "All studies loaded. ", Snackbar.LENGTH_LONG).show();

        System.out.println("OK");
        JsonFlattener parser = new JsonFlattener();

        CSVWriter writer = new CSVWriter();


        List<Map<String, String>> flatJson = null;
        try {
            flatJson = parser.parseJson(jsonValue());
            System.out.println(writer.writeAsCSV(flatJson, "sample.csv"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("END");


    }


    private static String jsonValue() {
        return "[\n" +
                "    {\n" +
                "        \"studentName\": \"Foo\",\n" +
                "        \"Age\": \"12\",\n" +
                "        \"subjects\": [\n" +
                "            {\n" +
                "                \"name\": \"English\",\n" +
                "                \"marks\": \"40\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"History\",\n" +
                "                \"marks\": \"50\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"studentName\": \"Bar\",\n" +
                "        \"Age\": \"12\",\n" +
                "        \"subjects\": [\n" +
                "            {\n" +
                "                \"name\": \"English\",\n" +
                "                \"marks\": \"40\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"History\",\n" +
                "                \"marks\": \"50\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Science\",\n" +
                "                \"marks\": \"40\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"studentName\": \"Baz\",\n" +
                "        \"Age\": \"12\",\n" +
                "        \"subjects\": []\n" +
                "    }\n" +
                "]";
    }
}
