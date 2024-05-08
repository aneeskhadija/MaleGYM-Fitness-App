package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class WorkoutPlan_Adapter extends RecyclerView.Adapter<WorkoutPlan_Adapter.MyViewHolder> {

    private List<PlanWorkout> albumList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public TextView datecreate;
        public ImageView thumbnail;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.datecreate = (TextView) view.findViewById(R.id.datecreate);
            this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setOnClickListener(this);
            this.thumbnail.setOnClickListener(this);
            this.title.setOnClickListener(this);
        }

        public void onClick(View v) {
        }
    }

    public WorkoutPlan_Adapter(Context mContext2, List<PlanWorkout> albumList2) {
        this.mContext = mContext2;
        this.albumList = albumList2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.your_plan_card, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlanWorkout album = (PlanWorkout) this.albumList.get(position);
        holder.title.setText(album.getName());
        holder.datecreate.setText(album.getReps());
        Picasso.with(this.mContext).load("file:///android_asset/images/" + album.getIdPlan() + "-min.png").into(holder.thumbnail);
        Log.d("HEHE", album.getIdPlan() + "");
    }

    public int getItemCount() {
        return this.albumList.size();
    }
}
