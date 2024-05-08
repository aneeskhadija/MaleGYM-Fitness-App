package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Frag_Show_List_1;

public class MainPlanAdapter extends RecyclerView.Adapter<MainPlanAdapter.MyViewHolder> {
    private List<Album> albumList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public ImageView thumbnail;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.thumbnail = view.findViewById(R.id.thumbnail);
            view.setOnClickListener(this);
            this.thumbnail.setOnClickListener(this);
            this.title.setOnClickListener(this);
        }

        public void onClick(View v) {
            MainActivity activity = (MainActivity) mContext;
            Fragment fragment = new Frag_Show_List_1();
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.CATEGORY_TYPE, getAdapterPosition() + 1);
            bundle.putString(MainActivity.NAME_EXERCISE_SEND, albumList.get(getAdapterPosition()).getName());
            fragment.setArguments(bundle);
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(R.id.frag_main, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public MainPlanAdapter(Context mContext2, List<Album> albumList2) {
        this.mContext = mContext2;
        this.albumList = albumList2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_card, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        Picasso.with(mContext).load("file:///android_asset/images/" + album.getThumbnail() + ".png").into(holder.thumbnail);
    }

    public int getItemCount() {
        return albumList.size();
    }
}
