package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;

/**
 * Created by Isaac on 2/27/2016.
 *
 * A StudyTemplate is the standard class for all user designed studies.
 * They add PreStudyFields and StudyFields, which are then used to dynamically
 * generate pages when the study is conducted.
 */
public class StudyTemplate {
    String studyName;
    ArrayList<PreStudyField> preSurveyFields;
    ArrayList<StudyField> surveyFields;

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

    public void addPreSurveyField(PreStudyField newField) {
        this.preSurveyFields.add(newField);
    }

    public void addSurveyField(StudyField newField) {
        this.surveyFields.add(newField);
    }

    public void clearTemplate() {
        studyName = null;
        preSurveyFields = new ArrayList<>();
        surveyFields = new ArrayList<>();
    }
}
