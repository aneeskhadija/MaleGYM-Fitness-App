package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DietTips extends AppCompatActivity {

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_diet_tips);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        /*adView = new AdView(this, "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();

        adView1 = new AdView(this, "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer1 = (LinearLayout) findViewById(R.id.banner_container1);
        // Add the ad view to your activity layout
        adContainer1.addView(adView1);
        // Request an ad
        adView1.loadAd();*/
    }
}