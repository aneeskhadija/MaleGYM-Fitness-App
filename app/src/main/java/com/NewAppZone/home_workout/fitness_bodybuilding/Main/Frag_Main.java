package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

import java.util.ArrayList;
import java.util.List;

public class Frag_Main extends Fragment {

    private MainPlanAdapter adapter;
    private List<Album> albumList;
    private String currentLanguage;
    private List<String> nameCategoryList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.frag_main, container, false);

       /* adView = new AdView(getContext(), "627859701435249_627859891435230", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();*/

        layTenCategory();
        khaiBaovaAnhxa(view);
        prepareAlbums();
        return view;
    }

    private void khaiBaovaAnhxa(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new MainPlanAdapter(getActivity(), albumList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void prepareAlbums() {
        String[] covers = {"abs", "back", "biceps", "calf", "chest", "forearms", "leg", "shoulder", "triceps"};
        for (int i = 0; i < 9; i++) {
            albumList.add(new Album(nameCategoryList.get(i), 0, covers[i]));
        }
        adapter.notifyDataSetChanged();
    }

    private void layTenCategory() {
        nameCategoryList = new ArrayList<>();
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        for (int i = 1; i < 10; i++) {
            Cursor cursor = database.query(MainActivity.TABLE_CATEGORY, new String[]{"nume"}, "id_type LIKE ? AND lang LIKE ?", new String[]{i + "", new LanguageReturn(getActivity()).getLanguage()}, null, null, null);
            cursor.moveToFirst();
            nameCategoryList.add(cursor.getString(0));
            cursor.close();
        }
        database.close();
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.title_1));
    }
}
