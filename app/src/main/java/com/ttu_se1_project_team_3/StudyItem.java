package com.ttu_se1_project_team_3;

/**
 * Created by Isaac on 2/27/2016.
 *
 * This class is inherited by StudyField and PreStudyField.
 *
 * The purpose of this class is to standardize some of the features
 * that are shared by both PreStudyFields (which are filled out before
 * a study is conducted) and StudyFields (which are the study questions).
 */
enum InputT {
    TEXT, CHECKBOXES, RADIOBUTTON
}

public class StudyItem {
    public String itemName;
    public InputT itemInput;

    public StudyItem() {
        this.itemName = null;
        this.itemInput = null;
    }
}
