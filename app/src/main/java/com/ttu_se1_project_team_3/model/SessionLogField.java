package com.ttu_se1_project_team_3.model;

/**
 * Created by Isaac on 2/27/2016.
 *
 * A SessionLogField is what is filled out before a study begins.
 */
public class SessionLogField extends FormItem {

    /**
    @param inputType is always TEXT
     */
    public SessionLogField(String name, InputT inputType) {
        this.itemName = name;
        this.itemInput = inputType;
    }
}