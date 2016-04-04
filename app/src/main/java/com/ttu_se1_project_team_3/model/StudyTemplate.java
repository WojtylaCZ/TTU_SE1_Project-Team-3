package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Isaac on 2/27/2016.
 * <p>
 * A StudyTemplate is the standard class for all user designed studies.
 * <p>
 * It contains @sessionLogFields and sessionDataFields, which are then used to dynamically
 * generate pages when the study is conducted.
 */
public class StudyTemplate {

    private String name;
    private ArrayList<SessionLogField> sessionLogFields;
    private ArrayList<SessionDataField> sessionDataFields;

    public StudyTemplate() {
        sessionLogFields = new ArrayList<>();
        sessionDataFields = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SessionLogField> getSessionLogFields() {
        return sessionLogFields;
    }

    public void setSessionLogFields(ArrayList<SessionLogField> logFields) {
        sessionLogFields = logFields;
    }

    public ArrayList<SessionDataField> getSessionDataFields() {
        return sessionDataFields;
    }

    public void setSessionDataFields(ArrayList<SessionDataField> dataFields) {
        sessionDataFields = dataFields;
    }

    public boolean addSessionLogField(String name, String type,HashMap<String,String> values) {
        if (!logFieldFree(name))
            return false;

        SessionLogField newField = new SessionLogField(name, type,values);
        sessionLogFields.add(newField);
        return true;
    }

    private boolean logFieldFree(String name) {
        for (int i = 0; i < sessionLogFields.size(); i++) {
            if (sessionLogFields.get(i).itemName.equals(name))
                return false;
        }
        return true;
    }

    public boolean addSessionDataField(String name, String type,HashMap<String,String> values) {
        if (!dataFieldFree(name))
            return false;

        SessionDataField newField = new SessionDataField(name, type,values);
        sessionDataFields.add(newField);
        return true;
    }

    private boolean dataFieldFree(String name) {
        for (int i = 0; i < sessionDataFields.size(); i++) {
            if (sessionDataFields.get(i).itemName.equals(name))
                return false;
        }
        return true;
    }

    public void clearTemplate() {
        name = null;
        sessionLogFields = new ArrayList<>();
        sessionDataFields = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "StudyTemplate{" +
                "name='" + name + '\'' +
                ", sessionLogFields=" + sessionLogFields +
                ", sessionDataFields=" + sessionDataFields +
                '}';
    }
}
