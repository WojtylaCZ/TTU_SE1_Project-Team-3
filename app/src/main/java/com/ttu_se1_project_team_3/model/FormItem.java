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
public class FormItem {

    public String inputStyle;
    public String itemName;
    public InputT itemInput;
    public HashMap<String,String> itemValues;

    public FormItem() {
//        this.inputStyle = null;
//        this.itemName = null;
//        this.itemInput = null;
//        this.itemValues = null;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        itemName = name;
    }

    public void setInputStyle(String input) {inputStyle = input; }

    public InputT getItemInput() {
        return itemInput;
    }

    public void setItemInput(InputT input) {
        itemInput = input;
    }

    public HashMap<String, String> getItemValues() {
        return itemValues;
    }

    public void setItemValues(HashMap<String, String> values) {
        itemValues = values;
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
