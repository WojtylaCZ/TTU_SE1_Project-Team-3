package com.ttu_se1_project_team_3;

import java.util.ArrayList;

/**
 * Created by Isaac on 2/27/2016.
 *
 * standardInputStyles is used to regulate the way that predefined input is handled.
 * EX: If you are conducting a study of how students understand language, you might
 * want to pre-define the words they are tested on and randomize their order.
 *
 * A StudyField is the format for questions involved in a study.
 * EX: Which (if any) of these kinds of accidents have you been involved in?
 */

enum standardInputStyles {
    NORMAL, RANDOMIZED
}

public class StudyField extends StudyItem {
    public standardInputStyles inputStyle;
    public ArrayList<String> standardInputs;

    public StudyField(String name, InputType inputType) {
        this.itemName = name;
        this.itemInput = inputType;
        this.inputStyle = standardInputStyles.NORMAL;
    }

    public void addStandardInput(String standardInput) {
        this.standardInputs.add(standardInput);
    }
}
