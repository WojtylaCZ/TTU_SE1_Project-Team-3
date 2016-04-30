package com.ttu_se1_project_team_3;

import org.junit.Rule;
import org.junit.Test;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.support.test.*;
import android.support.test.rule.ActivityTestRule;
import android.app.Instrumentation.ActivityMonitor;
import android.app.Instrumentation;
import android.test.InstrumentationTestCase;

import com.ttu_se1_project_team_3.activities.Login;
import com.ttu_se1_project_team_3.activities.Welcome;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.util.Log;
import android.widget.Button;

/**
 * Created by Isaac on 4/30/2016.
 * UI Tests for the Welcome Activity
 */
public class WelcomeTests extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<Welcome> mActivityRule = new ActivityTestRule<>(
            Welcome.class);

    private final String LOG_TAG = Welcome.class.getSimpleName();
    private Instrumentation instr;
    private Welcome welcome = mActivityRule.getActivity();

    @Override
    protected void setUp() throws Exception {
        instr = getInstrumentation();
        Log.d(LOG_TAG, "setUp instrumentation: " + instr);

        super.setUp();

        instr = getInstrumentation();
        Log.d(LOG_TAG, "setUp instrumentation: " + instr);
    }

    public void test_goToLoginActivity() {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Login.class.getName(), null, false);

        // Type text and then press the button.
        onView(withId(R.id.login_button)).perform(click());
        final Button button = (Button) welcome.findViewById(R.id.login_button);
        welcome.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });

        Login login = (Login)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(login);
        login.finish();
    }
}
