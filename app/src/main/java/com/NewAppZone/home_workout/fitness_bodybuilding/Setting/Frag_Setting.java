package com.NewAppZone.home_workout.fitness_bodybuilding.Setting;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Reminder.Frag_Reminder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Frag_Setting extends Fragment {

    private LinearLayout linearLanguage;
    private LinearLayout txtContactUs;
    private LinearLayout txtRatingApp;
    private LinearLayout Privacypolicy;
    private LinearLayout txtReminder;
    private LinearLayout txtShareApp;
    private TextView txt_nameLanguage;
   // AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__setting, container, false);



        getActivity().setTitle(getString(R.string.setting));
        khaibaovaAnhXa(view);
        xuLyOnClickListenner();
        return view;
    }

    private void xuLyOnClickListenner() {
        linearLanguage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showPopUpSelectLanguage();
            }
        });
        txtContactUs.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String mailto = "mailto:" + "torofy1@gmail.com";
                Intent emailIntent = new Intent("android.intent.action.SENDTO");
                emailIntent.setData(Uri.parse(mailto));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                }
            }
        });
        txtShareApp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT", getString(R.string.workoutDescription2) + "\n\n https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        txtRatingApp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            //    String appPackageName = getActivity().getPackageName();
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.NewAppZone.home_workout.fitness_bodybuilding")));
                }
            }
        });

        Privacypolicy.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://midribtechandroid.blogspot.com/p/blog-page.html")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://midribtechandroid.blogspot.com/p/blog-page.html")));
                }
            }
        });

        txtReminder.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new Frag_Reminder();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_frameSet, fragment);
                transaction.addToBackStack(MainActivity.TABLE_REMINDER);
                transaction.commit();
            }
        });
    }

    private void showPopUpSelectLanguage() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_frameSet, new Frag_Language()).addToBackStack(null).commit();
    }

    private void khaibaovaAnhXa(View view) {
        linearLanguage = view.findViewById(R.id.linearLanguage);
        txtReminder = view.findViewById(R.id.txtReminder);
        txtContactUs = view.findViewById(R.id.txtContactUs);
        txtShareApp = view.findViewById(R.id.txtShareApp);
        txtRatingApp = view.findViewById(R.id.txtRatingApp);
        Privacypolicy = view.findViewById(R.id.txt_privacy_policy);
        txt_nameLanguage = view.findViewById(R.id.txt_name_language);
        txt_nameLanguage.setText(getActivity().getSharedPreferences(MainActivity.SHAREPRE, 0).getString(MainActivity.DES_EXERCISE_SEND, "English"));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(getString(R.string.app_name));
    }
}
