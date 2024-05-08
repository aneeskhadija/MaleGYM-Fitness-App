package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.ArrayList;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Exercise;
import com.NewAppZone.home_workout.fitness_bodybuilding.Coach.Frag_Show;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.CustomGridLayoutManager;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.RecyclerItemClickListener;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.RecyclerItemClickListener.OnItemClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Fragment_Detail extends Fragment {

    private int id_category;
    private int id_dayOfWeek;
    private String nameCate;
    private String reps;

    AdView mAdView, mAdView2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_detail2, container, false);

        /*adView = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*adView1 = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer1 = (LinearLayout) view.findViewById(R.id.banner_container1);
        // Add the ad view to your activity layout
        adContainer1.addView(adView1);
        // Request an ad
        adView1.loadAd();*/

        mAdView2 = view.findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        /*adView2 = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer2 = (LinearLayout) view.findViewById(R.id.banner_container2);
        // Add the ad view to your activity layout
        adContainer2.addView(adView2);
        // Request an ad
        adView2.loadAd();*/

        getDataTuActivityTruoc();
        if (id_category == 1) {
            reps = getString(R.string.s1);
        } else if (id_category == 2) {
            reps = getString(R.string.s2);
        } else {
            reps = getString(R.string.s3);
        }
        showDay1(view);
        showDay2(view);
        if (id_dayOfWeek > 1) {
            showDay3(view);
            view.findViewById(R.id.linear3).setVisibility(View.VISIBLE);
        }
        if (id_dayOfWeek > 2) {
            showDay4(view);
            view.findViewById(R.id.linear4).setVisibility(View.VISIBLE);
        }
        setTitleDay(view);
        return view;
    }

    private void setTitleDay(View view) {
        TextView txt_2 = view.findViewById(R.id.txtDay2);
        TextView txt_3 = view.findViewById(R.id.txtDay3);
        TextView txt_4 = view.findViewById(R.id.txtDay4);
        ((TextView) view.findViewById(R.id.txtDay1)).setText(getString(R.string.day) + " 1");
        txt_2.setText(getString(R.string.day) + " 2");
        txt_3.setText(getString(R.string.day) + " 3");
        txt_4.setText(getString(R.string.day) + " 4");
    }

    private void getDataTuActivityTruoc() {
        Bundle bundle = getArguments();
        id_category = bundle.getInt(MainActivity.ID_EXERCISE_SEND, 1);
        id_dayOfWeek = bundle.getInt(MainActivity.DES_EXERCISE_SEND, 1);
        nameCate = bundle.getString(MainActivity.NAME_EXERCISE_SEND);
        getActivity().setTitle(nameCate);
    }

    private void showDay4(View view) {
        RecyclerView r1 = view.findViewById(R.id.recycler_view_your_plan_4);
        CustomGridLayoutManager customGridLayoutManager = new CustomGridLayoutManager(getActivity());
        customGridLayoutManager.setScrollEnabled(false);
        r1.setLayoutManager(customGridLayoutManager);
      //  r1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        r1.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<PlanWorkout> arrayList2 = new ArrayList<>();
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_GUIDE, new String[]{"id_exercices"}, "id_categ_guide LIKE ? AND id_num_days LIKE ? AND day LIKE ?", new String[]{id_category + "", id_dayOfWeek + "", "4"}, null, null, null);
        arrayList.clear();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        cursor.close();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor2 = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "video_name"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{arrayList.get(i) + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            while (cursor2.moveToNext()) {
                String name = cursor2.getString(0);
                String des = cursor2.getString(1);
                int code = cursor2.getInt(2);
                Cursor cursor3 = database.query(MainActivity.TABLE_FOTO, new String[]{"name", "number"}, "id_exercice LIKE ?", new String[]{arrayList.get(i) + ""}, null, null, null, null);
                cursor3.moveToFirst();
                String path = cursor3.getString(0);
                int i2 = cursor3.getInt(1);
                cursor3.close();
                arrayList2.add(new PlanWorkout(arrayList.get(i), name, reps, path, des, code));
            }
            cursor2.close();
        }
        r1.setAdapter(new WorkoutPlan_Adapter(getActivity(), arrayList2));
        final ArrayList<PlanWorkout> arrayList4 = arrayList2;
        r1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), r1, new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                PlanWorkout item = arrayList4.get(position);
                Exercise exercise = new Exercise(item.getIdPlan() + "", item.getName(), item.getDescription(), item.getNumberImg() + "");
                Frag_Show frag_show = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, exercise);
                frag_show.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_main, frag_show);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            public void onItemLongClick(View view, int position) {
            }
        }));
    }

    private void showDay3(View view) {
        RecyclerView r1 = view.findViewById(R.id.recycler_view_your_plan_3);
        CustomGridLayoutManager customGridLayoutManager = new CustomGridLayoutManager(getActivity());
        customGridLayoutManager.setScrollEnabled(false);
        r1.setLayoutManager(customGridLayoutManager);
        r1.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<PlanWorkout> arrayList2 = new ArrayList<>();
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_GUIDE, new String[]{"id_exercices"}, "id_categ_guide LIKE ? AND id_num_days LIKE ? AND day LIKE ?", new String[]{id_category + "", id_dayOfWeek + "", "3"}, null, null, null);
        arrayList.clear();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        cursor.close();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor2 = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "video_name"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{arrayList.get(i) + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            while (cursor2.moveToNext()) {
                String name = cursor2.getString(0);
                String des = cursor2.getString(1);
                int code = cursor2.getInt(2);
                Cursor cursor3 = database.query(MainActivity.TABLE_FOTO, new String[]{"name", "number"}, "id_exercice LIKE ?", new String[]{arrayList.get(i) + ""}, null, null, null, null);
                cursor3.moveToFirst();
                String path = cursor3.getString(0);
                int i2 = cursor3.getInt(1);
                cursor3.close();
                arrayList2.add(new PlanWorkout(arrayList.get(i), name, reps, path, des, code));
            }
            cursor2.close();
        }
        r1.setAdapter(new WorkoutPlan_Adapter(getActivity(), arrayList2));
        final ArrayList<PlanWorkout> arrayList4 = arrayList2;
        r1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), r1, new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                PlanWorkout item = arrayList4.get(position);
                Exercise exercise = new Exercise(item.getIdPlan() + "", item.getName(), item.getDescription(), item.getNumberImg() + "");
                Frag_Show frag_show = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, exercise);
                frag_show.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_main, frag_show);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            public void onItemLongClick(View view, int position) {
            }
        }));
    }

    private void showDay2(View view) {
        RecyclerView r1 = view.findViewById(R.id.recycler_view_your_plan_2);
        CustomGridLayoutManager customGridLayoutManager = new CustomGridLayoutManager(getActivity());
        customGridLayoutManager.setScrollEnabled(false);
        r1.setLayoutManager(customGridLayoutManager);
        r1.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<PlanWorkout> arrayList2 = new ArrayList<PlanWorkout>();
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_GUIDE, new String[]{"id_exercices"}, "id_categ_guide LIKE ? AND id_num_days LIKE ? AND day LIKE ?", new String[]{id_category + "", id_dayOfWeek + "", "2"}, null, null, null);
        arrayList.clear();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        cursor.close();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor2 = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "video_name"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{arrayList.get(i) + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            while (cursor2.moveToNext()) {
                String name = cursor2.getString(0);
                String des = cursor2.getString(1);
                int code = cursor2.getInt(2);
                Cursor cursor3 = database.query(MainActivity.TABLE_FOTO, new String[]{"name", "number"}, "id_exercice LIKE ?", new String[]{arrayList.get(i) + ""}, null, null, null, null);
                cursor3.moveToFirst();
                String path = cursor3.getString(0);
                int i2 = cursor3.getInt(1);
                cursor3.close();
                arrayList2.add(new PlanWorkout(arrayList.get(i), name, reps, path, des, code));
            }
            cursor2.close();
        }
        r1.setAdapter(new WorkoutPlan_Adapter(getActivity(), arrayList2));
        final ArrayList<PlanWorkout> arrayList4 = arrayList2;
        r1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), r1, new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                PlanWorkout item = arrayList4.get(position);
                Exercise exercise = new Exercise(item.getIdPlan() + "", item.getName(), item.getDescription(), item.getNumberImg() + "");
                Frag_Show frag_show = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, exercise);
                frag_show.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_main, frag_show);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            public void onItemLongClick(View view, int position) {
            }
        }));
    }

    private void showDay1(View view) {
        RecyclerView r1 = view.findViewById(R.id.recycler_view_your_plan_1);
        CustomGridLayoutManager customGridLayoutManager = new CustomGridLayoutManager(getActivity());
        customGridLayoutManager.setScrollEnabled(false);
        r1.setLayoutManager(customGridLayoutManager);
        r1.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<PlanWorkout> arrayList2 = new ArrayList<>();
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_GUIDE, new String[]{"id_exercices"}, "id_categ_guide LIKE ? AND id_num_days LIKE ? AND day LIKE ?", new String[]{id_category + "", id_dayOfWeek + "", "1"}, null, null, null);
        arrayList.clear();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getInt(0));
        }
        cursor.close();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor2 = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "video_name"}, "id_exercice LIKE ? AND lang LIKE ?", new String[]{arrayList.get(i) + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            while (cursor2.moveToNext()) {
                String name = cursor2.getString(0);
                String des = cursor2.getString(1);
                int code = cursor2.getInt(2);
                Cursor cursor3 = database.query(MainActivity.TABLE_FOTO, new String[]{"name", "number"}, "id_exercice LIKE ?", new String[]{arrayList.get(i) + ""}, null, null, null, null);
                cursor3.moveToFirst();
                String path = cursor3.getString(0);
                int i2 = cursor3.getInt(1);
                cursor3.close();
                arrayList2.add(new PlanWorkout(arrayList.get(i), name, reps, path, des, code));
            }
            cursor2.close();
        }
        r1.setAdapter(new WorkoutPlan_Adapter(getActivity(), arrayList2));
        final ArrayList<PlanWorkout> arrayList4 = arrayList2;
        r1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), r1, new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                PlanWorkout item = arrayList4.get(position);
                Exercise exercise = new Exercise(item.getIdPlan() + "", item.getName(), item.getDescription(), item.getNumberImg() + "");
                Frag_Show frag_show = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, exercise);
                frag_show.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_main, frag_show);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            public void onItemLongClick(View view, int position) {
            }
        }));
    }
}
