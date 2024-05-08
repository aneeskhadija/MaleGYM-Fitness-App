package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import androidx.viewpager.widget.ViewPager;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import me.relex.circleindicator.CircleIndicator;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Frag_Show extends Fragment {

    private TextView des_exercise;
    Exercise exercise;
    private TextView name_exercise;
    private ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag__show, container, false);
        addControl(view);
        setText(view);
        return view;
    }

    private void setText(View view) {
        this.name_exercise = view.findViewById(R.id.name_exercise);
        this.des_exercise = view.findViewById(R.id.des_exercise);
        this.des_exercise.setMovementMethod(new ScrollingMovementMethod());
        this.name_exercise.setText(this.exercise.getName());
        this.des_exercise.setText(this.exercise.getDes());
        getActivity().setTitle(this.exercise.getName());
    }

    private void addControl(View view) {
        exercise = getArguments().getParcelable(MainActivity.NAME_EXERCISE_SEND);
        pager = view.findViewById(R.id.view_pager);
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), exercise);
        pager.setAdapter(adapter);
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
    }
}
