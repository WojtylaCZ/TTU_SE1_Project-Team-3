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



public class FormItem {
    public enum InputT {
        TEXT, CHECKBOX, RADIOBUTTON
    }

    public String itemName;
    public InputT itemInput;
    public String itemValue;

    public FormItem() {
        this.itemName = null;
        this.itemInput = null;
        this.itemValue = null;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public InputT getItemInput() {
        return itemInput;
    }

    public void setItemInput(InputT itemInput) {
        this.itemInput = itemInput;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        return "FormItem{" +
                "itemName='" + itemName + '\'' +
                ", itemInput=" + itemInput +
                ", itemValue='" + itemValue + '\'' +
                '}';
    }
}
