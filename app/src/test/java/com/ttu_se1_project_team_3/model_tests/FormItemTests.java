package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.FormItem;
import com.ttu_se1_project_team_3.model.InputT;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Isaac on 4/24/2016.
 * Unit tests for the FormItem class
 */
public class FormItemTests {

    FormItem formItem = new FormItem();

    @Before
    public void clearTest() throws Exception {
        formItem.setItemName("");
        formItem.setItemInput(InputT.TEXT);
        HashMap<String, String> hashMap = new HashMap<>();
        formItem.setItemValues(hashMap);
    }

    @Test
    public void setItemName_empty() throws Exception {
        formItem.setItemName("");
        assertEquals("", formItem.getItemName());
    }

    @Test
    public void setItemName_nums() throws Exception {
        formItem.setItemName("1s22a");
        assertEquals("1s22a", formItem.getItemName());
    }

    @Test
    public void setItemName_chars() throws Exception {
        formItem.setItemName("jelly");
        assertEquals("jelly", formItem.getItemName());
    }

    @Test
    public void setItemName_null() throws Exception {
        formItem.setItemName(null);
        assertEquals("", formItem.getItemName());
    }

    @Test
    public void setInputType_text() throws Exception {
        formItem.setItemInput(InputT.TEXT);
        assertEquals(InputT.TEXT, formItem.getItemInput());
    }

    @Test
    public void setInputType_radio() throws Exception {
        formItem.setItemInput(InputT.RADIOBUTTONS);
        assertEquals(InputT.RADIOBUTTONS, formItem.getItemInput());
    }

    @Test
    public void setInputType_checkboxes() throws Exception {
        formItem.setItemInput(InputT.CHECKBOXES);
        assertEquals(InputT.CHECKBOXES, formItem.getItemInput());
    }

    @Test
    public void setInputType_null() throws Exception {
        formItem.setItemInput(null);
        assertEquals(InputT.TEXT, formItem.getItemInput());
    }

    @Test
    public void setItemValues_empty() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        formItem.setItemValues(values);
        assertEquals(values, formItem.getItemValues());
    }

    @Test
    public void setItemValues_filled() throws Exception {
        HashMap<String, String> values = new HashMap<>();
        values.put("check", "check");
        values.put("these", "these");
        values.put("unit", "unit");
        values.put("tests", "tests");
        formItem.setItemValues(values);
        assertEquals(values, formItem.getItemValues());
    }

    @Test
    public void setItemValues_null() throws Exception {
        HashMap<String, String> value = new HashMap<>();
        HashMap<String, String> values = null;
        formItem.setItemValues(values);
        assertEquals(value, formItem.getItemValues());
    }
}
