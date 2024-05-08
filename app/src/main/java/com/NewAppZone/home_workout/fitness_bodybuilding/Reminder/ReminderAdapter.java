package com.NewAppZone.home_workout.fitness_bodybuilding.Reminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.Calendar;
import java.util.List;
import com.NewAppZone.home_workout.fitness_bodybuilding.Main.MainActivity;

public class ReminderAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private boolean isTouched;
    private Context mContext;
    private List<Reminder> reminderList;

    class ViewHolder {
        ImageView btnDelete;
        SwitchCompat switchCompat;
        TextView txtDayReminder;
        TextView txtTime;

        ViewHolder() {
        }
    }

    public ReminderAdapter(List<Reminder> reminderList2, Context mContext2) {
        this.reminderList = reminderList2;
        this.inflater = LayoutInflater.from(mContext2);
        this.mContext = mContext2;
    }

    public int getCount() {
        return this.reminderList.size();
    }

    public Object getItem(int position) {
        return this.reminderList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    @SuppressLint("ClickableViewAccessibility")
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.abc_action_usages, null);
            holder = new ViewHolder();
            holder.txtTime = convertView.findViewById(R.id.select_time);
            holder.txtDayReminder = convertView.findViewById(R.id.select_day);
            holder.switchCompat = convertView.findViewById(R.id.isSelected);
            isTouched = false;
            holder.switchCompat.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    isTouched = true;
                    return false;
                }
            });
            holder.switchCompat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isTouched) {
                        isTouched = false;
                        if (isChecked) {
                            SQLiteDatabase database = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("isRepeatOn", "1");
                            database.update(MainActivity.TABLE_REMINDER, contentValues, "idReminder LIKE ?", new String[]{reminderList.get(position).getId() + ""});
                            for (int i = 0; i < 7; i++) {
                                if (reminderList.get(position).getIsDayinWeekOn().get(i)) {
                                    xuLyCancelAlarm((reminderList.get(position).getId() * 10) + i);
                                    xuLyScheduleAlarm((reminderList.get(position).getId() * 10) + i, i, reminderList.get(position).getHour(), reminderList.get(position).getMinute());
                                }
                            }
                            return;
                        }
                        for (int i2 = 0; i2 < 7; i2++) {
                            xuLyCancelAlarm((reminderList.get(position).getId() * 10) + i2);
                        }
                        SQLiteDatabase database2 = mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("isRepeatOn", "0");
                        database2.update(MainActivity.TABLE_REMINDER, contentValues2, "idReminder LIKE ?", new String[]{reminderList.get(position).getId() + ""});
                    }
                }
            });
            holder.btnDelete = convertView.findViewById(R.id.btn_delete);
            holder.btnDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Builder builder = new Builder(mContext);
                    builder.setTitle(R.string.exit);
                    builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            long delete = (long) mContext.openOrCreateDatabase(MainActivity.DATABASE_NAME, 0, null).delete(MainActivity.TABLE_REMINDER, "idReminder LIKE ?", new String[]{"" + reminderList.get(position).getId()});
                            for (int i = 0; i < 7; i++) {
                                xuLyCancelAlarm((reminderList.get(position).getId() * 10) + i);
                            }
                            reminderList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setPositiveButton(R.string.cancel, null);
                    builder.create().show();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Reminder item = (Reminder) getItem(position);
        if (item != null) {
            holder.txtTime.setText(item.getTime());
            holder.switchCompat.setChecked(item.getIsRepeatOnStatus());
            holder.txtDayReminder.setText(item.getTextDaySeletecd(mContext));
        }
        return convertView;
    }

    private void xuLyCancelAlarm(int request_code) {
        try {
            ((AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE)).cancel(PendingIntent.getBroadcast(mContext, request_code, new Intent(mContext, AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, Request_code, new Intent(mContext, AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Context context = mContext;
        Context context2 = mContext;
        ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE))
                .setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 604800000, pendingIntent);
    }
}
