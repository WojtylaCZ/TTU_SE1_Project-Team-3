package com.ttu_se1_project_team_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This activity is used to provide specific details for the study. Currently,
 * - Name
 *
 * Long-term this may extend to,
 * - Institution (Optional) : Used to limit where the study can be used.
 * - Password (Optional) : Limit who can use the study.
 */
public class TemplateDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_details_activity);
    }

    public void createPreStudyFields(View v) {
        
    }
}
