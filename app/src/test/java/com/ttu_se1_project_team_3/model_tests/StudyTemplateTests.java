package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.InputT;
import com.ttu_se1_project_team_3.model.SessionDataField;
import com.ttu_se1_project_team_3.model.SessionLogField;
import com.ttu_se1_project_team_3.model.StudyTemplate;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Isaac on 4/11/2016.
 * This set of unit tests is used to verify correctness for StudyTemplate.
 */
public class StudyTemplateTests {

    StudyTemplate template = new StudyTemplate();
    boolean testsSetup = false;

    @Before
    public void clearTest() throws Exception {
        template.setName("");
        ArrayList<SessionDataField> sessionDataFields = new ArrayList<>();
        template.setSessionDataFields(sessionDataFields);
        ArrayList<SessionLogField> sessionLogFields = new ArrayList<>();
        template.setSessionLogFields(sessionLogFields);
    }

    @Test
    public void setName_chars() throws Exception {
        template.setName("test_name");
        assertEquals("test_name", template.getName());
    }

    @Test
    public void setName_null() throws Exception {
        template.setName(null);
        assertEquals("", template.getName());
    }

    @Test
    public void addSessionLogs_checkDuplicate() throws Exception {
        ArrayList<SessionLogField> logList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        SessionLogField logField = new SessionLogField();
        SessionLogField logField01 = new SessionLogField();
        SessionLogField logField02 = new SessionLogField();

        logField.setItemName("first");
        logField.setItemInput(InputT.TEXT);
        logField.setItemValues(hashMap);
        logList.add(logField);

        logField01.setItemName("second");
        logField01.setItemInput(InputT.CHECKBOXES);
        logField01.setItemValues(hashMap);
        logList.add(logField01);

        logField02.setItemName("third");
        logField02.setItemInput(InputT.TEXT);
        logField02.setItemValues(hashMap);
        logList.add(logField02);

        setupTests();

        for (int i = 0; i < logList.size(); i++) {
            assertEquals(logList.get(i).getItemName(), template.getSessionLogFields().get(i).getItemName());
        }
    }

    @Test
    public void getInputOptions_isNull() throws Exception {
        HashMap<String, String> content = new HashMap<>();
        assertEquals(content, template.getInputOptions(null));
    }

    @Test
    public void getInputOptions_isEmpty() throws Exception {
        HashMap<String, String> content = new HashMap<>();
        assertEquals(content, template.getInputOptions(""));
    }

    private void setupTests() {
        if (!testsSetup) {
            // SessionLogFields
            HashMap<String, String> hashMap = new HashMap<>();
            template.addSessionLogField("first", "Text", hashMap);
            template.addSessionLogField("second", "Checkboxes", hashMap);
            template.addSessionLogField("third", "Text", hashMap);
            template.addSessionLogField("first", "Checkboxes", hashMap);

            testsSetup = true;
        }
    }
}
