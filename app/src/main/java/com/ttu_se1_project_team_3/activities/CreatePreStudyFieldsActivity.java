package com.ttu_se1_project_team_3.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ttu_se1_project_team_3.R;
import com.ttu_se1_project_team_3.model.StudyTemplate;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This activity allows the user to create as many PreStudyFields as they wish
 * for their study.
 */
public class CreatePreStudyFieldsActivity extends AppCompatActivity {
    StudyTemplate studyTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_prestudy_fields_activity);
        getStudyTemplate();
    }

    public void getStudyTemplate() {
        this.studyTemplate = StudyTemplate.getInstance();
    }
}
