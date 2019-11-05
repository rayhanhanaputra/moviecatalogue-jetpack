package com.rayhanhanaputra.moviecataloguevm.activity;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteActivityTest {

    @Rule
    public ActivityTestRule<FavoriteActivity> activityRule = new ActivityTestRule<>(FavoriteActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void checkFavorite() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));

        onView(withId(R.id.view_pager2)).perform(swipeLeft());

        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
    }
}