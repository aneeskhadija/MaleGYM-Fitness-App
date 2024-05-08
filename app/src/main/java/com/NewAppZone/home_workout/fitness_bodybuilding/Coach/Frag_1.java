package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Frag_1 extends Fragment {
    private VideoView img_Gif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1, container, false);
        setView(view);
        return view;
    }

    private void setView(View view) {
        String path = "android.resource://" + getActivity().getPackageName() + "/raw/ex" + ((Exercise) getArguments().getParcelable(MainActivity.NAME_EXERCISE_SEND)).getIdExercise();
        this.img_Gif = (VideoView) view.findViewById(R.id.imageView);
        this.img_Gif.setVideoURI(Uri.parse(path));
        this.img_Gif.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        this.img_Gif.start();
    }

    public void onDetach() {
        super.onDetach();
        if (this.img_Gif != null && this.img_Gif.isPlaying()) {
            this.img_Gif.stopPlayback();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.img_Gif != null) {
            this.img_Gif.start();
        }
    }
}
