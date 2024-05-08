package com.NewAppZone.home_workout.fitness_bodybuilding.Reminder;

import android.content.Context;
import com.NewAppZone.home_workout.fitness_bodybuilding.R;
import java.util.List;

public class Reminder {

    private int hour;
    private int id;
    private List<Boolean> isDayinWeekOn;
    private boolean isRepeatOn;
    private int minute;
    private String time;

    public Reminder(int id2, String time2, List<Boolean> isDayinWeekOn2, boolean isRepeatOn2, int hour2, int minute2) {
        this.id = id2;
        this.time = time2;
        this.isDayinWeekOn = isDayinWeekOn2;
        this.isRepeatOn = isRepeatOn2;
        this.hour = hour2;
        this.minute = minute2;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour2) {
        this.hour = hour2;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute2) {
        this.minute = minute2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time2) {
        this.time = time2;
    }

    public List<Boolean> getIsDayinWeekOn() {
        return this.isDayinWeekOn;
    }

    public void setIsDayinWeekOn(List<Boolean> isDayinWeekOn2) {
        this.isDayinWeekOn = isDayinWeekOn2;
    }

    public boolean getIsRepeatOnStatus() {
        return this.isRepeatOn;
    }

    public void setRepeatOn(boolean repeatOn) {
        this.isRepeatOn = repeatOn;
    }

    public String getTextDaySeletecd(Context context) {
        String[] txtDaySelected = context.getResources().getStringArray(R.array.week_simple);
        String s = "";
        for (int i = 0; i < 7; i++) {
            if ((Boolean) this.isDayinWeekOn.get(i)) {
                if (i < 6) {
                    s = s + txtDaySelected[i] + ", ";
                } else {
                    s = s + txtDaySelected[i];
                }
            }
        }
        if (s == null || s.length() <= 0 || s.charAt(s.length() - 1) != ',') {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }
}
