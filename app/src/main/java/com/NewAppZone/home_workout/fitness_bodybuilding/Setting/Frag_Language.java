package com.NewAppZone.home_workout.fitness_bodybuilding.Setting;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
 import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import androidx.annotation.IdRes;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.Locale;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.SplashScreen;

public class Frag_Language extends Fragment {

    private String language;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__language, container, false);
        final String[] list_02 = {"English", "Deutsch", "Spanish", "French", "Italian", "Japanese", "Korean", "Portuguese", "Russian", "Swedish", "Turkish", "Chinese", "Dutch", "Thai"};
        final String[] list = {"en", "de", "es", "fr", "it", "ja", "ko", "pt", "ru", "sv", "tr", "zh", "nl", "th"};
        RadioGroup group = view.findViewById(R.id.radio_language);
        for (String text : list_02) {
            RadioButton button = new RadioButton(getActivity());
            button.setText(text);
            group.addView(button);
        }
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int idx = group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
                language = list[idx];
                Editor editor = getActivity().getSharedPreferences(MainActivity.SHAREPRE, 0).edit();
                editor.putString(MainActivity.NAME_EXERCISE_SEND, language);
                editor.putString(MainActivity.DES_EXERCISE_SEND, list_02[idx]);
                editor.apply();
                Resources res = getActivity().getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.setLocale(new Locale(language.toLowerCase()));
                res.updateConfiguration(conf, dm);
                getActivity().recreate();
                startActivity(new Intent(getActivity(), SplashScreen.class));
                getActivity().finish();
            }
        });
        return view;
    }

    @TargetApi(24)
    public Locale getCurrentLocale() {
        if (VERSION.SDK_INT >= 24) {
            return getResources().getConfiguration().getLocales().get(0);
        }
        return getResources().getConfiguration().locale;
    }
}
