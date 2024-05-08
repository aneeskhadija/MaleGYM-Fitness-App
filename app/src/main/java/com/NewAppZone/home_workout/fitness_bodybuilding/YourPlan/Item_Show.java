package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

public class Item_Show {

    private int day;
    private String des;
    private int id;
    private int id_exercise;
    private String name;
    private int number_set;
    private String path;
    private int reps;

    public Item_Show(int id2, int id_exercise2, String name2, String des2, String path2, int number_set2, int reps2, int day2) {
        this.id = id2;
        this.id_exercise = id_exercise2;
        this.name = name2;
        this.des = des2;
        this.path = path2;
        this.number_set = number_set2;
        this.reps = reps2;
        this.day = day2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getId_exercise() {
        return this.id_exercise;
    }

    public void setId_exercise(int id_exercise2) {
        this.id_exercise = id_exercise2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String des2) {
        this.des = des2;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path2) {
        this.path = path2;
    }

    public int getNumber_set() {
        return this.number_set;
    }

    public void setNumber_set(int number_set2) {
        this.number_set = number_set2;
    }

    public int getReps() {
        return this.reps;
    }

    public void setReps(int reps2) {
        this.reps = reps2;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day2) {
        this.day = day2;
    }
}
