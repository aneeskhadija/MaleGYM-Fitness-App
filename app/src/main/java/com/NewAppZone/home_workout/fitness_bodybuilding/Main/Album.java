package com.NewAppZone.home_workout.fitness_bodybuilding.Main;

public class Album {
    private String name;
    private int numOfSongs;
    private String thumbnail;

    public Album() {
    }

    public Album(String name2, int numOfSongsString, String thumbnail2) {
        this.name = name2;
        this.numOfSongs = this.numOfSongs;
        this.thumbnail = thumbnail2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public int getNumOfSongs() {
        return this.numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs2) {
        this.numOfSongs = numOfSongs2;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail2) {
        this.thumbnail = thumbnail2;
    }
}
