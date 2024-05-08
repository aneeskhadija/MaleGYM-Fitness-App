package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    private List<Plan> albumList;
    private Context mContext;

    class InputFilterMinMax implements InputFilter {
        private int max;
        private int min;

        public InputFilterMinMax(int min2, int max2) {
            this.min = min2;
            this.max = max2;
        }

        public InputFilterMinMax(String min2, String max2) {
            this.min = Integer.parseInt(min2);
            this.max = Integer.parseInt(max2);
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                if (isInRange(this.min, this.max, Integer.parseInt(dest.toString() + source.toString()))) {
                    return null;
                }
            } catch (NumberFormatException e) {
            }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private int pos;

        public MyMenuItemClickListener(int pos2) {
            this.pos = pos2;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            final Plan plan = (Plan) albumList.get(pos);
            final SQLiteDatabase database3 = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
            switch (menuItem.getItemId()) {
                case R.id.action_addname  :
                    Builder dialogBuilder = new Builder(mContext);
                    View dialogView = LayoutInflater.from(mContext).inflate(R.layout.newnamedialog, null);
                    dialogBuilder.setView(dialogView);
                    final EditText edt = (EditText) dialogView.findViewById(R.id.edit);
                    dialogBuilder.setTitle(R.string.work_title);
                    dialogBuilder.setPositiveButton("OK", null);
                    final AlertDialog b = dialogBuilder.create();
                    b.show();
                    b.getButton(-1).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (edt.getText() == null) {
                                return;
                            }
                            if (edt.getText().toString().length() > 0) {
                                String name = edt.getText().toString();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("NamePlan", edt.getText().toString());
                                database3.update(MainActivity.TABLE_CUSTOM_WORKOUT, contentValues, "IDPlan LIKE ?", new String[]{"" + plan.getIdPlan()});
                                plan.setName(name);
                                albumList.set(pos, plan);
                                notifyDataSetChanged();
                                b.dismiss();
                                return;
                            }
                            Toast.makeText(mContext, R.string.erorr, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                case R.id.action_delete  :
                    Builder builder = new Builder(mContext);
                    builder.setTitle(R.string.exit);
                    builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            database3.delete(MainActivity.TABLE_CUSTOM_WORKOUT, "IDPlan LIKE ?", new String[]{"" + plan.getIdPlan()});
                            albumList.remove(pos);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setPositiveButton(R.string.cancel, null);
                    builder.create().show();
                    return true;
                default:
                    return false;
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public TextView count;
        public ImageView overflow;
        public TextView thumbnail;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.count = (TextView) view.findViewById(R.id.count);
            this.thumbnail = (TextView) view.findViewById(R.id.thumbnail);
            this.overflow = (ImageView) view.findViewById(R.id.overflow);
            view.setOnClickListener(this);
            this.thumbnail.setOnClickListener(this);
            this.title.setOnClickListener(this);
            this.overflow.setOnClickListener(this);
        }

        public void onClick(View v) {
            if (v.getId() == overflow.getId()) {
                showPopupMenu(overflow, getAdapterPosition());
                return;
            }
            YourPlanActivity myActivity = (YourPlanActivity) mContext;
            Frag_show_your_create_2 fragment = new Frag_show_your_create_2();
            FragmentTransaction transaction = myActivity.getSupportFragmentManager().beginTransaction();
            int adapterPosition = getAdapterPosition() + 1;
            Plan plan = (Plan) albumList.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putInt(MainActivity.NAME_EXERCISE_SEND, plan.getIdPlan());
            bundle.putInt(MainActivity.ID_EXERCISE_SEND, Integer.parseInt(plan.getReps()));
            bundle.putString(MainActivity.DES_EXERCISE_SEND, plan.getName());
            fragment.setArguments(bundle);
            transaction.replace(R.id.frag_show, fragment);
            transaction.addToBackStack("1");
            transaction.commit();
        }
    }

    public PlanAdapter(Context mContext2, List<Plan> albumList2) {
        mContext = mContext2;
        albumList = albumList2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_plan_card, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        String str = "";
        Plan album = (Plan) albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getReps() + " " + mContext.getString(R.string.days_of_week));
        holder.thumbnail.setText(String.valueOf(album.getName().charAt(0)));
    }

    private void showPopupMenu(View view, int pos) {
        PopupMenu popup = new PopupMenu(mContext, view);
        popup.getMenuInflater().inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(pos));
        popup.show();
    }

    public int getItemCount() {
        return albumList.size();
    }
}
