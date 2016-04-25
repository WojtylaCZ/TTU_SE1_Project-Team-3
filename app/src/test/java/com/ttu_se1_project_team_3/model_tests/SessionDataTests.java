package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.InputStyles;
import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionDataField;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Isaac on 4/24/2016.
 * Basic tests for the SessionData class
 */
public class SessionDataTests {

    ArrayList<String> stringChecks = new ArrayList<>();
    SessionDataField sessionData = new SessionDataField();
    Boolean testsSetup = false;

    @Test
    public void setItemName_checkList() throws Exception {
        setupTests();
        for (String item : stringChecks) {
            sessionData.setItemName(item);
            assertEquals(item, sessionData.getItemName());
        }
    }

    @Test
    public void setInputType_check() throws Exception {
        sessionData.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, sessionData.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        sessionData.setItemValues(values);
        assertEquals(values, sessionData.getItemValues());
    }

    @Test
    public void setItemValues_check() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        sessionData.setItemValues(values);
        assertEquals(values, sessionData.getItemValues());
    }

    @Test
    public void setInputStyle_check() throws Exception {
        sessionData.setInputStyle(InputStyles.RANDOM);
        assertEquals(InputStyles.RANDOM, sessionData.getInputStyle());
    }

    private void setupTests() {
        if (!testsSetup) {
            stringChecks.add("");
            stringChecks.add("words");
            stringChecks.add("121d12eZZZ");
            testsSetup = true;
        }
    }
}
