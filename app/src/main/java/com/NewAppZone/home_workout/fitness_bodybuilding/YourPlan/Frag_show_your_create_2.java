package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;

import java.util.ArrayList;
import java.util.List;

import com.NewAppZone.home_workout.fitness_bodybuilding.Main.CustomGridLayoutManager;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Frag_show_your_create_2 extends Fragment {

    private Plan_exercise_selected_adapter adapter;
    private SQLiteDatabase database;
    private int day_XuLY;
    private List<Item_Show> planList;
    private int plan_Dang_Xu_Ly;
    private RecyclerView rvSongs;
    AdView mAdView, mAdView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //    AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.frag_show_your_create, container, false);

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*adView = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        mAdView4 = view.findViewById(R.id.adView4);
        AdRequest adRequest4 = new AdRequest.Builder().build();
        mAdView4.loadAd(adRequest4);


        Bundle bundle = getArguments();
        plan_Dang_Xu_Ly = bundle.getInt(MainActivity.NAME_EXERCISE_SEND, -1);
        day_XuLY = bundle.getInt(MainActivity.ID_EXERCISE_SEND, -1);
        getActivity().setTitle(getString(R.string.work_title) + " " + bundle.getString(MainActivity.DES_EXERCISE_SEND));
        xulyShow_Day(view, day_XuLY);
        txtAddProcess(view);
        txtDisPlayDayProcess(view);
        return view;
    }

    private void txtDisPlayDayProcess(View view) {
        TextView txt2 = view.findViewById(R.id.txt_day2);
        TextView txt3 = view.findViewById(R.id.txt_day3);
        TextView txt4 = view.findViewById(R.id.txt_day4);
        TextView txt5 = view.findViewById(R.id.txt_day5);
        TextView txt6 = view.findViewById(R.id.txt_day6);
        TextView txt7 = view.findViewById(R.id.txt_day7);
        ((TextView) view.findViewById(R.id.txt_day1)).setText(getString(R.string.day) + " 1");
        txt2.setText(getString(R.string.day) + " 2");
        txt3.setText(getString(R.string.day) + " 3");
        txt4.setText(getString(R.string.day) + " 4");
        txt5.setText(getString(R.string.day) + " 5");
        txt6.setText(getString(R.string.day) + " 6");
        txt7.setText(getString(R.string.day) + " 7");
    }

    private void xulyShow_Day(View view, int day) {
        switch (day) {
            case 1:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                return;
            case 2:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                return;
            case 3:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_3, 3);
                return;
            case 4:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_3, 3);
                view.findViewById(R.id.linear4).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_4, 4);
                return;
            case 5:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_3, 3);
                view.findViewById(R.id.linear4).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_4, 4);
                view.findViewById(R.id.linear5).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_5, 5);
                return;
            case 6:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_3, 3);
                view.findViewById(R.id.linear4).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_4, 4);
                view.findViewById(R.id.linear5).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_5, 5);
                view.findViewById(R.id.linear6).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_6, 6);
                return;
            case 7:
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_1, 1);
                view.findViewById(R.id.linear2).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_2, 2);
                view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_3, 3);
                view.findViewById(R.id.linear4).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_4, 4);
                view.findViewById(R.id.linear5).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_5, 5);
                view.findViewById(R.id.linear6).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_6, 6);
                view.findViewById(R.id.linear7).setVisibility(View.VISIBLE);
                ShowDay1(view, plan_Dang_Xu_Ly, R.id.recycler_day_7, 7);
                return;
            default:
                return;
        }
    }

    private void txtAddProcess(View view) {
        final SQLiteDatabase database2 = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        view.findViewById(R.id.txt_add_1).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "1"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 1);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 1");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_2).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "2"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 2);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 2");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_3).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "3"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 3);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 3");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_4).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "4"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 4");
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 4);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_5).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "5"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 5");
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 5);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_6).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "6"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 6);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 6");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        view.findViewById(R.id.txt_add_7).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContentValues cV = new ContentValues();
                cV.put("custom", "0");
                database2.update(MainActivity.TABLE_EXERCISES, cV, null, null);
                List<String> list = new ArrayList<>();
                Cursor cursor = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices"}, " id_workouts LIKE ? and day LIKE ?", new String[]{String.valueOf(plan_Dang_Xu_Ly), "7"}, null, null, null);
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
                cursor.close();
                for (int i = 0; i < list.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put("custom", "1");
                    database2.update(MainActivity.TABLE_EXERCISES, cv, "id_exercice LIKE ?", new String[]{list.get(i)});
                }
                Intent intent = new Intent(getActivity(), SelectExerciseYourPlan.class);
                intent.putExtra(MainActivity.NAME_EXERCISE_SEND, plan_Dang_Xu_Ly);
                intent.putExtra(MainActivity.ID_EXERCISE_SEND, 7);
                intent.putExtra(MainActivity.DES_EXERCISE_SEND, getString(R.string.day) + " 7");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
    }

    private void ShowDay1(View view, int idPlan, int id_recycler, int day) {
        rvSongs = view.findViewById(id_recycler);
        planList = new ArrayList<>();
        prepareYourPlanList(day);
        adapter = new Plan_exercise_selected_adapter(getActivity(), planList, idPlan);
        rvSongs.setAdapter(adapter);
        CustomGridLayoutManager mLayoutManager = new CustomGridLayoutManager(getActivity());
        mLayoutManager.setScrollEnabled(false);
        rvSongs.setLayoutManager(mLayoutManager);
    }

    private void prepareYourPlanList(int day_data) {
        int z = getArguments().getInt(MainActivity.NAME_EXERCISE_SEND, -1);
        database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_CUSTOM_EXERCISE, new String[]{"id_exercices", "name", "path", "reps", "des", "number_set", "id"}, "id_workouts LIKE ? AND day LIKE ?", new String[]{z + "", day_data + ""}, null, null, null);
        planList.clear();
        while (cursor.moveToNext()) {
            int id_exercise = cursor.getInt(0);
            String name = cursor.getString(1);
            int rest = cursor.getInt(3);
            String path = cursor.getString(2);
            String des = cursor.getString(4);
            int number_set = cursor.getInt(5);
            planList.add(new Item_Show(cursor.getInt(6), id_exercise, name, des, path, number_set, rest, R.string.day));
        }
        cursor.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        xulyShow_Day(getView(), day_XuLY);
        adapter.notifyDataSetChanged();
    }
}
