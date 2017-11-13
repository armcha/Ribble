package io.armcha.ribble.presentation.screen.dispatch;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.armcha.ribble.R;
import io.armcha.ribble.presentation.screen.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DispatchActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void dispatchActivityTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction arcView = onView(
                allOf(withId(R.id.arcView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()));
        arcView.perform(click());

        ViewInteraction recyclerView = onView(
                childAtPosition(
                        withId(R.id.navView),
                        1));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.likesRecyclerView),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(10, click()));

        ViewInteraction arcView2 = onView(
                allOf(withId(R.id.arcView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()));
        arcView2.perform(click());

        ViewInteraction arcView3 = onView(
                allOf(withId(R.id.arcView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()));
        arcView3.perform(click());

        ViewInteraction recyclerView3 = onView(
                childAtPosition(
                        withId(R.id.navView),
                        1));
        recyclerView3.perform(actionOnItemAtPosition(3, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction arcView4 = onView(
                allOf(withId(R.id.arcView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()));
        arcView4.perform(click());

        ViewInteraction recyclerView4 = onView(
                childAtPosition(
                        withId(R.id.navView),
                        1));
        recyclerView4.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.shotRecyclerView),
                                childAtPosition(
                                        withClassName(is("android.support.constraint.ConstraintLayout")),
                                        0)),
                        3),
                        isDisplayed()));
        linearLayout.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
