package com.ttu_se1_project_team_3.model_tests;

import com.ttu_se1_project_team_3.model.StudyTemplate;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Isaac on 4/11/2016.
 * This set of unit tests is used to verify correctness for StudyTemplate.
 */
public class StudyTemplateTests {

    StudyTemplate template = new StudyTemplate();

    @Test
    public void getInputOptions_isNull() throws Exception {
        template.clearTemplate();
        HashMap<String, String> content = new HashMap<>();
        assertEquals(content, template.getInputOptions(null));
    }

    @Test
    public void getInputOptions_isEmpty() throws Exception {
        template.clearTemplate();
        HashMap<String, String> content = new HashMap<>();
        assertEquals(content, template.getInputOptions(""));
    }
}
