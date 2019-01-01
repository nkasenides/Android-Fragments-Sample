package com.panickapps.androidfragmentssampleproject.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.panickapps.androidfragmentssampleproject.R;
import com.panickapps.androidfragmentssampleproject.View.Fragments.AboutFragment;
import com.panickapps.androidfragmentssampleproject.View.Fragments.CFragment;
import com.panickapps.androidfragmentssampleproject.View.Fragments.MainFragment;
import com.panickapps.androidfragmentssampleproject.View.Fragments.PreferencesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String INITIAL_FRAGMENT_KEY = "InitialFragment";

    //UI:
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private View navigationHeader;

    //TODO Nav Header UI Elements

    //Fragments:
    private static MainFragment mainFragment;
    private static AboutFragment aboutFragment;
    private static PreferencesFragment preferencesFragment;

    //Data:
    //TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI:
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationHeader = navigationView.getHeaderView(0);

        //TODO Set up the Nav Header UI elements

        //Start the first fragment:
        CFragment initialFragment = (CFragment) getIntent().getSerializableExtra(INITIAL_FRAGMENT_KEY);
        if (initialFragment == null) showFragmentAsInitial(CFragment.MAIN_FRAGMENT);
        else showFragmentAsInitial(initialFragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) { //If drawer is open, close it
            drawer.closeDrawer(GravityCompat.START);
        }
        else { //Otherwise
            FragmentManager manager = getSupportFragmentManager();
            if(manager.getBackStackEntryCount() > 0) {
                super.onBackPressed();
                Fragment currentFragment = manager.findFragmentById(R.id.frame);
                if (currentFragment instanceof MainFragment){
                    navigationView.getMenu().getItem(0).setChecked(true);
                    setTitle(CFragment.MAIN_FRAGMENT.getTitle());
                }
                else if(currentFragment instanceof AboutFragment){
                    navigationView.getMenu().getItem(1).setChecked(true);
                    setTitle(CFragment.ABOUT_FRAGMENT.getTitle());
                }
                else if(currentFragment instanceof PreferencesFragment){
                    navigationView.getMenu().getItem(2).setChecked(true);
                    setTitle(CFragment.PREFERENCES_FRAGMENT.getTitle());
                }
            }
            else {
                super.onBackPressed();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                //TODO Define action
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment newFragment = null;
        String newFragmentTag = null;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left_to_right, R.anim.slide_right_to_left, R.anim.slide_left_to_right, R.anim.slide_right_to_left);

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame);

        boolean existed = false;

        switch (id) {
            case R.id.nav_main:
                setTitle(CFragment.MAIN_FRAGMENT.getTitle());
                newFragmentTag = CFragment.MAIN_FRAGMENT.getTag();
                if (!(currentFragment instanceof MainFragment)) {
                    newFragment = (mainFragment == null) ? new MainFragment() : mainFragment;
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
                else existed = true;
                break;
            case R.id.nav_about:
                setTitle(CFragment.ABOUT_FRAGMENT.getTitle());
                newFragmentTag = CFragment.ABOUT_FRAGMENT.getTag();
                if (!(currentFragment instanceof AboutFragment)) {
                    newFragment = (aboutFragment == null) ? new AboutFragment() : aboutFragment;
                    navigationView.getMenu().getItem(1).setChecked(true);
                }
                else existed = true;
                break;
            case R.id.nav_preferences:
                setTitle(CFragment.PREFERENCES_FRAGMENT.getTitle());
                newFragmentTag = CFragment.PREFERENCES_FRAGMENT.getTag();
                if (!(currentFragment instanceof PreferencesFragment)) {
                    newFragment = (preferencesFragment == null) ? new PreferencesFragment() : preferencesFragment;
                    navigationView.getMenu().getItem(2).setChecked(true);
                }
                else existed = true;
                break;
        }

        if (newFragment != null && !existed) {
            fragmentTransaction.replace(R.id.frame, newFragment, newFragmentTag);
            fragmentTransaction.addToBackStack(newFragmentTag);
            fragmentTransaction.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showFragmentAsInitial(CFragment fragment) {

        Fragment defaultFragment;
        FragmentTransaction fragmentTransaction;

        switch (fragment) {
            case MAIN_FRAGMENT:
                defaultFragment = (mainFragment == null) ? new MainFragment() : mainFragment;
                setTitle(CFragment.MAIN_FRAGMENT.getTitle());
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, defaultFragment, CFragment.MAIN_FRAGMENT.getTag());
                fragmentTransaction.commit();
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case ABOUT_FRAGMENT:
                defaultFragment = (aboutFragment== null) ? new AboutFragment() : aboutFragment;
                setTitle(CFragment.ABOUT_FRAGMENT.getTitle());
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, defaultFragment, CFragment.ABOUT_FRAGMENT.getTag());
                fragmentTransaction.commit();
                navigationView.getMenu().getItem(1).setChecked(true);
                break;
            case PREFERENCES_FRAGMENT:
                defaultFragment = (preferencesFragment == null) ? new PreferencesFragment() : preferencesFragment;
                setTitle(CFragment.PREFERENCES_FRAGMENT.getTitle());
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, defaultFragment, CFragment.PREFERENCES_FRAGMENT.getTag());
                fragmentTransaction.commit();
                navigationView.getMenu().getItem(2).setChecked(true);
                break;
        }
    }



}
