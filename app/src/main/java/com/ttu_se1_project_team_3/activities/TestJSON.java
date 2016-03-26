package com.ttu_se1_project_team_3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionLogField;

public class TestJSON extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);

        SessionLogField slf = new SessionLogField("name", InputT.TEXT);

    }


}
