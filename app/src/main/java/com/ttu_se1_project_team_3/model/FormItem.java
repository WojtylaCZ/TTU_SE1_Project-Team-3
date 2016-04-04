package com.ttu_se1_project_team_3.model;

import java.util.ArrayList;
import java.util.HashMap;

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
    TEXT, CHECKBOXES, RADIOBUTTONS
}


public class FormItem {

    public String itemName;
    public InputT itemInput;
    public HashMap<String,String> itemValues;

    public FormItem() {
        this.itemName = null;
        this.itemInput = null;
        this.itemValues = null;
    }

    public static InputT getInputT(String input) {
        switch(input) {
            case "Text":
                return InputT.TEXT;
            case "RadioButtons":
                return InputT.RADIOBUTTONS;
            case "Checkboxes":
                return InputT.CHECKBOXES;
            default:
                return InputT.TEXT;
        }
    }

    @Override
    public String toString() {
        return "FormItem{" +
                "itemName='" + itemName + '\'' +
                ", itemInput=" + itemInput +
                ", itemValues='" + itemValues + '\'' +
                '}';
    }
}
