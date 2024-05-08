package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.content.Context;

public class LanguageReturn {
    private Context context;

    public LanguageReturn(Context context2) {
        this.context = context2;
    }

    public String getLanguage() {
        return this.context.getSharedPreferences(MainActivity.SHAREPRE, 0).getString(MainActivity.NAME_EXERCISE_SEND, "en");
    }
}
