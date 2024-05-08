package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Frag_tao_planMoi_1 extends Fragment {

    private PlanAdapter mSongAdapter;
    private List<Plan> mSongs;
    private RecyclerView rvSongs;
    private int soPlanDaTao;



    private class InputFilterMinMax implements InputFilter {
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

    AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_frag_tao_plan_moi, container, false);

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

        rvSongs = (RecyclerView) view.findViewById(R.id.recycler_view_your_plan);
        mSongs = new ArrayList<>();
        prepareYourPlanList();
        getActivity().setTitle(getString(R.string.title_3));
        mSongAdapter = new PlanAdapter(getActivity(), mSongs);
        rvSongs.setAdapter(mSongAdapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ((FloatingActionButton) view.findViewById(R.id.fab)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });
        return view;
    }

    private void prepareYourPlanList() {
        SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        Cursor cursor = database.query(MainActivity.TABLE_CUSTOM_WORKOUT, new String[]{"IDPlan", "NamePlan", "Resttime", "Datecreate"}, null, null, null, null, null);
        mSongs.clear();
        while (cursor.moveToNext()) {
            mSongs.add(new Plan(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), ""));
        }
        cursor.close();
        database.close();
    }

    private void showChangeLangDialog() {
        Builder dialogBuilder = new Builder(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.createplandialog, null);
        dialogBuilder.setView(dialogView);
        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        final EditText etime = (EditText) dialogView.findViewById(R.id.edit2);
        etime.setFilters(new InputFilter[]{new InputFilterMinMax("1", "7")});
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        final AlertDialog b = dialogBuilder.create();
        b.show();
        b.getButton(-1).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Boolean valueOf = false;
                if (edt.getText() == null) {
                    return;
                }
                if (edt.getText().toString().length() <= 0) {
                    Toast.makeText(getActivity(), "Fill plan name", Toast.LENGTH_SHORT).show();
                } else if (etime.getText() == null) {
                } else {
                    if (etime.getText().toString().length() > 0) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainActivity.SHAREPRE, 0);
                        soPlanDaTao = sharedPreferences.getInt("soPlanDaTao", 0);
                        soPlanDaTao = soPlanDaTao + 1;
                        Editor editor = sharedPreferences.edit();
                        editor.putInt("soPlanDaTao", soPlanDaTao);
                        editor.apply();
                        SQLiteDatabase database3 = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
                        String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("IDPlan", soPlanDaTao);
                        contentValues.put("NamePlan", edt.getText().toString());
                        contentValues.put("Resttime", etime.getText().toString());
                        contentValues.put("Datecreate", date);
                        database3.insert(MainActivity.TABLE_CUSTOM_WORKOUT, null, contentValues);
                        database3.close();
                        Bundle bundle = new Bundle();
                        bundle.putInt(MainActivity.NAME_EXERCISE_SEND, soPlanDaTao);
                        bundle.putInt(MainActivity.ID_EXERCISE_SEND, Integer.parseInt(etime.getText().toString()));
                        bundle.putString(MainActivity.DES_EXERCISE_SEND, edt.getText().toString());
                        Fragment fragment = new Frag_show_your_create_2();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                        fragment.setArguments(bundle);
                        transaction.replace(R.id.frag_show, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        b.dismiss();
                        return;
                    }
                    Toast.makeText(getActivity(), "Fill break-time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareYourPlanList();
        mSongAdapter.notifyDataSetChanged();
    }
}
