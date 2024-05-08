package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class Exercise_Adapter extends RecyclerView.Adapter<Exercise_Adapter.SongViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Exercise> mSongs;

    class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView tvCode;
        private TextView tvTitle;

        public SongViewHolder(View itemView) {
            super(itemView);
            this.tvCode = itemView.findViewById(R.id.thumbnail);
            this.tvTitle = itemView.findViewById(R.id.count);
        }
    }

    public Exercise_Adapter(Context context, List<Exercise> datas) {
        this.mContext = context;
        this.mSongs = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(this.mLayoutInflater.inflate(R.layout.coach_card, parent, false));
    }

    public void onBindViewHolder(SongViewHolder holder, int position) {
        Exercise item = mSongs.get(position);
        Picasso.with(mContext).load("file:///android_asset/images/" + item.getIdExercise() + "-min.png").into(holder.tvCode);
        holder.tvTitle.setText(item.getName());
    }

    public int getItemCount() {
        return mSongs.size();
    }
}
