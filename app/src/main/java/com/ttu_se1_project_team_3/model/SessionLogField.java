package com.ttu_se1_project_team_3.model;

import java.util.HashMap;

/**
 * Created by Isaac on 2/27/2016.
 * <p/>
 * A SessionLogField is what is filled out before a study begins.
 * These are our session log items
 */
public class SessionLogField extends FormItem {

    public SessionLogField() {}

    public SessionLogField(String name, String inputType, HashMap<String, String> values) {
        this.itemName = name;
        this.itemInput = getInputT(inputType);
        this.itemValues = values;
    }
}