package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

public class ItemWorkout {

    private int colorWorkout;
    private String idWorkout;
    private int id_cate;
    private int id_dayofWeek;
    private String nameWorkout;

    public ItemWorkout(String nameWorkout2, String idWorkout2, int colorWorkout2, int id_cate2, int id_dayofWeek2) {
        this.nameWorkout = nameWorkout2;
        this.idWorkout = idWorkout2;
        this.colorWorkout = colorWorkout2;
        this.id_cate = id_cate2;
        this.id_dayofWeek = id_dayofWeek2;
    }

    public String getNameWorkout() {
        return this.nameWorkout;
    }

    public void setNameWorkout(String nameWorkout2) {
        this.nameWorkout = nameWorkout2;
    }

    public String getIdWorkout() {
        return this.idWorkout;
    }

    public void setIdWorkout(String idWorkout2) {
        this.idWorkout = idWorkout2;
    }

    public int getColorWorkout() {
        return this.colorWorkout;
    }

    public void setColorWorkout(int colorWorkout2) {
        this.colorWorkout = colorWorkout2;
    }

    public int getId_cate() {
        return this.id_cate;
    }

    public void setId_cate(int id_cate2) {
        this.id_cate = id_cate2;
    }

    public int getId_dayofWeek() {
        return this.id_dayofWeek;
    }

    public void setId_dayofWeek(int id_dayofWeek2) {
        this.id_dayofWeek = id_dayofWeek2;
    }
}
