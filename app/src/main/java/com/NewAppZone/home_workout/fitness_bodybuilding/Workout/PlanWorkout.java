package com.NewAppZone.home_workout.fitness_bodybuilding.Workout;

public class PlanWorkout {

    private String dateCreate;
    private String description;
    private int idPlan;
    private String name;
    private int numberImg;
    private String reps;

    public PlanWorkout(int idPlan2, String name2, String reps2, String dateCreateOrPath, String description2, int numberImg2) {
        this.name = name2;
        this.reps = reps2;
        this.idPlan = idPlan2;
        this.dateCreate = dateCreateOrPath;
        this.description = description2;
        this.numberImg = numberImg2;
    }

    public int getNumberImg() {
        return this.numberImg;
    }

    public void setNumberImg(int numberImg2) {
        this.numberImg = numberImg2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getReps() {
        return this.reps;
    }

    public void setReps(String reps2) {
        this.reps = reps2;
    }

    public int getIdPlan() {
        return this.idPlan;
    }

    public void setIdPlan(int idPlan2) {
        this.idPlan = idPlan2;
    }

    public String getDateCreate() {
        return this.dateCreate;
    }

    public void setDateCreate(String dateCreate2) {
        this.dateCreate = dateCreate2;
    }
}
