package com.ttu_se1_project_team_3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ttu_se1_project_team_3.activities.Login;
import com.ttu_se1_project_team_3.activities.Welcome;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Created by Isaac on 4/30/2016.
 * UI Tests for the Welcome Activity.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class  WelcomeTests {

    @Rule
    public final ActivityTestRule<Welcome> rule =
            new ActivityTestRule<>(Welcome.class);

    @Test
    public void test_goToLoginActivity() throws Exception {
        Intents.init();

        onView(withId(R.id.login_button)).perform(click());

        intended(hasComponent(Login.class.getName()));

        Intents.release();
    }
}
