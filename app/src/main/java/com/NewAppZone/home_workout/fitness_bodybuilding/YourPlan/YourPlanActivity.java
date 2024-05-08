package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;


import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Frag_Show;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.caiDatConfirgution;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class YourPlanActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;
   // private AdView adView;
   AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_your_plan);
        new caiDatConfirgution(getApplicationContext()).okSetting();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*adView = new AdView(this, "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

      /*  interstitialAd = new InterstitialAd(this, "627859701435249_627860684768484");
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
               // Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
               // Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
              //  Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
              //  Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                //Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                //Log.d(TAG, "Interstitial ad impression logged!");
            }
        };
*/

        if (getIntent().getBooleanExtra(MainActivity.ISHOW, false)) {

            /*interstitialAd.loadAd(
                    interstitialAd.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());*/

            Frag_Show fragment = new Frag_Show();
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, getIntent().getParcelableExtra(MainActivity.NAME_EXERCISE_SEND));
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_show, fragment);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.commit();

        } else {
            Frag_tao_planMoi_1 fragment = new Frag_tao_planMoi_1();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_show, fragment);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.commit();
        }

    }

    /*private void showAds() {
        if (mInterstitialAd == null || !mInterstitialAd.isLoaded()) {

        } else {
            mInterstitialAd.show();
        }
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                Frag_Show fragment = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, getIntent().getParcelableExtra(MainActivity.NAME_EXERCISE_SEND));
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_show, fragment);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.commit();
            }
        });
    }*/


}
