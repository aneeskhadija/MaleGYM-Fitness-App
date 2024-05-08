package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Exercise;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Frag_Show;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Plan_exercise_selected_adapter extends RecyclerView.Adapter<Plan_exercise_selected_adapter.MyViewHolder> {

    private int idPlanNow;
    private List<Item_Show> list_showItem;
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
            final Item_Show plan = list_showItem.get(this.pos);
            final SQLiteDatabase database = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
            switch (menuItem.getItemId()) {
                case R.id.action_delete  :
                    Builder builder = new Builder(mContext);
                    builder.setTitle(R.string.exit);
                    builder.setNeutralButton(R.string.delete, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int delete = database.delete(MainActivity.TABLE_CUSTOM_EXERCISE, "id LIKE ?", new String[]{"" + plan.getId()});
                            list_showItem.remove(pos);
                            notifyDataSetChanged();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("custom", "0");
                            database.update(MainActivity.TABLE_EXERCISES, contentValues, "id_exercice LIKE ?", new String[]{"" + plan.getId_exercise()});
                            database.close();
                        }
                    });
                    builder.setPositiveButton(R.string.cancel, null);
                    builder.create().show();
                    return true;
                case R.id.action_editreps :
                    Builder dialogBuilder = new Builder(mContext);
                    View dialogView = LayoutInflater.from(mContext).inflate(R.layout.addexercisedialog, null);
                    dialogBuilder.setView(dialogView);
                    final EditText edt = dialogView.findViewById(R.id.edit1);
                    final EditText etime = dialogView.findViewById(R.id.edit2);
                    etime.setFilters(new InputFilter[]{new InputFilterMinMax("1", "120")});
                    edt.setFilters(new InputFilter[]{new InputFilterMinMax("1", "20")});
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.setNegativeButton(R.string.cancel, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
                    final AlertDialog b = dialogBuilder.create();
                    b.show();
                    b.getButton(-1).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Boolean valueOf = false;
                            if (edt.getText() == null) {
                                return;
                            }
                            if (edt.getText().toString().length() <= 0) {
                                Toast.makeText(mContext, R.string.erorr, Toast.LENGTH_SHORT).show();
                            } else if (etime.getText() == null) {
                            } else {
                                if (etime.getText().toString().length() > 0) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("number_set", edt.getText().toString());
                                    contentValues.put("reps", etime.getText().toString());
                                    long update = (long) database.update(MainActivity.TABLE_CUSTOM_EXERCISE, contentValues, "id LIKE ?", new String[]{String.valueOf(plan.getId())});
                                    plan.setNumber_set(Integer.parseInt(edt.getText().toString()));
                                    plan.setReps(Integer.parseInt(etime.getText().toString()));
                                    list_showItem.set(pos, plan);
                                    notifyDataSetChanged();
                                    b.dismiss();
                                    return;
                                }
                                Toast.makeText(mContext, R.string.erorr, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    return true;
                default:
                    return false;
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView count;
        public ImageView overflow;
        public ImageView thumbnail;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.count = view.findViewById(R.id.count);
            this.thumbnail = view.findViewById(R.id.thumbnail);
            this.overflow = view.findViewById(R.id.overflow);
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
            SQLiteDatabase database = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
            Cursor cursor = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "video_name"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{"" + list_showItem.get(getAdapterPosition()).getId_exercise(), new LanguageReturn(mContext).getLanguage()}, null, null, null);
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String str = name;
            Exercise exercise = new Exercise("" + list_showItem.get(getAdapterPosition()).getId_exercise(), str, cursor.getString(1), cursor.getInt(2) + "");
            cursor.close();
            database.close();
            Frag_Show frag_show = new Frag_Show();
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, exercise);
            YourPlanActivity activity = (YourPlanActivity) mContext;
            frag_show.setArguments(bundle);
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_show, frag_show);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public Plan_exercise_selected_adapter(Context mContext2, List<Item_Show> albumList, int idPlanNow2) {
        mContext = mContext2;
        list_showItem = albumList;
        idPlanNow = idPlanNow2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_your_plan_item, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item_Show itemShow = list_showItem.get(position);
        holder.title.setText(itemShow.getName());
        holder.count.setText(itemShow.getNumber_set() + " " + mContext.getString(R.string.set_x) + " " + itemShow.getReps() + " " + mContext.getString(R.string.rep));
        Picasso.with(mContext).load("file:///android_asset/images/" + itemShow.getId_exercise() + "-min.png").into(holder.thumbnail);
    }

    private void showPopupMenu(View view, int pos) {
        PopupMenu popup = new PopupMenu(mContext, view);
        popup.getMenuInflater().inflate(R.menu.menu_editplan, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(pos));
        popup.show();
    }

    public int getItemCount() {
        return list_showItem.size();
    }
}
