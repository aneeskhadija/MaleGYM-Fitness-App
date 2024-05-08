package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

public class caiDatConfirgution {
    private Context context;

    public caiDatConfirgution(Context context2) {
        this.context = context2;
    }

    public void okSetting() {
        String language = context.getSharedPreferences(MainActivity.SHAREPRE, 0).getString(MainActivity.NAME_EXERCISE_SEND, "en");
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(language.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }
}
