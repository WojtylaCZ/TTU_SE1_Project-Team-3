package com.ttu_se1_project_team_3.model;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This class is inherited by @SessionLogField and @SessionDataField.
 *
 * The purpose of this class is to standardize some of the features
 * that are shared by both SessionLogFields (which are filled out before
 * a study is conducted) and SessionDataField (which are the study questions).
 */
enum InputT {
    TEXT, CHECKBOXES, RADIOBUTTON
}

public class FormItem {
    public String itemName;
    public InputT itemInput;

    public FormItem() {
        this.itemName = null;
        this.itemInput = null;
    }
}
