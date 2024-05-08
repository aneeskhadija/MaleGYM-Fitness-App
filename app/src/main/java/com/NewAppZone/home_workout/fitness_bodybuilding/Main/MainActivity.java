package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.NewAppZone.home_workout.fitness_bodybuilding.Setting.BaseSettingActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Workout.Frag_Workouts_Dashboard;
import com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan.YourPlanActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CATEGORY_TYPE = "categorytype";
    public static final String DATABASE_NAME = "data311";
    public static final String DATABASE_NAME2 = "data";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static final String DES_EXERCISE_SEND = "des_exercise_send";
    public static final String ID_EXERCISE_SEND = "id_exercise_send";
    public static final String ISHOW = "ISHOW";
    public static final String NAME_EXERCISE_SEND = "name_exercise_send";
    public static final String SHAREPRE = "YourPlan";
    public static final String TABLE_CATEGORY = "exercices_types";
    public static final String TABLE_CATEGORY_GUIDE = "categories_guides";
    public static final String TABLE_CUSTOM_EXERCISE = "CustomExercise";
    public static final String TABLE_CUSTOM_WORKOUT = "CustomWorkout";
    public static final String TABLE_DAYS = "days";
    public static final String TABLE_EXERCISES = "exercices";
    public static final String TABLE_FOTO = "foto";
    public static final String TABLE_GUIDE = "guides";
    public static final String TABLE_REMINDER = "Reminder";
    private DrawerLayout drawer;

 //   AdView mAdView;

    /*private InterstitialAd interstitialAd;
    InterstitialAdListener interstitialAdListener;*/

    protected Bundle mSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AudienceNetworkAds.initialize(this);
        mSavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        /*adView = new AdView(this, "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        /*interstitialAd = new InterstitialAd(this, "627859701435249_627860684768484");
        // Create listeners for the Interstitial Ad
        interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
               // Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                //Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
               // Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                //Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
               // Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
               // Log.d(TAG, "Interstitial ad impression logged!");
            }
        };*/

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        Frag_Main frag_main = new Frag_Main();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_main, frag_main);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.action_settings) {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_exercise) {

            Frag_Main frag_main = new Frag_Main();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_main, frag_main);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_workouts) {

            Frag_Workouts_Dashboard frag_main2 = new Frag_Workouts_Dashboard();
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.frag_main, frag_main2);
            transaction2.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction2.addToBackStack(null);
            transaction2.commit();

        } else if (id == R.id.nav_step_count) {

            startActivity(new Intent(this, StepCount.class));
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        } else if (id == R.id.nav_diet_tips) {

            startActivity(new Intent(this, DietTips.class));
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        }else if (id == R.id.nav_custom) {

            startActivity(new Intent(this, YourPlanActivity.class));
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        } else if (id == R.id.nav_setting) {

            startActivity(new Intent(this, BaseSettingActivity.class));
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction("android.intent.action.SEND");
            sendIntent.putExtra("android.intent.extra.TEXT", getString(R.string.app_name) + "\n\n https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_rate) {

           // String appPackageName = getPackageName();
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
            }
        } else if (id == R.id.nav_mail) {
            String mailto = "mailto:" + "torofy1@gmail.com";
            Intent emailIntent = new Intent("android.intent.action.SENDTO");
            emailIntent.setData(Uri.parse(mailto));
            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e2) {
            }
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        int back_stack_count = fm.getBackStackEntryCount();
        DrawerLayout drawer2 = findViewById(R.id.drawer_layout);

        if (drawer2.isDrawerOpen(GravityCompat.START)) {

            drawer2.closeDrawer(GravityCompat.START);

        } else if (back_stack_count <= 0) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.rate_us);
            alertDialogBuilder.setMessage(R.string.rate_message);
            alertDialogBuilder.setNegativeButton(R.string.cancel, null);
            alertDialogBuilder.setNeutralButton(R.string.rate_us, new OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                   // String appPackageName = getPackageName();
                    try {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
                    }
                }
            });
            alertDialogBuilder.setPositiveButton(R.string.quit, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            });
            alertDialogBuilder.create().show();
        } /*else if (back_stack_count == 2) {
           // showAds();
            interstitialAd.loadAd(
                    interstitialAd.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());
        }*/ else {
            fm.popBackStack();
        }
    }

}
