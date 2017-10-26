package io.full.calculator;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import io.full.calculator.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Shangeeth on 14/07/17.
 */

public class CalculateTest {

    @Rule
    public ActivityTestRule<MainActivity> mTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addition(){

        onView(withId(R.id.number_1)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("1")));

        onView(withId(R.id.operation_add)).perform(click());

        onView(withId(R.id.number_7)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("7")));

        onView(withId(R.id.operation_equalTo)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("8")));
    }

    @Test
    public void subraction(){

        onView(withId(R.id.number_2)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("2")));

        onView(withId(R.id.operation_subtract)).perform(click());

        onView(withId(R.id.number_4)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("4")));

        onView(withId(R.id.operation_equalTo)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("-2")));
    }

    @Test
    public void multiply(){

        onView(withId(R.id.number_2)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("2")));

        onView(withId(R.id.operation_multiply)).perform(click());

        onView(withId(R.id.number_4)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("4")));

        onView(withId(R.id.operation_equalTo)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("8")));
    }

    @Test
    public void divide(){

        onView(withId(R.id.number_6)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("6")));

        onView(withId(R.id.operation_division)).perform(click());

        onView(withId(R.id.number_3)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("3")));

        onView(withId(R.id.operation_equalTo)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("2")));
    }

    @Test
    public void modulo(){

        onView(withId(R.id.number_6)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("6")));

        onView(withId(R.id.operation_modulo)).perform(click());

        onView(withId(R.id.number_3)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("3")));

        onView(withId(R.id.operation_equalTo)).perform(click());
        onView(withId(R.id.operand2Line)).check(matches(withText("0")));
    }

}
