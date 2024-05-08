package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.NewAppZone.home_workout.fitness_bodybuilding.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class SplashScreen extends Activity {
   // private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processCopy();
        processCopy2();
        if (getSharedPreferences(MainActivity.SHAREPRE, 0).getBoolean("isF", true)) {
            copyFirst();
        }
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash);
        if (getSharedPreferences(MainActivity.SHAREPRE, 0).getString("CHECK_FIRST_TIME", "0").equals("0")) {
            checkFirsttime();
        }


        /*mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(BuildConfig.GOOGLE_INTERSTITIAL);
        requestNewInterstitial();*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new caiDatConfirgution(getApplicationContext()).okSetting();
                /*if (mInterstitialAd == null || !mInterstitialAd.isLoaded()) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                } else {
                    mInterstitialAd.show();
                }*/
                /*mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    }
                });*/
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    private void copyFirst() {
        SQLiteDatabase database1 = openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
        SQLiteDatabase database2 = openOrCreateDatabase(MainActivity.DATABASE_NAME2, 0, null);
        Cursor cursor = database2.query(MainActivity.TABLE_REMINDER, new String[]{"idReminder", "Time", "day1", "day2", "day3", "day4", "day5", "day6", "day7", "isRepeatOn", "hour", "minute"}, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("idReminder", cursor.getInt(0));
                contentValues.put("Time", cursor.getString(1));
                contentValues.put("day1", cursor.getInt(2));
                contentValues.put("day2", cursor.getInt(3));
                contentValues.put("day3", cursor.getInt(4));
                contentValues.put("day4", cursor.getInt(5));
                contentValues.put("day5", cursor.getInt(6));
                contentValues.put("day6", cursor.getInt(7));
                contentValues.put("day7", cursor.getInt(8));
                contentValues.put("isRepeatOn", cursor.getInt(9));
                contentValues.put("hour", cursor.getInt(10));
                contentValues.put("minute", cursor.getInt(11));
                database1.insert(MainActivity.TABLE_REMINDER, null, contentValues);
            }
        }
        cursor.close();
        String[] table_custom_ex = {"id", "day", "id_exercices", "id_workouts", "name", "path", "des", "number_set", "reps"};
        Cursor cursor2 = database2.query(MainActivity.TABLE_CUSTOM_EXERCISE, table_custom_ex, null, null, null, null, null);
        if (cursor2.getCount() > 0) {
            while (cursor2.moveToNext()) {
                ContentValues contentValues2 = new ContentValues();
                for (int i = 0; i < table_custom_ex.length; i++) {
                    contentValues2.put(table_custom_ex[i], cursor2.getString(i));
                }
                database1.insert(MainActivity.TABLE_CUSTOM_EXERCISE, null, contentValues2);
            }
        }
        cursor2.close();
        String[] table_wo = {"IDPlan", "NamePlan", "Resttime", "Datecreate"};
        Cursor cursor3 = database2.query(MainActivity.TABLE_CUSTOM_WORKOUT, table_wo, null, null, null, null, null);
        if (cursor3.getCount() > 0) {
            while (cursor3.moveToNext()) {
                ContentValues contentValues3 = new ContentValues();
                for (int i2 = 0; i2 < table_wo.length; i2++) {
                    contentValues3.put(table_wo[i2], cursor3.getString(i2));
                }
                database1.insert(MainActivity.TABLE_CUSTOM_WORKOUT, null, contentValues3);
            }
        }
        cursor3.close();
        database1.close();
        database2.close();
        Editor editor = getSharedPreferences(MainActivity.SHAREPRE, 0).edit();
        editor.putBoolean("isF", false);
        editor.apply();
    }

    private void processCopy() {
        if (!getDatabasePath(MainActivity.DATABASE_NAME).exists()) {
            try {
                CopyDataBaseFromAsset();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void processCopy2() {
        if (!getDatabasePath(MainActivity.DATABASE_NAME2).exists()) {
            try {
                InputStream myInput = getAssets().open(MainActivity.DATABASE_NAME2);
                String outFileName = getApplicationInfo().dataDir + MainActivity.DB_PATH_SUFFIX + MainActivity.DATABASE_NAME2;
                File f = new File(getApplicationInfo().dataDir + MainActivity.DB_PATH_SUFFIX);
                if (!f.exists()) {
                    f.mkdir();
                }
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = myInput.read(buffer);
                    if (length > 0) {
                        myOutput.write(buffer, 0, length);
                    } else {
                        myOutput.flush();
                        myOutput.close();
                        myInput.close();
                        return;
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(MainActivity.DATABASE_NAME);
            String outFileName = getApplicationInfo().dataDir + MainActivity.DB_PATH_SUFFIX + MainActivity.DATABASE_NAME;
            File f = new File(getApplicationInfo().dataDir + MainActivity.DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            while (true) {
                int length = myInput.read(buffer);
                if (length > 0) {
                    myOutput.write(buffer, 0, length);
                } else {
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFirsttime() {
        String[] list = {"en", "de", "es", "fr", "it", "ja", "ko", "pt", "ru", "sv", "tr", "zh", "nl", "th"};
        String[] list_02 = {"English", "Deutsch", "Spanish", "French", "Italian", "Japanese", "Korean", "Portuguese", "Russian", "Swedish", "Turkish", "Chinese", "Dutch", "Thai"};
        String locale = Locale.getDefault().getLanguage();
        for (int i = 0; i < list.length; i++) {
            if (locale.equals(list[i])) {
                Editor edit = getSharedPreferences(MainActivity.SHAREPRE, 0).edit();
                edit.putString(MainActivity.NAME_EXERCISE_SEND, list[i]);
                edit.putString(MainActivity.DES_EXERCISE_SEND, list_02[i]);
                edit.putString("CHECK_FIRST_TIME", "1");
                edit.apply();
            }
        }
    }

   /* private void requestNewInterstitial() {
        mInterstitialAd.loadAd(new Builder().build());
    }*/
}
