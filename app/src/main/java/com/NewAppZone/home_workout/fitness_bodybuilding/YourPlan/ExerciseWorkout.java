package com.NewAppZone.home_workout.fitness_bodybuilding.YourPlan;

public class ExerciseWorkout {

    private String desExercise;
    private int idExercise;
    private String nameExercise;
    private int numberImage;
    private int numberSound;
    private String pathExercise;

    public ExerciseWorkout(int idExercise2, String nameExercise2, String desExercise2, String pathExercise2, int numberSound2, int numberImage2) {
        this.idExercise = idExercise2;
        this.nameExercise = nameExercise2;
        this.pathExercise = pathExercise2;
        this.desExercise = desExercise2;
        this.numberSound = numberSound2;
        this.numberImage = numberImage2;
    }

    public int getNumberImage() {
        return this.numberImage;
    }

    public void setNumberImage(int numberImage2) {
        this.numberImage = numberImage2;
    }

    public int getNumberSound() {
        return this.numberSound;
    }

    public void setNumberSound(int numberSound2) {
        this.numberSound = numberSound2;
    }

    public int getIdExercise() {
        return this.idExercise;
    }

    public void setIdExercise(int idExercise2) {
        this.idExercise = idExercise2;
    }

    public String getNameExercise() {
        return this.nameExercise;
    }

    public void setNameExercise(String nameExercise2) {
        this.nameExercise = nameExercise2;
    }

    public String getPathExercise() {
        return this.pathExercise;
    }

    public void setPathExercise(String pathExercise2) {
        this.pathExercise = pathExercise2;
    }

    public String getDesExercise() {
        return this.desExercise;
    }

    public void setDesExercise(String desExercise2) {
        this.desExercise = desExercise2;
    }
}
