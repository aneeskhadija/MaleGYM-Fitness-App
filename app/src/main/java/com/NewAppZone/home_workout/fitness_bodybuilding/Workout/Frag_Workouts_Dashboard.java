package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.ArrayList;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

public class Frag_Workouts_Dashboard extends Fragment {

    private List<String> list;
    private List<String> listNumber;
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.frag__workouts__dashboard, container, false);

        /*adView = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        docDuLieu();
        xulyshowPlan(view);
        return view;
    }

    private void docDuLieu() {
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_CATEGORY_GUIDE, new String[]{"categ_name"}, "lang LIKE ?", new String[]{new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        Cursor cursor2 = database.query(MainActivity.TABLE_DAYS, new String[]{"title"}, "lang LIKE ?", new String[]{new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
        listNumber = new ArrayList<>();
        while (cursor2.moveToNext()) {
            listNumber.add(cursor2.getString(0));
        }
        cursor2.close();
        database.close();
    }

    private void xulyshowPlan(View view) {
        final List<ItemWorkout> listItemWorkout = new ArrayList<>();
        ItemWorkout_Adapter item_workoutAdapter = new ItemWorkout_Adapter(listItemWorkout, getActivity());
        ListView listView = view.findViewById(R.id.list_view);
        listItemWorkout.add(new ItemWorkout(list.get(0) + " : " + listNumber.get(0),
                "", R.drawable.border_blue, 1, 1));
        listItemWorkout.add(new ItemWorkout(list.get(0) + " : " + listNumber.get(1),
                "", R.drawable.border_blue, 1, 2));
        listItemWorkout.add(new ItemWorkout(list.get(0) + " : " + listNumber.get(2),
                "", R.drawable.border_blue, 1, 3));
        listItemWorkout.add(new ItemWorkout(list.get(1) + " : " + listNumber.get(0),
                "", R.drawable.border_purpose, 2, 1));
        listItemWorkout.add(new ItemWorkout(list.get(1) + " : " + listNumber.get(1),
                "", R.drawable.border_purpose, 2, 2));
        listItemWorkout.add(new ItemWorkout(list.get(1) + " : " + listNumber.get(2),
                "", R.drawable.border_purpose, 2, 3));
        listItemWorkout.add(new ItemWorkout(list.get(2) + " : " + listNumber.get(0),
                "", R.drawable.border_red, 3, 1));
        listItemWorkout.add(new ItemWorkout(list.get(2) + " : " + listNumber.get(1),
                "", R.drawable.border_red, 3, 2));
        listItemWorkout.add(new ItemWorkout(list.get(2) + " : " + listNumber.get(2),
                "", R.drawable.border_red, 3, 3));

        listView.setAdapter(item_workoutAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                starDetailActivity(listItemWorkout.get(position).getId_cate(),
                        listItemWorkout.get(position).getId_dayofWeek(),
                        listItemWorkout.get(position).getNameWorkout());
            }
        });
    }

    private void starDetailActivity(int i, int day, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.ID_EXERCISE_SEND, i);
        bundle.putInt(MainActivity.DES_EXERCISE_SEND, day);
        bundle.putString(MainActivity.NAME_EXERCISE_SEND, name);
        Fragment_Detail fragment = new Fragment_Detail();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.frag_main, fragment);
        transaction.commit();
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.title_2));
    }
}
