package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.InputT;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Isaac on 4/24/2016.
 * Unit tests for the FormItem class
 */
public class FormItemTests {

    ArrayList<String> stringChecks = new ArrayList<>();
    FormItem formItem = new FormItem();
    Boolean testsSetup = false;

    @Test
    public void setItemName_checkList() throws Exception {
        setupTests();
        for (String item : stringChecks) {
            formItem.setItemName(item);
            assertEquals(item, formItem.getItemName());
        }
    }

    @Test
    public void setInputType_check() throws Exception {
        formItem.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, formItem.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        formItem.setItemValues(values);
        assertEquals(values, formItem.getItemValues());
    }

    @Test
    public void setItemValues_check() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        formItem.setItemValues(values);
        assertEquals(values, formItem.getItemValues());
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
