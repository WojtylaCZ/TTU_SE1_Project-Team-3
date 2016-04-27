package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionLogField;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;

/**
 * Created by Isaac Griswold-Steiner on 4/24/2016.
 * Unit tests for the SessionLog class
 */
public class SessionLogTests {

    ArrayList<String> stringChecks = new ArrayList<>();
    SessionLogField sessionLog = new SessionLogField();
    Boolean testsSetup = false;

    @Test
    public void setItemName_checkList() throws Exception {
        setupTests();
        for (String item : stringChecks) {
            sessionLog.setItemName(item);
            assertEquals(item, sessionLog.getItemName());
        }
    }

    @Test
    public void setInputType_check() throws Exception {
        sessionLog.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, sessionLog.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        sessionLog.setItemValues(values);
        assertEquals(values, sessionLog.getItemValues());
    }

    @Test
    public void setItemValues_check() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        sessionLog.setItemValues(values);
        assertEquals(values, sessionLog.getItemValues());
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
