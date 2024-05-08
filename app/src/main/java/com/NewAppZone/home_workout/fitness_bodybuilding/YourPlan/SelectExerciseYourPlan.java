package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;

import java.util.ArrayList;
import java.util.List;

import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.caiDatConfirgution;

public class SelectExerciseYourPlan extends AppCompatActivity {

    private int day_Xuly;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int plan_dang_Xuly;

    AdView mAdView;

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        ThemHoacXoaBaiTapAdapter adapter;
        List<ExerciseWorkout> albumList;
        int currentDay;
        SQLiteDatabase database;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt("section_number", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
            //AudienceNetworkAds.initialize(getContext());
            View view = inflater.inflate(R.layout.frag_select_exercise_your_plan, container, false);

            /**/

            /*AdView adView1 = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
            // Find the Ad Container
            LinearLayout adContainer1 = (LinearLayout) view.findViewById(R.id.banner_container1);
            // Add the ad view to your activity layout
            adContainer1.addView(adView1);
            // Request an ad
            adView1.loadAd();*/

            albumList = new ArrayList<>();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainActivity.SHAREPRE, 0);
            int plandangXuly = sharedPreferences.getInt("CurrentPlan", 0);
            currentDay = sharedPreferences.getInt("Current_Day", 0);
            adapter = new ThemHoacXoaBaiTapAdapter(getContext(), albumList, plandangXuly, currentDay);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerDisPlayExercises);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            prepareLIST();
            return view;
        }

        private void prepareLIST() {
            int sl;
            String workoutselect = getArguments().getInt("section_number") + "";
            Cursor cursor = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "id_exercice", "custom", "video_name"}, "id_type LIKE ? AND lang LIKE ?", new String[]{"" + workoutselect, new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            albumList.clear();
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String text = cursor.getString(1);
                String id_exercice = cursor.getString(2);
                String selected = cursor.getString(3);
                int code = cursor.getInt(4);
                if (selected.equals("0")) {
                    sl = R.drawable.btn_add;
                } else {
                    sl = R.drawable.btn_delete;
                }
                Cursor cursor1 = database.query(MainActivity.TABLE_FOTO, new String[]{"name", "number"}, "id_exercice LIKE ?", new String[]{id_exercice}, null, null, null);
                cursor1.moveToFirst();
                String path = cursor1.getString(0);
                int i = cursor1.getInt(1);
                cursor1.close();
                albumList.add(new ExerciseWorkout(Integer.parseInt(id_exercice), name, text, path, sl, code));
            }
            cursor.close();
            adapter.notifyDataSetChanged();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        public int getCount() {
            return 9;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.Abs);
                case 1:
                    return getString(R.string.back);
                case 2:
                    return getString(R.string.Biceps);
                case 3:
                    return getString(R.string.Calf);
                case 4:
                    return getString(R.string.Chest);
                case 5:
                    return getString(R.string.Forearms);
                case 6:
                    return getString(R.string.Legs);
                case 7:
                    return getString(R.string.shoulders);
                case 8:
                    return getString(R.string.Triceps);
                default:
                    return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_create_your_plan);
        new caiDatConfirgution(getApplicationContext()).okSetting();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*adView = new AdView(this, "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        plan_dang_Xuly = getIntent().getIntExtra(MainActivity.NAME_EXERCISE_SEND, 0);
        day_Xuly = getIntent().getIntExtra(MainActivity.ID_EXERCISE_SEND, 1);
        setTitle(getIntent().getStringExtra(MainActivity.DES_EXERCISE_SEND));
        Editor editor = getSharedPreferences(MainActivity.SHAREPRE, 0).edit();
        editor.putInt("CurrentPlan", plan_dang_Xuly);
        editor.putInt("Current_Day", day_Xuly);
        editor.apply();
        Toolbar toolbar = findViewById(R.id.toolbar);
        ((AppBarLayout.LayoutParams) toolbar.getLayoutParams()).setScrollFlags(0);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(mViewPager);
    }
}
