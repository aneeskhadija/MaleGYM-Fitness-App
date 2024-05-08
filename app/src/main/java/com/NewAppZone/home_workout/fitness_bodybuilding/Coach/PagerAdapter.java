package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.os.Bundle;
import androidx.fragment.app.Fragment;


import androidx.fragment.app.FragmentManager;


import androidx.fragment.app.FragmentStatePagerAdapter;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Exercise exercise;

    PagerAdapter(FragmentManager fragmentManager, Exercise exercise2) {
        super(fragmentManager);
        this.exercise = exercise2;
    }

    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new Frag_1();
                break;
            case 1:
                frag = new Frag_2();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, this.exercise);
        frag.setArguments(bundle);
        return frag;
    }

    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                return "One";
            case 1:
                return "Two";
            default:
                return title;
        }
    }
}
