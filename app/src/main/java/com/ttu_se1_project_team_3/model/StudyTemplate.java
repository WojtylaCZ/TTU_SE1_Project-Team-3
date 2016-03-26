package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;

/**
 * Created by Isaac on 2/27/2016.
 * <p/>
 * A StudyTemplate is the standard class for all user designed studies.
 *
 * It contains @sessionLogFields and sessionDataFields, which are then used to dynamically
 * generate pages when the study is conducted.
 *
 */


public class StudyTemplate {
    String studyName;
    ArrayList<SessionLogField> sessionLogFields;
    ArrayList<SessionDataField> sessionDataFields;

    private static StudyTemplate instance = null;

    private StudyTemplate() {
        // Exists only to deal with instantiation.
    }

    public static StudyTemplate getInstance() {
        if (instance == null) {
            instance = new StudyTemplate();
        }
        return instance;
    }

    public void setName(String name) {
        this.studyName = name;
    }

    public void addSessionLogField(SessionLogField newField) {
        this.sessionLogFields.add(newField);
    }

    public void addSessionDataField(SessionDataField newField) {
        this.sessionDataFields.add(newField);
    }

    public void clearTemplate() {
        studyName = null;
        sessionLogFields = new ArrayList<>();
        sessionDataFields = new ArrayList<>();
    }
}
