package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Frag_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2, container, false);
        Picasso.with(getActivity()).load("file:///android_asset/images/icon" + ((Exercise) getArguments().getParcelable(MainActivity.NAME_EXERCISE_SEND)).getCode() + ".png").into((ImageView) view.findViewById(R.id.img_exercise));
        return view;
    }
}
