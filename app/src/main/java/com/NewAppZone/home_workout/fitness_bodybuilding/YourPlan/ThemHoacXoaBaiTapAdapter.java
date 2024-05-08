package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Exercise;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class ThemHoacXoaBaiTapAdapter extends RecyclerView.Adapter<ThemHoacXoaBaiTapAdapter.ExViewHolder> {

    private int currentDay;
    private SQLiteDatabase database;
    private ExerciseWorkout e;
    private ExerciseWorkout exercise;
    private List<ExerciseWorkout> exerciseList;
    private Context mContext;
    private int planDangXuLy;
    private int soPlanDaTao;
    int txtAddtobtnChange;

    public class ExViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private ImageView imgICON;
        private TextView tvnameEx;
        private ImageView txtAddtoPlan;

        public ExViewHolder(View itemView) {
            super(itemView);
            this.tvnameEx = itemView.findViewById(R.id.tv_nameExercise);
            this.imgICON = itemView.findViewById(R.id.img_iconExercise);
            this.txtAddtoPlan = itemView.findViewById(R.id.txtaddtobttn);
            itemView.setOnClickListener(this);
            this.txtAddtoPlan.setOnClickListener(this);
            this.txtAddtoPlan.setClickable(true);
            this.txtAddtoPlan.setFocusable(true);
        }

        public void onClick(View v) {
            if (v.getId() == txtAddtoPlan.getId()) {
                updateList(exerciseList, getAdapterPosition());
                return;
            }
            ExerciseWorkout exercise_workout = exerciseList.get(getAdapterPosition());
            Exercise exercise = new Exercise(exercise_workout.getIdExercise() + "", exercise_workout.getNameExercise(), exercise_workout.getDesExercise(), exercise_workout.getNumberImage() + "");
            Intent intent = new Intent(mContext, YourPlanActivity.class);
            intent.putExtra(MainActivity.ISHOW, true);
            intent.putExtra(MainActivity.NAME_EXERCISE_SEND, exercise);
            mContext.startActivity(intent);
        }
    }

    class InputFilterMinMax implements InputFilter {
        private int max;
        private int min;

        public InputFilterMinMax(int min2, int max2) {
            min = min2;
            max = max2;
        }

        public InputFilterMinMax(String min2, String max2) {
            min = Integer.parseInt(min2);
            max = Integer.parseInt(max2);
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                if (isInRange(min, max, Integer.parseInt(dest.toString() + source.toString()))) {
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

    public void updateList(List<ExerciseWorkout> data2, int pos2) {
        final List<ExerciseWorkout> data = data2;
        final int pos = pos2;
        e = data.get(pos);
        soPlanDaTao = planDangXuLy;
        database = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        txtAddtobtnChange = e.getNumberSound();
        if (txtAddtobtnChange == R.drawable.btn_add) {
            Builder builder = new Builder(mContext);
            View dialogView = LayoutInflater.from(mContext).inflate(R.layout.addexercisedialog, null);
            builder.setView(dialogView);
            final EditText edt = dialogView.findViewById(R.id.edit1);
            final EditText etime = dialogView.findViewById(R.id.edit2);
            edt.setFilters(new InputFilter[]{new InputFilterMinMax("1", "99")});
            etime.setFilters(new InputFilter[]{new InputFilterMinMax("1", "200")});
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton(R.string.cancel, null);
            final AlertDialog b = builder.create();
            b.show();
            b.getButton(-1).setOnClickListener(new OnClickListener() {
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
                            txtAddtobtnChange = R.drawable.btn_delete;
                            ContentValues row = new ContentValues();
                            row.put("custom", "1");
                            ContentValues contentValues = row;
                            database.update(MainActivity.TABLE_EXERCISES, contentValues, "id_exercice LIKE ?", new String[]{"" + e.getIdExercise()});
                            ContentValues contentValues2 = new ContentValues();
                            Cursor cursor = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{"" + e.getIdExercise(), new LanguageReturn(mContext).getLanguage()}, null, null, null);
                            while (cursor.moveToNext()) {
                                String SSname = cursor.getString(0);
                                String SSdes = cursor.getString(1);
                                contentValues2.put("name", SSname);
                                contentValues2.put("des", SSdes);
                            }
                            cursor.close();
                            Cursor cursor1 = database.query(MainActivity.TABLE_FOTO, new String[]{"name"}, "id_exercice LIKE ?", new String[]{"" + e.getIdExercise()}, null, null, null);
                            cursor1.moveToFirst();
                            String path = cursor1.getString(0);
                            cursor1.close();
                            contentValues2.put("id_workouts", soPlanDaTao);
                            contentValues2.put("id_exercices", e.getIdExercise());
                            contentValues2.put("path", path);
                            contentValues2.put("number_set", edt.getText().toString());
                            contentValues2.put("reps", etime.getText().toString());
                            contentValues2.put("day", currentDay);
                            contentValues2.put("id", (soPlanDaTao * 10000) + (currentDay * 1000) + e.getIdExercise());
                            long insert = database.insert(MainActivity.TABLE_CUSTOM_EXERCISE, null, contentValues2);
                            data.set(pos, new ExerciseWorkout(e.getIdExercise(), e.getNameExercise(), e.getDesExercise(), e.getPathExercise(), txtAddtobtnChange, e.getNumberImage()));
                            exerciseList = data;
                            notifyDataSetChanged();
                            b.dismiss();
                            return;
                        }
                        Toast.makeText(mContext, R.string.erorr, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return;
        }
        ContentValues row = new ContentValues();
        row.put("custom", "0");
        ContentValues contentValues = row;
        long update = (long) database.update(MainActivity.TABLE_EXERCISES, contentValues, "id_exercice LIKE ?", new String[]{e.getIdExercise() + ""});
        database.delete(MainActivity.TABLE_CUSTOM_EXERCISE, "id LIKE ?", new String[]{String.valueOf((soPlanDaTao * 10000) + (currentDay * 1000) + e.getIdExercise())});
        txtAddtobtnChange = R.drawable.btn_add;
        data.set(pos, new ExerciseWorkout(e.getIdExercise(), e.getNameExercise(), e.getDesExercise(), e.getPathExercise(), txtAddtobtnChange, e.getNumberImage()));
        exerciseList = data;
        notifyDataSetChanged();
    }

    public ThemHoacXoaBaiTapAdapter(Context mContext2, List<ExerciseWorkout> exerciseList2, int planDangXuLy2, int current_Day) {
        mContext = mContext2;
        exerciseList = exerciseList2;
        planDangXuLy = planDangXuLy2;
        currentDay = current_Day;
    }

    public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_createyourplan_card, parent, false));
    }

    public void onBindViewHolder(ExViewHolder holder, int position) {
        exercise = exerciseList.get(position);
        holder.tvnameEx.setText(exercise.getNameExercise());
        holder.txtAddtoPlan.setImageResource(exercise.getNumberSound());
        Picasso.with(mContext).load("file:///android_asset/images/" + exercise.getIdExercise() + "-min.png").into(holder.imgICON);
    }

    public int getItemCount() {
        return exerciseList.size();
    }
}
