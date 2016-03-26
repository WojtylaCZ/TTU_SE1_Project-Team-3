package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.google.gson.Gson;
import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

public class TestJSON extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);

        SessionLogField slfName = new SessionLogField(FormItem.InputT.TEXT,"name","Vojta");
        SessionLogField slfYear = new SessionLogField(FormItem.InputT.TEXT,"year","2016");

        StudyTemplate studyTemplate = StudyTemplate.getInstance();

        studyTemplate.ad

        Gson gson = new Gson();
        String json = gson.toJson(slfName);

        System.out.println("zde"+json);



    }


}
