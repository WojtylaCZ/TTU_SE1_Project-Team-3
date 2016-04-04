package com.ttu_se1_project_team_3.model;

/**
 * Created by Isaac on 4/3/2016.
 * This is a singleton for all core app data.
 */
public class AppState {

    private StudyTemplate studyTemplate;
    private static AppState instance = null;

    private AppState() {
        studyTemplate = new StudyTemplate();
    }

    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public StudyTemplate getStudyTemplate() {
        return studyTemplate;
    }
}
