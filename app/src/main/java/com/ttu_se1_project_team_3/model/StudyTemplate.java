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
        sessionLogFields = new ArrayList<>();
        sessionDataFields = new ArrayList<>();
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

    public boolean addSessionLogField(String name, String type) {
        if (!logFieldFree(name))
            return false;

        SessionLogField newField = new SessionLogField(name, type);
        sessionLogFields.add(newField);
        return true;
    }

    private boolean logFieldFree(String name) {
        for (int i = 0; i<sessionLogFields.size(); i++) {
            if (sessionLogFields.get(i).itemName.equals(name))
                return false;
        }
        return true;
    }

    public boolean addSessionDataField(String name, String type) {
        if (!dataFieldFree(name))
            return false;

        SessionDataField newField = new SessionDataField(name, type);
        sessionDataFields.add(newField);
        return true;
    }

    private boolean dataFieldFree(String name) {
        for (int i = 0; i<sessionDataFields.size(); i++) {
            if (sessionDataFields.get(i).itemName.equals(name))
                return false;
        }
        return true;
    }

    public void clearTemplate() {
        studyName = null;
        sessionLogFields = new ArrayList<>();
        sessionDataFields = new ArrayList<>();
    }
}
