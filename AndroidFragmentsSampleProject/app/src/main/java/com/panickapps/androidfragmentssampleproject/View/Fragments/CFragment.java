package com.panickapps.androidfragmentssampleproject.View.Fragments;

/**
 * Project: buzzchat
 * Created by hfnov on 17-Nov-18 for PaNickApps.
 */
public enum CFragment {

    MAIN_FRAGMENT("Main", "MainFragment", MainFragment.class),
    ABOUT_FRAGMENT("About", "AboutFragment", AboutFragment.class),
    PREFERENCES_FRAGMENT("Preferences", "PreferencesFragment", PreferencesFragment.class),

    ;

    private String TITLE;
    private String TAG;
    private Class fragmentClass;

    CFragment(String TITLE, String TAG, Class fragmentClass) {
        this.TITLE = TITLE;
        this.TAG = TAG;
        this.fragmentClass = fragmentClass;
    }

    public String getTitle() {
        return TITLE;
    }

    public String getTag() {
        return TAG;
    }

    public Class getFragmentClass() {
        return fragmentClass;
    }

}
