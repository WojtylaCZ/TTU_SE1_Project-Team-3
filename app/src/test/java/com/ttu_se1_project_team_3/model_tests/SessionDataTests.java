package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.InputStyles;
import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionDataField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Isaac on 4/24/2016.
 * Basic tests for the SessionData class
 */
public class SessionDataTests {

    SessionDataField sessionData = new SessionDataField();

    @Before
    public void clearTest() throws Exception {
        sessionData.setItemName("");
        sessionData.setItemInput(InputT.TEXT);
        HashMap<String, String> hashMap = new HashMap<>();
        sessionData.setItemValues(hashMap);
        sessionData.setInputStyle(InputStyles.NORMAL);
    }

    @Test
    public void setItemName_empty() throws Exception {
        sessionData.setItemName("");
        assertEquals("", sessionData.getItemName());
    }

    @Test
    public void setItemName_nums() throws Exception {
        sessionData.setItemName("1s22a");
        assertEquals("1s22a", sessionData.getItemName());
    }

    @Test
    public void setItemName_chars() throws Exception {
        sessionData.setItemName("jelly");
        assertEquals("jelly", sessionData.getItemName());
    }

    @Test
    public void setItemName_null() throws Exception {
        sessionData.setItemName(null);
        assertEquals("", sessionData.getItemName());
    }

    @Test
    public void setInputType_text() throws Exception {
        sessionData.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, sessionData.getItemInput());
    }

    @Test
    public void setInputType_checkboxes() throws Exception {
        sessionData.setItemInput(InputT.CHECKBOXES);
        assertEquals(InputT.CHECKBOXES, sessionData.getItemInput());
    }

    @Test
    public void setInputType_radio() throws Exception {
        sessionData.setItemInput(InputT.RADIOBUTTONS);
        assertEquals(InputT.RADIOBUTTONS, sessionData.getItemInput());
    }

    @Test
    public void setInputType_null() throws Exception {
        sessionData.setItemInput(null);
        assertEquals(InputT.TEXT, sessionData.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        sessionData.setItemValues(values);
        assertEquals(values, sessionData.getItemValues());
    }

    @Test
    public void setItemValues_filled() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        sessionData.setItemValues(values);
        assertEquals(values, sessionData.getItemValues());
    }

    @Test
    public void setItemValues_null() throws Exception {
        HashMap<String, String> value = new HashMap<>();
        HashMap<String, String> values = null;
        sessionData.setItemValues(values);
        assertEquals(value, sessionData.getItemValues());
    }

    @Test
    public void setInputStyle_random() throws Exception {
        sessionData.setInputStyle(InputStyles.RANDOM);
        assertEquals(InputStyles.RANDOM, sessionData.getInputStyle());
    }

    @Test
    public void setInputStyle_normal() throws Exception {
        sessionData.setInputStyle(InputStyles.NORMAL);
        assertEquals(InputStyles.NORMAL, sessionData.getInputStyle());
    }

    @Test
    public void setInputStyle_null() throws Exception {
        sessionData.setInputStyle(null);
        assertEquals(InputStyles.NORMAL, sessionData.getInputStyle());
    }
}
