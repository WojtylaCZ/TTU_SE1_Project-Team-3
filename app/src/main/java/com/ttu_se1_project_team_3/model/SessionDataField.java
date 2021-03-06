package com.ttu_se1_project_team_3.model;

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
public class SessionDataField extends FormItem {
    public InputStyles inputStyle;

    public SessionDataField() {}

    public SessionDataField(String name, String inputType, HashMap<String,String> values) {
        this.itemName = name;
        this.itemInput = getInputT(inputType);
        this.inputStyle = InputStyles.NORMAL;
        this.itemValues = values;
    }

    public InputStyles getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(InputStyles style) {
        if(style != null)
            inputStyle = style;
    }
}
