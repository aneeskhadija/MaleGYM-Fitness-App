package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.List;

public class ItemWorkout_Adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater = null;
    private List<ItemWorkout> list;

    class ViewHolder {
        TextView txtDay;

        ViewHolder() {
        }
    }

    public ItemWorkout_Adapter(List<ItemWorkout> list2, Context context2) {

        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_workout, null);
            holder = new ViewHolder();
            holder.txtDay = (TextView) convertView.findViewById(R.id.txt_nameWorkout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ItemWorkout item = (ItemWorkout) getItem(position);
        if (item != null) {
            holder.txtDay.setText(item.getNameWorkout());
            holder.txtDay.setBackgroundResource(item.getColorWorkout());
        }
        return convertView;
    }
}
