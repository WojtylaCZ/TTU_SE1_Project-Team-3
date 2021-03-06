package com.ttu_se1_project_team_3.activities;

import com.firebase.client.Firebase;

/**
 * Created by wojtyla on 3/3/16.
 * <p/>
 * <p/>
 * this is a singleton which holds a DB connection
 */


public class DBconn {

    private final static String DB_LOCATION = "https://pless.firebaseio.com/";

    private static DBconn instance = null;
    private Firebase fbConnection;

    public static DBconn getInstance() {
        if (instance == null) {
            instance = new DBconn();
        }
        return instance;
    }

    private DBconn() {
        fbConnection = new Firebase(DB_LOCATION);
    }

    public Firebase getFbConnection() {
        return fbConnection;
    }


}
