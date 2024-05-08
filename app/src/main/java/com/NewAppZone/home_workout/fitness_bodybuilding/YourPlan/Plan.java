package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

public class Plan {

    private String dateCreate;
    private String description;
    private int idPlan;
    private String name;
    private String reps;

    public Plan(int id_exercise, String name2, String reps2, String dateCreateOrPath, String description2) {
        this.name = name2;
        this.reps = reps2;
        this.idPlan = id_exercise;
        this.dateCreate = dateCreateOrPath;
        this.description = description2;
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
