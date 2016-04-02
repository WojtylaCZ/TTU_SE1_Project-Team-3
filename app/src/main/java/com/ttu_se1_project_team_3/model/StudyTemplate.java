package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;

/**
 * Created by Isaac on 2/27/2016.
 * <p/>
 * A StudyTemplate is the standard class for all user designed studies.
 * <p/>
 * It contains @sessionLogFields and sessionDataFields, which are then used to dynamically
 * generate pages when the study is conducted.
 */


public class StudyTemplate {

    String name;
    ArrayList<SessionLogField> sessionLogFields;
    ArrayList<SessionDataField> sessionDataFields;

    private static StudyTemplate instance = null;


    /**
     * TODO because it is singleton, it should be private. But Jackson parser within Firebase needs contructors public :/
     *
     * TODO VOJTA But because we handle different templates, I ask, do we need singleton here?
     */


    public StudyTemplate() {
        // Exists only to deal with instantiation.
    }

    public static StudyTemplate getInstance() {
        if (instance == null) {
            instance = new StudyTemplate();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SessionDataField> getSessionDataFields() {
        return sessionDataFields;
    }

    public void setSessionDataFields(ArrayList<SessionDataField> sessionDataFields) {
        this.sessionDataFields = sessionDataFields;
    }

    public ArrayList<SessionLogField> getSessionLogFields() {
        return sessionLogFields;
    }

    public void setSessionLogFields(ArrayList<SessionLogField> sessionLogFields) {
        this.sessionLogFields = sessionLogFields;
    }

    public void addSessionLogField(SessionLogField newField) {
        this.sessionLogFields.add(newField);
    }

    public void addSessionDataField(SessionDataField newField) {
        this.sessionDataFields.add(newField);
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
