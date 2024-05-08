package com.NewAppZone.home_workout.fitness_bodybuilding.Coach;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {
    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
    private String Des;
    private String Name;
    private String code;
    private String idExercise;
    private int imgAnh;
    private String video;

    public Exercise(String idExercise2, int imgAnh2, String name, String des, String code2, String video2) {
        this.idExercise = idExercise2;
        this.imgAnh = imgAnh2;
        this.Name = name;
        this.Des = des;
        this.code = code2;
        this.video = video2;
    }

    public Exercise(String idExercise2, String name, String des, String code2) {
        this.idExercise = idExercise2;
        this.Name = name;
        this.Des = des;
        this.code = code2;
    }

    protected Exercise(Parcel in) {
        this.idExercise = in.readString();
        this.imgAnh = in.readInt();
        this.Name = in.readString();
        this.Des = in.readString();
        this.code = in.readString();
        this.video = in.readString();
    }

    public String getIdExercise() {
        return this.idExercise;
    }

    public void setIdExercise(String idExercise2) {
        this.idExercise = idExercise2;
    }

    public int getImgAnh() {
        return this.imgAnh;
    }

    public void setImgAnh(int imgAnh2) {
        this.imgAnh = imgAnh2;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDes() {
        return this.Des;
    }

    public void setDes(String des) {
        this.Des = des;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code2) {
        this.code = code2;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video2) {
        this.video = video2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idExercise);
        dest.writeInt(imgAnh);
        dest.writeString(Name);
        dest.writeString(Des);
        dest.writeString(code);
        dest.writeString(video);
    }
}
