package com.ttu_se1_project_team_3.model;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This class is inherited by StudyField and SessionLogField.
 *
 * The purpose of this class is to standardize some of the features
 * that are shared by both PreStudyFields (which are filled out before
 * a study is conducted) and StudyFields (which are the study questions).
 */



public class FormItem {
    public enum InputT {
        TEXT, CHECKBOXES, RADIOBUTTON
    }

    public String itemName;
    public InputT itemInput;
    public String itemValue;

    public FormItem() {
        this.itemName = null;
        this.itemInput = null;
        this.itemValue = null;
    }
}
