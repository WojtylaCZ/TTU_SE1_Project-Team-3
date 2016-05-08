package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionLogField;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;

/**
 * Created by Isaac Griswold-Steiner on 4/24/2016.
 * Unit tests for the SessionLog class
 */
public class SessionLogTests {

    SessionLogField sessionLog = new SessionLogField();

    @Before
    public void clearTest() throws Exception {
        sessionLog.setItemName("");
        sessionLog.setItemInput(InputT.TEXT);
        HashMap<String, String> hashMap = new HashMap<>();
        sessionLog.setItemValues(hashMap);
    }

    @Test
    public void setItemName_empty() throws Exception {
        sessionLog.setItemName("");
        assertEquals("", sessionLog.getItemName());
    }

    @Test
    public void setItemName_nums() throws Exception {
        sessionLog.setItemName("1s22a");
        assertEquals("1s22a", sessionLog.getItemName());
    }

    @Test
    public void setItemName_chars() throws Exception {
        sessionLog.setItemName("jelly");
        assertEquals("jelly", sessionLog.getItemName());
    }

    @Test
    public void setItemName_null() throws Exception {
        sessionLog.setItemName(null);
        assertEquals("", sessionLog.getItemName());
    }

    @Test
    public void setInputType_text() throws Exception {
        sessionLog.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, sessionLog.getItemInput());
    }

    @Test
    public void setInputType_checkboxes() throws Exception {
        sessionLog.setItemInput(InputT.CHECKBOXES);
        assertEquals(InputT.CHECKBOXES, sessionLog.getItemInput());
    }

    @Test
    public void setInputType_radio() throws Exception {
        sessionLog.setItemInput(InputT.RADIOBUTTONS);
        assertEquals(InputT.RADIOBUTTONS, sessionLog.getItemInput());
    }

    @Test
    public void setInputType_null() throws Exception {
        sessionLog.setItemInput(null);
        assertEquals(InputT.TEXT, sessionLog.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        sessionLog.setItemValues(values);
        assertEquals(values, sessionLog.getItemValues());
    }

    @Test
    public void setItemValues_filled() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        sessionLog.setItemValues(values);
        assertEquals(values, sessionLog.getItemValues());
    }

    @Test
    public void setItemValues_null() throws Exception {
        HashMap<String, String> value = new HashMap<>();
        HashMap<String, String> values = null;
        sessionLog.setItemValues(values);
        assertEquals(value, sessionLog.getItemValues());
    }
}
