package com.NewAppZone.home_workout.fitness_bodybuilding.Reminder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class Frag_Reminder extends Fragment {
    private ReminderAdapter adapter;
    private int idPlanCreate;
    private List<Reminder> listReminder;
    private ListView listview;
    AdView mAdView;

    @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // AudienceNetworkAds.initialize(getContext());
        View view = inflater.inflate(R.layout.frag__reminder, container, false);

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

        listview = view.findViewById(R.id.listReminder);
        getActivity().setTitle(getString(R.string.REMINDER));
        listReminder = new ArrayList<>();
        adapter = new ReminderAdapter(listReminder, getActivity());
        listview.setAdapter(adapter);
        PreparelistSelect();
        XyLyFloattingButton(view);
        return view;
    }

    private void PreparelistSelect() {
        boolean tf;
        Cursor cursor = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null).query(MainActivity.TABLE_REMINDER, null, null, null, null, null, null);
        listReminder.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String time = cursor.getString(1);
            String isRepet = cursor.getString(9);
            int hour = cursor.getInt(10);
            int minute = cursor.getInt(11);
            if (isRepet.equals("1")) {
                tf = true;
            } else {
                tf = false;
            }
            List<Boolean> list = new ArrayList<>();
            for (int i = 2; i < 9; i++) {
                if (cursor.getString(i).equals("1")) {
                    list.add(true);
                } else {
                    list.add(false);
                }
            }
            listReminder.add(new Reminder(id, time, list, tf, hour, minute));
        }
        cursor.close();
    }

    private void XyLyFloattingButton(View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        final List<Boolean> booleen = new ArrayList<>();
        booleen.add(false);
        booleen.add(false);
        booleen.add(false);
        booleen.add(false);
        booleen.add(false);
        booleen.add(false);
        booleen.add(false);
        fab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final SQLiteDatabase database = getActivity().openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
                Cursor cursor = database.query(MainActivity.TABLE_REMINDER, null, null, null, null, null, null, null);
                idPlanCreate = cursor.getCount();
                cursor.close();
                Calendar mcurrentTime = Calendar.getInstance();
                final TimePickerDialog mTimePicker = new TimePickerDialog(getActivity(), new OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, final int selectedHour, final int selectedMinute) {
                        String[] week_simple = getResources().getStringArray(R.array.week_simple);
                        new AlertDialog.Builder(getActivity()).setMultiChoiceItems(new CharSequence[]{week_simple[0], week_simple[1], week_simple[2], week_simple[3], week_simple[4], week_simple[5], week_simple[6]}, null, new OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                if (isChecked) {
                                    booleen.set(indexSelected, true);
                                    return;
                                }
                                booleen.set(indexSelected, false);
                                xuLyCancelAlarm((idPlanCreate * 10) + indexSelected);
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String h;
                                String m;
                                ContentValues contentValues = new ContentValues();
                                for (int i = 1; i < 8; i++) {
                                    if (booleen.get(i - 1)) {
                                        xuLyScheduleAlarm(((idPlanCreate * 10) + i) - 1, i, selectedHour, selectedMinute);
                                        contentValues.put("day" + i, "1");
                                    } else {
                                        contentValues.put("day" + i, "0");
                                    }
                                }
                                if (selectedHour < 10) {
                                    h = "0" + selectedHour;
                                } else {
                                    h = "" + selectedHour;
                                }
                                if (selectedMinute < 10) {
                                    m = "0" + selectedMinute;
                                } else {
                                    m = "" + selectedMinute;
                                }
                                contentValues.put("hour", selectedHour);
                                contentValues.put("minute", selectedMinute);
                                contentValues.put("Time", h + ":" + m);
                                contentValues.put("idReminder", idPlanCreate);
                                contentValues.put("isRepeatOn", "1");
                                Log.d("HEHE", database.insertOrThrow(MainActivity.TABLE_REMINDER, null, contentValues) + "");
                                listReminder.add(new Reminder(idPlanCreate, h + ":" + m, booleen, true, selectedHour, selectedMinute));
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                }, mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                mTimePicker.setButton(-1, "OK", mTimePicker);
                mTimePicker.setButton(-2, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == -2) {
                            mTimePicker.dismiss();
                        }
                    }
                });
                mTimePicker.show();
                PreparelistSelect();
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onResume() {
        super.onResume();
        PreparelistSelect();
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void xuLyCancelAlarm(int request_code) {
        try {
            ((AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE)).cancel(PendingIntent.getBroadcast(getActivity(), request_code, new Intent(getActivity(), AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
        } catch (Exception e) {
            Log.e("Loi Cancel", "AlarmManager update was not canceled. " + e.toString());
        }
    }

    private void xuLyScheduleAlarm(int Request_code, int dayofWeek, int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, dayofWeek);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), Request_code, new Intent(getActivity(), AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Activity activity = getActivity();
        getActivity();
        ((AlarmManager) activity.getSystemService(Context.ALARM_SERVICE))
                .setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 604800000, pendingIntent);
    }

    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(getString(R.string.app_name));
    }
}
