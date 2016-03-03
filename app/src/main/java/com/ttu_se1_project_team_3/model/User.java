package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;

/**
 * Created by wojtyla on 3/2/16.
 */
public class User {
    private String name;
    private String email;
    private String passwordHash;
    private String institution;
    private ArrayList<StudyTemplate> studyTemplates;
    private ArrayList conductedStudies;

    public User(String name, String email, String passwordHash, String institution) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public ArrayList<StudyTemplate> getStudyTemplates() {
        return studyTemplates;
    }

    public void setStudyTemplates(ArrayList<StudyTemplate> studyTemplates) {
        this.studyTemplates = studyTemplates;
    }

    public ArrayList getConductedStudies() {
        return conductedStudies;
    }

    public void setConductedStudies(ArrayList conductedStudies) {
        this.conductedStudies = conductedStudies;
    }
}
