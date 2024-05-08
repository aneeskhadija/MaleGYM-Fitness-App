package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;



import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.ArrayList;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.LanguageReturn;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.RecyclerItemClickListener;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.RecyclerItemClickListener.OnItemClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Frag_Show_List_1 extends Fragment {

    private Exercise_Adapter adapter;
    private List<Exercise> listExercise;
    private RecyclerView recyclerView;
    AdView mAdView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_show_list_exercise1, container, false);

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


        khoitaoViewRecyler(view);
        setDataChoRecyclerView();
        this.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this.recyclerView, new OnItemClickListener() {
            public void onItemClick(View view, int position) {

                Frag_Show frag_show = new Frag_Show();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.NAME_EXERCISE_SEND, listExercise.get(position));
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
        return view;
    }

    private void setDataChoRecyclerView() {

        int id_type = getArguments().getInt(MainActivity.CATEGORY_TYPE);
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_EXERCISES, new String[]{"nume", "text", "id_exercice", "video_name"}, "id_type LIKE ? AND lang LIKE ?", new String[]{id_type + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null, null);
        listExercise.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String des = cursor.getString(1);
            listExercise.add(new Exercise(cursor.getString(2), 0, name, des, cursor.getString(3), ""));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
        database.close();
    }

    private void khoitaoViewRecyler(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        listExercise = new ArrayList<>();
        adapter = new Exercise_Adapter(getActivity(), listExercise);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(getArguments().getString(MainActivity.NAME_EXERCISE_SEND));
    }
}
