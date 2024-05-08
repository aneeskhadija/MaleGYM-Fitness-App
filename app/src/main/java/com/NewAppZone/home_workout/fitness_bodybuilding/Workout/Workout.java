package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

public class Workout {

    private int day;
    private int id_categ_guide;
    private int id_exercise;
    private int id_num_days;

    public Workout(int id_categ_guide2, int id_num_days2, int day2, int id_exercise2) {
        this.id_categ_guide = id_categ_guide2;
        this.id_num_days = id_num_days2;
        this.day = day2;
        this.id_exercise = id_exercise2;
    }

    public int getId_categ_guide() {
        return this.id_categ_guide;
    }

    public void setId_categ_guide(int id_categ_guide2) {
        this.id_categ_guide = id_categ_guide2;
    }

    public int getId_num_days() {
        return this.id_num_days;
    }

    public void setId_num_days(int id_num_days2) {
        this.id_num_days = id_num_days2;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day2) {
        this.day = day2;
    }

    public int getId_exercise() {
        return this.id_exercise;
    }

    public void setId_exercise(int id_exercise2) {
        this.id_exercise = id_exercise2;
    }
}
