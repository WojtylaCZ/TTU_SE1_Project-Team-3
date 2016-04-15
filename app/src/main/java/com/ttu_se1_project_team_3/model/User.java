package com.ttu_se1_project_team_3.model;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by wojtyla on 3/2/16.
 * Going to be used to regulate user access to studies and templates.
 */
public class User {
    private String email;
    private String fbUid;


    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    private User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFbUid() {
        return fbUid;
    }

    public void setFbUid(String fbUid) {
        this.fbUid = fbUid;
    }
}
