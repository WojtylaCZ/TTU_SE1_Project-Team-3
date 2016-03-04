package com.ttu_se1_project_team_3.activities;

import com.firebase.client.Firebase;

/**
 * Created by wojtyla on 3/3/16.
 * <p/>
 * <p/>
 * class conteins singleton to get DB connection
 */


public class DBconn {

    private final static String DB_LOCATION = "https://pless.firebaseio.com/";

    private static DBconn ourInstance = new DBconn();
    private Firebase fbConnection;

    public static DBconn getInstance() {
        return ourInstance;
    }

    private DBconn() {
        fbConnection = new Firebase(DB_LOCATION);
    }

    public Firebase getFbConnection(){
        return fbConnection;
    }

}
