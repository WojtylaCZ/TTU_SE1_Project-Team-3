package com.ttu_se1_project_team_3.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Isaac on 2/27/2016.
 *
 * standardInputStyles is used to regulate the way that predefined input is handled.
 * EX: If you are conducting a study of how students understand language, you might
 * want to pre-define the words they are tested on and randomize their order.
 *
 * A SessionDataField is the format for questions involved in a study.
 * EX: Which (if any) of these kinds of accidents have you been involved in?
 */

enum standardInputStyles {
    NORMAL, RANDOM
}

public class SessionDataField extends FormItem {
    public standardInputStyles inputStyle;
    public ArrayList<String> standardInputs;

    public SessionDataField() {}

    public SessionDataField(String name, String inputType, HashMap<String,String> values) {
        this.itemName = name;
        this.itemInput = getInputT(inputType);
        this.inputStyle = standardInputStyles.NORMAL;
        this.itemValues = values;
    }

    public standardInputStyles getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(standardInputStyles style) {
        inputStyle = style;
    }

    public ArrayList<String> getStandardInputs() {
        return standardInputs;
    }

    public void setStandardInputs(ArrayList<String> inputs) {
        standardInputs = inputs;
    }

    public void addStandardInput(String standardInput) {
        this.standardInputs.add(standardInput);
    }
}
